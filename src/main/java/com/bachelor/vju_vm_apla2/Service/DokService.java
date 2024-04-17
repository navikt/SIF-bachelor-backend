package com.bachelor.vju_vm_apla2.Service;

import com.bachelor.vju_vm_apla2.Config.CustomClientException;
import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.CreateJournalpost_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.Saf.GetJournalpostList_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.Saf.ReturnFromGraphQl_DTO;
import com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv.CreateJournalpost;
import com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv.Dokumenter;
import com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv.Dokumentvariant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class DokService {
    private static final Logger logger = LogManager.getLogger(SimpleService.class);

    private final WebClient webClient;
    @Value("${wiremock-dok.combined}")
    private String url;
    //setter opp HTTP syntax slik at vi kan gjøre kall på serverere (Serevere er erstattet med Wiremock)
    public DokService() {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }
    private ObjectMapper objectMapper = new ObjectMapper();  // Jackson ObjectMapper for manual JSON serialization


    //------------------------------------------------------------------------------------

    public Mono<String> fetchHello() {
        return webClient.get()
                .uri(url + "/hello")
                .retrieve()
                .bodyToMono(String.class);
    }

    /////METODE FOR CREATE JOURNALPOST BETA//////////////////////

    /**
     * This method initiates the process of updating document IDs in the provided DTO, serializes the updated DTO to JSON,
     * and sends it as a POST request to an external service. It logs the JSON payload and handles any HTTP errors that might occur.
     *
     * @param meta The CreateJournalpost_DTO object containing initial data that needs to be processed and sent.
     * @return Mono<ResponseEntity<String>> This Mono will complete when the POST request is fully handled, encapsulating the response in a ResponseEntity object.
     */
    public Mono<ResponseEntity<String>> createJournalpost(CreateJournalpost_DTO meta) {
        // Asynchronously update the document IDs in the DTO
        return updateDocumentIdsInDto(meta)
                .then(Mono.defer(() -> {
                    // After all updates are complete, serialize the updated DTO to JSON
                    String jsonPayload = serializeDtoToJson(meta);
                    logger.info("Sending updated JSON data: {}", jsonPayload);

                    // Define the URI of the external service
                    String uri = "http://external-service.com/api/journalposts";

                    // Send the serialized JSON as a POST request to the external service
                    return webClient.post()
                            .uri(uri)
                            .header("Content-Type", "application/json")
                            .bodyValue(jsonPayload)
                            .retrieve()
                            .onStatus(status -> status.isError(), response ->
                                    response.bodyToMono(String.class).flatMap(errorBody -> {
                                        String errorMessage = String.format("Error response %d: %s", response.statusCode().value(), errorBody);
                                        logger.error(errorMessage);
                                        return Mono.error(new Exception(errorMessage));
                                    }))
                            .toEntity(String.class)
                            .doOnSuccess(response -> logger.info("Received response: {}", response.getStatusCode()))
                            .doOnError(error -> logger.error("Error during POST request", error));
                }));
    }

    private String serializeDtoToJson(CreateJournalpost_DTO dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (Exception e) {
            logger.error("Error serializing DTO to JSON", e);
            return "{}";
        }
    }




    ////////////////////////////Handle Beta Metode avansert//////////////////////////////////////////////////////

    /**
     * This method processes the DTO to replace each physical document ID
     * with a unique string returned by the hentdok_dokarkiv method, and ensures
     * that the DTO is correctly updated.
     *
     * @param dto The CreateJournalpost_DTO object containing both old and new metadata along with a journalpostID.
     */
    private Mono<Void> updateDocumentIdsInDto(CreateJournalpost_DTO dto) {
        Mono<List<String>> updatedDocumentIds = extractAndReplaceDocumentIds(dto);

        // Handle the Mono to replace document IDs in the DTO when they are available
        updatedDocumentIds.subscribe(newIds -> {
            replaceDocumentIdsInDto(dto, newIds);

            // Optionally log the updated DTO for verification after update
            logger.info("Updated DTO: {}", dto);
        });
        return Mono.empty();
    }

    /**
     * This method replaces old document IDs in the DTO with new ones provided in the list. It iterates over all documents
     * and variants within those documents to update their 'fysiskDokument' fields.
     *
     * @param dto The DTO containing documents whose IDs need updating.
     * @param newIds A list of new IDs to replace the old ones, corresponding in order to the documents in the DTO.
     */
    private void replaceDocumentIdsInDto(CreateJournalpost_DTO dto, List<String> newIds) {
        int count = 0;
        for (Dokumenter dokument : dto.getOldMetadata().getDokumenter()) {
            for (Dokumentvariant variant : dokument.getDokumentvarianter()) {
                variant.setFysiskDokument(newIds.get(count++));
            }
        }
        for (Dokumenter dokument : dto.getNewMetadata().getDokumenter()) {
            for (Dokumentvariant variant : dokument.getDokumentvarianter()) {
                variant.setFysiskDokument(newIds.get(count++));
            }
        }
    }
    /**
     * Combines the document processing of both old and new metadata from the DTO to replace their document IDs.
     * It uses the processDocuments method for both and combines their results into a single list of updated IDs.
     *
     * @param dto The DTO containing old and new metadata whose document IDs need to be updated.
     * @return Mono<List<String>> A reactive Mono that completes with a list of all new document IDs once all documents have been processed.
     */

    private Mono<List<String>> extractAndReplaceDocumentIds(CreateJournalpost_DTO dto) {
        //TODO: bytt til riktig journalpostID for PROD
        //String journalpostId = dto.getJournalpostID();
        String journalpostId = "1";


        // Combining both document processes into a single Mono stream
        return Mono.zip(
                processDocuments(dto.getOldMetadata(), journalpostId),
                processDocuments(dto.getNewMetadata(), journalpostId),
                (oldIds, newIds) -> {
                    List<String> allIds = new ArrayList<>();
                    allIds.addAll(oldIds);
                    allIds.addAll(newIds);
                    return allIds;
                }
        );
    }

    /**
     * Processes all documents in given metadata to update their document IDs using the hentDokument_DokArkiv method.
     * It asynchronously fetches new IDs for each document variant and collects them into a list.
     *
     * @param metadata Metadata object containing documents to process.
     * @param journalpostId The journal post ID associated with these documents, used in fetching new document IDs.
     * @return Mono<List<String>> A reactive Mono that completes with a list of new document IDs once they are all fetched.
     */
    private Mono<List<String>> processDocuments(CreateJournalpost metadata, String journalpostId) {
        List<Mono<String>> documentIdMonos = new ArrayList<>();

        for (Dokumenter dokument : metadata.getDokumenter()) {
            for (Dokumentvariant variant : dokument.getDokumentvarianter()) {
                //Mono<String> documentIdMono = hentDokument_DokArkiv(variant.getFysiskDokument(), journalpostId)
                Mono<String> documentIdMono = random_hentdok_dokarkiv(variant.getFysiskDokument(), journalpostId)
                        .doOnNext(newId -> logger.info("Replaced ID {} with {} for journal post {}", variant.getFysiskDokument(), newId, journalpostId));
                documentIdMonos.add(documentIdMono);
            }
        }

        // Wait for all document ID operations to complete and then collect the results into a list
        return Mono.zip(documentIdMonos, results ->
                List.of(results).stream()
                        .map(result -> (String) result)
                        .collect(Collectors.toList())
        );
    }


    /**
     * Fetches a document from an external document archive server using its document info ID and journal post ID.
     * The document is fetched as a byte array, converted to a Base64-encoded string to ensure safe JSON serialization.
     *
     * @param dokumentInfoId The ID of the document to fetch, used in the server endpoint URL.
     * @param journalpostId The ID of the journal post associated with the document, used in the server endpoint URL.
     * @return Mono<String> A reactive Mono that completes with the Base64-encoded string representation of the fetched document.
     */
    public Mono<String> hentDokument_DokArkiv(String dokumentInfoId, String journalpostId) {
        String endpoint = String.format("/mock/rest/hentdokument/%s/%s", journalpostId, dokumentInfoId);
        logger.info("Henter dokument med ID: {} for journalpostID: {}", dokumentInfoId, journalpostId);


        return webClient.get()
                .uri(url + endpoint)
                .retrieve()
                .onStatus(status -> status.isError(), clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            int statusValue = clientResponse.statusCode().value();
                            String errorMessage = String.format("Feil ved henting av dokument: %s - %s", statusValue, errorBody);
                            logger.error(errorMessage);
                            return Mono.error(new CustomClientException(statusValue, errorMessage));
                        }))
                .bodyToMono(byte[].class)  // Konverter responsen til en byte array
                .map(bytes -> Base64.getEncoder().encodeToString(bytes))  // Konverter byte array til base64 string
                .onErrorResume(e -> {
                    if (!(e instanceof CustomClientException)) {
                        logger.error("En uventet feil oppstod: ", e);
                        String errorMessageForClient = "SAF API error in retrieving the documents, please try again later.";
                        return Mono.just(Base64.getEncoder().encodeToString((errorMessageForClient).getBytes(StandardCharsets.UTF_8)));
                    }
                    // Viderefør CustomClientException slik at den kan håndteres oppstrøms
                    return Mono.error(e);
                });
    }












    //////////////////RANDOM STRING RETURNER//////////////

    /**
     * Asynchronously generates a random 3-letter string and logs the operation.
     * This method now returns a Mono<String> to fit into a reactive programming model.
     *
     * @param dokumentId The original document ID for logging.
     * @param journalpostId The journal post ID for logging.
     * @return Mono<String> containing the new random ID.
     */
    public Mono<String> random_hentdok_dokarkiv(String dokumentId, String journalpostId) {
        return Mono.fromSupplier(() -> {
            String newId = generateRandomString(3);
            logger.info("Received physical document ID: {}, replaced with: {}", dokumentId, newId);
            return newId;
        });
    }

    /**
     * Generates a random string of a given length using characters from A to Z.
     *
     * @param length The length of the string to generate.
     * @return A random string of the specified length.
     */
    private String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char tmp = (char) ('A' + random.nextInt('Z' - 'A'));
            sb.append(tmp);
        }
        return sb.toString();
    }







    //TODO: GetDok_dokarkiv(FysiskdokId)

    //TODO: Create_Dok(DTO old/New)
}
