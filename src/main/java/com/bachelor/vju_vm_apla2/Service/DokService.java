package com.bachelor.vju_vm_apla2.Service;

import com.bachelor.vju_vm_apla2.Config.CustomClientException;
import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.CreateJournalpost_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.ResponeReturnFromDokArkiv_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.Saf.GetJournalpostList_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.Saf.ReturnFromGraphQl_DTO;
import com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv.CreateJournalpost;
import com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv.Dokumenter;
import com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv.Dokumentvariant;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.stream.Stream;

@Service
public class DokService {
    private static final Logger logger = LogManager.getLogger(DokService.class);

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
     * Orchestrates the updating of document IDs for both 'oldMetadata' and 'newMetadata' within a CreateJournalpost_DTO object,
     * sends serialized versions of these objects as separate POST requests, and handles responses.
     *
     * This method performs the following operations in sequence:
     * 1. Asynchronously updates document IDs in both the old and new metadata components of the DTO.
     * 2. After ensuring both updates are complete, it proceeds to serialize each component into JSON format.
     * 3. It then sends each serialized metadata object as a separate POST request to a designated URI.
     * 4. Responses from these POST requests are combined and logged, and any occurring errors are handled and logged.
     * 5. Finally, it aggregates the responses into a single ResponseEntity object that encapsulates the combined responses
     *    from both POST operations, which is then returned to the caller.
     *
     * @param meta The CreateJournalpost_DTO object that contains the oldMetadata and newMetadata needing processing.
     * @return Mono<ResponseEntity<String>> This returns a Mono that emits a ResponseEntity containing the combined responses
     *         from both metadata POST requests if successful, or logs and returns errors if any arise during the operations.
     */
    public Mono<ResponseEntity<List<ResponeReturnFromDokArkiv_DTO>>>createJournalpost(CreateJournalpost_DTO meta) {
        logger.info("1 . Inne i createJournalpost - Received JSON data: {}", meta);

        // Setter versjon på metadata
        meta.getOldMetadata().setVersjon("old");
        meta.getNewMetadata().setVersjon("new");

        Mono<CreateJournalpost> updatedOldMeta = updateDocumentIdsInDto(meta.getOldMetadata()).thenReturn(meta.getOldMetadata());
        Mono<CreateJournalpost> updatedNewMeta = updateDocumentIdsInDto(meta.getNewMetadata()).thenReturn(meta.getNewMetadata());

        return Mono.zip(updatedOldMeta, updatedNewMeta)
                .doOnSuccess(item -> logger.info("12. Nå skal begge DTO være klare for å sende til dokarkiv gjennom serializeAndSendJournalpost"))
                .flatMap(tuple -> {
                    Mono<ResponeReturnFromDokArkiv_DTO> responseForOldMeta = serializeAndSendJournalpost(tuple.getT1());
                    Mono<ResponeReturnFromDokArkiv_DTO> responseForNewMeta = serializeAndSendJournalpost(tuple.getT2());
                    return Mono.zip(responseForOldMeta, responseForNewMeta, List::of);
                })
                .map(responses -> {
                    logger.info("Responses: {}, {}", responses.get(0), responses.get(1));
                    return ResponseEntity.ok().body(responses);
                })
                .doOnError(error -> logger.error("Error in processing", error));
    }


    /**
     * Serializes a CreateJournalpost object to a JSON string and sends it as a POST request to a defined URI.
     * This method is responsible for handling the entire process of converting the journal post data into JSON format,
     * sending it over the network, and managing the responses and errors that may occur during the process.
     *
     * Detailed Steps:
     * 1. Serialization: Converts the CreateJournalpost object into a JSON string using Jackson's ObjectMapper.
     *    - Logs the JSON string for verification and debugging purposes.
     * 2. Network Communication:
     *    - Sends the serialized JSON as a POST request to a predefined URI ("http://external-service.com/api/journalposts").
     *    - Sets the 'Content-Type' header to 'application/json' to indicate the media type of the request body.
     * 3. Response Handling:
     *    - On successful POST, logs the server's response indicating successful data submission.
     *    - On error (HTTP status codes like 4xx or 5xx), it extracts the error message from the response, logs it,
     *      and propagates an error signal using a CustomClientException.
     * 4. Error Handling:
     *    - If serialization fails (due to issues in data format, etc.), logs the error and returns an error signal.
     *
     * @param journalPost The CreateJournalpost object to be serialized and sent.
     * @return Mono<String> A Mono that emits the body of the response if the POST is successful,
     *         or errors out with a CustomClientException or a serialization-related exception if not.
     */
    private Mono<ResponeReturnFromDokArkiv_DTO> serializeAndSendJournalpost(CreateJournalpost journalPost) {
        logger.info("13. Vi er i serializeAndSendJournalpost med " + journalPost + " og prøver å gjøre kall til wiremock nå");

        try {
            String jsonPayload = objectMapper.writeValueAsString(journalPost);
            logger.info("14 . Sending JSON data: {}", jsonPayload);  // Log the serialized JSON string.

            String uri = "/mock/dockarkiv";
            return webClient.post()
                    .uri(url+"/mock/dockarkiv")
                    .header("Content-Type", "application/json")  // Specify media type of the request body
                    .bodyValue(jsonPayload)  // Attach the JSON payload to the POST request
                    .retrieve()  // Initiate the retrieval of the response
                    .onStatus(status -> status.isError(), clientResponse ->
                            clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                                // Log and throw an Exception if there is an HTTP error response
                                System.out.println("We are inside the Service-class that will provide specific error codes:");
                                int statusValue = clientResponse.statusCode().value();
                                String errorMessage = "Error calling external service: " + statusValue + " - " + errorBody;
                                return Mono.error(new CustomClientException(statusValue, errorMessage));
                            }))
                    .bodyToMono(ResponeReturnFromDokArkiv_DTO.class)  // Extract the response body as a string
                    .doOnSuccess(response -> logger.info("Received POST response for: {}", journalPost.getTittel()))  // Log successful response
                    .doOnError(error -> logger.error("Error during POST request for: {}", journalPost.getTittel()));  // Log any errors that occur
        } catch (JsonProcessingException e) {
            // Log and return error if JSON serialization fails
            logger.error("Error serializing DTO to JSON", e);
            return Mono.error(e);
        }
    }


    ////////////////////////////Handle Beta Metode avansert//////////////////////////////////////////////////////

    /**
     * Asynchronously updates the document IDs within a given CreateJournalpost object by executing a series of transformations
     * and replacements dictated by business rules encapsulated in the extractAndReplaceDocumentIds method.
     *
     * This method executes the following sequence of operations:
     * 1. Extraction and Replacement of IDs:
     *    - Initiates the process to extract existing document IDs and calculate their replacements using the
     *      extractAndReplaceDocumentIds method. This method returns a Mono<List<String>> representing the new document IDs.
     * 2. Application of New IDs:
     *    - Subscribes to the Mono returned by extractAndReplaceDocumentIds and updates the document IDs in the CreateJournalpost
     *      DTO with the new values once they are available.
     *    - Logs the updated DTO for verification and debugging purposes, aiding in ensuring data integrity throughout the process.
     * 3. Completion Signal:
     *    - After applying the new document IDs, the method returns a Mono<Void> that completes when all operations are finished,
     *      indicating that the DTO has been successfully updated.
     *
     * @param dto The CreateJournalpost object whose document IDs are to be updated.
     * @return Mono<Void> A Mono that signals completion of the update process, used for chaining further reactive operations if necessary.
     */
    private Mono<Void> updateDocumentIdsInDto(CreateJournalpost dto) {
        logger.info("2. Vi er inne i updateDocumentIdsInDto og Starting to update Document IDs in DTO: {}", dto);
        Mono<List<String>> cachedDocumentIds = extractAndReplaceDocumentIds(dto).cache();  // Cache the results of this operation

        logger.info("8. Vi er tilbake i updateDocumentIdsInDto og skal nå sette Base64 string inn i DTO med metode replaceDocumentIdsInDto");

        return cachedDocumentIds
                .flatMap(newIds -> replaceDocumentIdsInDto(dto, newIds))
                .then()
                .doOnSuccess(done -> logger.info("11. Tilbake til updateDocumentIdsInDto etter at DTO har fått nye fysiskID. Vi skal tilbake til craetejournapost"))
                .doOnError(error -> logger.error("Error updating document IDs", error));
    }

    /**
     * Updates the 'fysiskDokument' fields within the documents of a CreateJournalpost object using a list of new IDs.
     * This method iterates over each document and its variants contained in the provided DTO, replacing their existing
     * document IDs with new ones supplied in the 'newIds' list.
     *
     * Detailed Steps:
     * 1. Iterate over all documents in the provided CreateJournalpost object.
     * 2. For each document, iterate over all its variants.
     * 3. Replace the 'fysiskDokument' field of each variant with a new ID from the 'newIds' list.
     *    - The replacements are done in a sequential manner, using an incrementing counter to ensure each document variant
     *      receives a unique new ID from the list.
     * 4. Repeat the process for each document to ensure all IDs are updated.
     *    - The method performs two full passes over the document list to double-check and ensure all IDs are correctly updated.
     *
     * @param dto The CreateJournalpost object containing the documents whose IDs need updating.
     * @param newIds A list of new document IDs intended to replace the existing ones. This list must be at least as long
     *               as the number of document variants in the DTO to avoid index errors.
     */
    private Mono<Void> replaceDocumentIdsInDto(CreateJournalpost dto, List<String> newIds) {
        logger.info("9. Vi er i replaceDocumentIdsInDto og kommet inn med DTO: {} og nye IDer: {}", dto, newIds);
        int count = 0;
        for (Dokumenter dokument : dto.getDokumenter()) {
            for (Dokumentvariant variant : dokument.getDokumentvarianter()) {
                if (count < newIds.size()) {
                    String oldId = variant.getFysiskDokument();  // Lagrer gammel ID for logging
                    variant.setFysiskDokument(newIds.get(count));
                    logger.info("Erstattet gammel ID: {} med ny ID: {} for dokumentvariant", oldId, newIds.get(count));
                    count++;
                }
            }
        }
        logger.info("10. Ferdig med å erstatte IDer i DTO, oppdatert DTO: {}", dto);
        return Mono.empty();  // Returnerer en tom Mono for å signalisere fullførelse.
    }


    /**
     * Extracts and replaces all document IDs within a given CreateJournalpost object and combines the results
     * into a single list. This method processes the document IDs by calling the `processDocuments` method twice,
     * once for potentially the old IDs and once for potentially the new IDs, then merges the resulting lists into one.
     *
     * Steps:
     * 1. Call the `processDocuments` method twice using the same DTO but with the same journal post ID each time.
     *    This could be intended to handle different scenarios or conditions within the processing method.
     * 2. Each call to `processDocuments` is expected to return a list of processed (possibly replaced) document IDs.
     * 3. The two lists of IDs are then combined into a single list to consolidate all the new and old IDs.
     * 4. This consolidated list of IDs is then returned as a Mono for further asynchronous processing or handling.
     *
     * Note: The method currently uses the same DTO and the same hardcoded 'journalpostId' for both calls which might
     *       not be intended and could be a potential area for bugs if different behaviors are expected for different
     *       IDs or processing rounds.
     *
     * @param dto The CreateJournalpost object containing the documents whose IDs need to be extracted and replaced.
     * @return Mono<List<String>> A reactive Mono that emits a single list containing all the new and old document IDs
     *         combined after both processing calls.
     */
    private Mono<List<String>> extractAndReplaceDocumentIds(CreateJournalpost dto) {
        logger.info("3. Vi er inne i extractAndReplaceDocumentIds og Starting ID extraction for: {}", dto);

        // Obtain the journal post ID in a correct manner
        String journalpostId = "1";  // Replace with dynamic ID retrieval logic

        // Single call to process the documents
        return processDocuments(dto, journalpostId)
                .map(ids -> {
                    // Optionally you can handle the IDs further here if needed
                    logger.info("7. Vi er tilbake til extractAndReplaceDocumentIds og har en liste med encoded string som skal returners");
                    return ids;
                });
    }


    /**
     * Processes all document variants within a CreateJournalpost object to potentially replace their 'fysiskDokument' IDs.
     * This method asynchronously retrieves new document IDs for each variant using an external service or a random ID generator
     * and collects these new IDs into a list.
     *
     * Steps:
     * 1. Iterate over each document in the provided CreateJournalpost object and each of its variants.
     * 2. For each variant, asynchronously fetch a new ID using the `hentDokument_DokArkiv` method (or `random_hentdok_dokarkiv` for testing),
     *    which might involve calling an external document archive service to get a new document ID based on the current ID and the journal post ID.
     * 3. Log the replacement of each document ID for tracking and verification purposes.
     * 4. Collect all the asynchronous operations (Mono<String>) into a list and use Mono.zip to wait for all of them to complete.
     * 5. Once all new IDs are retrieved, combine them into a single list and return this list as the result of the Mono.
     *
     * This method ensures that all document variants in the metadata are processed concurrently for efficiency, and the results
     * are aggregated into a single list for further processing or verification.
     *
     * @param metadata The CreateJournalpost object containing the documents and their variants to be processed.
     * @param journalpostId A journal post ID used potentially in the document ID retrieval process.
     * @return Mono<List<String>> A reactive Mono that emits a list of new document IDs, one for each variant in the metadata.
     */
    private Mono<List<String>> processDocuments(CreateJournalpost metadata, String journalpostId) {
        logger.info("4. Processing documents for metadata: {} and journalPostID: {} ", metadata + " " +  journalpostId);

        // Creating a list to hold Monos of document IDs
        List<Mono<String>> documentIdMonos = new ArrayList<>();

        // Iterate over all documents and their variants to replace document IDs
        for (Dokumenter dokument : metadata.getDokumenter()) {
            for (Dokumentvariant variant : dokument.getDokumentvarianter()) {
                Mono<String> documentIdMono = hentDokument_DokArkiv(variant.getFysiskDokument(), journalpostId)
                //Mono<String> documentIdMono = random_hentdok_dokarkiv(variant.getFysiskDokument(), journalpostId)
                        .doOnNext(newId -> logger.info("6 Vi er tilbake til processDocuments og har mottat en Base64 string fra hentDokument_DokArkiv og puttet i en liste" ) )
                        .cache(); // Cache each Mono to ensure the operation is performed only once
                documentIdMonos.add(documentIdMono);
            }
        }

        // Use Mono.zip to wait for all document ID fetching operations to complete and then collect the results
        return Mono.zip(documentIdMonos, results ->
                        Stream.of(results).map(result -> (String) result).collect(Collectors.toList())
                )
                .doOnNext(allIds -> logger.info("All document IDs processed and collected: {}", allIds));
    }



    /**
     * Retrieves a document based on its ID and the journal post ID, converts the document data into a Base64 string.
     * This method performs an HTTP GET request to an external service and handles various response scenarios including
     * successful data retrieval and different types of errors.
     *
     * Steps:
     * 1. Build the endpoint URL using the document and journal post IDs.
     * 2. Send an HTTP GET request to the constructed URL.
     * 3. Handle HTTP status errors by logging them and throwing a CustomClientException with detailed error information.
     * 4. If the response is successful, convert the received byte array (document data) into a Base64 string to standardize
     *    the format for further processing or storage.
     * 5. Log and manage unexpected errors using a generic error message, and continue the flow by providing a fallback
     *    error message encoded in Base64.
     *
     * The method uses reactive programming principles to handle asynchronous HTTP calls and error handling,
     * ensuring that all operations are non-blocking and efficiently managed.
     *
     * @param dokumentInfoId The ID of the document to retrieve.
     * @param journalpostId The ID of the journal post associated with the document.
     * @return Mono<String> A reactive Mono that emits the Base64-encoded string of the document if retrieval is successful,
     *         or errors out with appropriate exceptions if issues occur during the process.
     */
    public Mono<String> hentDokument_DokArkiv(String dokumentInfoId, String journalpostId) {

        logger.info("5. vi er inne i hentDokument_DokArkiv med denne meta" + dokumentInfoId + "og " + journalpostId);
        String endpoint = String.format("/mock/rest/hentdokument/%s/%s", journalpostId, dokumentInfoId);
        logger.info("Fetching document with ID: {} for journal post ID: {}", dokumentInfoId, journalpostId);

        return webClient.get()
                .uri(url + endpoint)
                .retrieve()
                .onStatus(status -> status.isError(), clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            int statusValue = clientResponse.statusCode().value();
                            String errorMessage = "Error fetching document: " + statusValue + " - " + errorBody;
                            logger.error(errorMessage);
                            return Mono.error(new CustomClientException(statusValue, errorMessage));
                        }))
                .bodyToMono(byte[].class)  // Convert the response to a byte array
                .map(bytes -> Base64.getEncoder().encodeToString(bytes))  // Convert the byte array to a Base64 string
                .onErrorResume(e -> {
                    if (!(e instanceof CustomClientException)) {
                        logger.error("An unexpected error occurred: ", e);
                        String errorMessageForClient = "API error in retrieving documents, please try again later.";
                        return Mono.just(Base64.getEncoder().encodeToString((errorMessageForClient).getBytes(StandardCharsets.UTF_8)));
                    }
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



    /////////////////METODE FOR Å GJØRE KALL TIL WIREMOCK////////////////////////////





}
