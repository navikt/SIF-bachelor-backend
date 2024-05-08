package com.bachelor.vju_vm_apla2.Service.DokArkiv_Service;

import com.bachelor.vju_vm_apla2.ErrorHandling.CustomClientException;
import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.CreateJournalpost_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.ResponeReturnFromDokArkiv_DTO;
import com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv.CreateJournalpost;
import com.bachelor.vju_vm_apla2.Service.Utilz.ExtractDokumentIDinDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OpprettNyeJournalposter_CREATE {
    private static final Logger logger = LogManager.getLogger(OpprettNyeJournalposter_CREATE.class);


    private final ExtractDokumentIDinDTO extractDokumentIDinDTO;

    private final WebClient webClient;
    @Value("{mock-oauth2-server.combined}")
    private String url;
    //setter opp HTTP syntax slik at vi kan gjøre kall på serverere (Serevere er erstattet med Wiremock)
    @Autowired
    public OpprettNyeJournalposter_CREATE(ExtractDokumentIDinDTO extractDokumentIDinDTO) {
        this.extractDokumentIDinDTO = extractDokumentIDinDTO;
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
    public Mono<ResponseEntity<List<ResponeReturnFromDokArkiv_DTO>>> createJournalpost_Service(CreateJournalpost_DTO meta, HttpHeaders originalHeader) {
        logger.info("DokArkiv_Service - createJournalpost() -  Inne i createJournalpost - Received JSON data: {}", meta);


        // Setter versjon på metadata. Det er for stubs slik at de kan skille mellom dem
        meta.getOldMetadata().setVersjon("old");
        meta.getNewMetadata().setVersjon("new");



        Mono<CreateJournalpost> updatedOldMeta = extractDokumentIDinDTO.updateDocumentIdsInDto(meta.getOldMetadata(), meta.getJournalpostID()).thenReturn(meta.getOldMetadata());
        Mono<CreateJournalpost> updatedNewMeta = extractDokumentIDinDTO.updateDocumentIdsInDto(meta.getNewMetadata(), meta.getJournalpostID()).thenReturn(meta.getNewMetadata());

        return Mono.zip(updatedOldMeta, updatedNewMeta)
                .doOnSuccess(item -> logger.info("DoArkiv_Service - OpprettNyeJournalposter_CREATE - SUCCSESS - Parsing updateOldMeta/updateNewMeta vellykket -  Nå skal begge DTO være klare for å sende til dokarkiv gjennom postNewJournalpost (opprette nye)"))
                .flatMap(tuple -> {
                    Mono<ResponeReturnFromDokArkiv_DTO> responseForOldMeta = postNewJournalpost(tuple.getT1(), originalHeader);
                    Mono<ResponeReturnFromDokArkiv_DTO> responseForNewMeta = postNewJournalpost(tuple.getT2(), originalHeader);
                    return Mono.zip(responseForOldMeta, responseForNewMeta, List::of)
                    .onErrorResume(e -> {
                        logger.error("DoArkiv_Service - OpprettNyeJournalposter_CREATE - FAIL - Feil oppstått ved prosessering av opprettnyejournalposter: {}", e.getMessage());
                        return Mono.error((e));
                    });

                })
                .map(responses -> {
                    logger.info("DoArkiv_Service - OpprettNyeJournalposter_CREATE - SUCCSESS - Vi er tilbake fra opprette nye journaposter med  {}, {}", responses.get(0), responses.get(1));
                    return ResponseEntity.ok().body(responses);
                })
                .onErrorResume(e -> {
                    logger.error("DoArkiv_Service - OpprettNyeJournalposter_CREATE - FAIL - Feil oppstått ved prosessering av opprettnyejournalposter: {}", e.getMessage());
                    return Mono.error((new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DoArkiv_Service - OpprettNyeJournalposter_CREATE - FAIL - Feil oppstått ved prosessering av opprettnyejournalposter", e)));  // Sender en INTERNAL_SERVER_ERROR respons tilbake til klienten
                });
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
    private Mono<ResponeReturnFromDokArkiv_DTO> postNewJournalpost(CreateJournalpost journalPost, HttpHeaders originalHeader) {
        logger.info("DoArkiv_Service - serializeAndSendJournalpost() - Inne i metoden med " + journalPost + " og prøver å gjøre kall til wiremock nå");

        String jsonPayload;
        try {
            jsonPayload = objectMapper.writeValueAsString(journalPost);
            logger.info("DoArkiv_Service - postNewJournalpost() - Vi skal inn i wiremock dokarkiv nå Sending JSON data: {}", jsonPayload);
        } catch (JsonProcessingException e) {
            logger.error("DoArkiv_Service - postNewJournalpost() - FAIL - Error serializing DTO to JSON", e);
            return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DoArkiv_Service - serializeAndSendJournalpost() - FAIL - Error parsing DTO to JSON", e));
        }

        return webClient.post()
                .uri(url+"/rest/journapostapi/v1/journalpost?forsoekFerdigstill=false")
                .headers(h -> h.addAll(originalHeader))
                .bodyValue(jsonPayload)  // Attach the JSON payload to the POST request
                .retrieve()  // Initiate the retrieval of the response
                .onStatus(status -> status.isError(), clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            int statusValue = clientResponse.statusCode().value();
                            String errorMessage = "DoArkiv_Service - postNewJournalpost() - FAIL - Error calling external service: " + statusValue + " - " + errorBody;
                            logger.error("DoArkiv_Service - postNewJournalpost() - FAIL - Error calling external service:  {}", errorMessage);
                            return Mono.error(new CustomClientException(statusValue, errorMessage, "postNewJournalpost"));
                        }))
                .bodyToMono(ResponeReturnFromDokArkiv_DTO.class)
                .onErrorResume(e -> {
                    if (e instanceof CustomClientException){
                        return Mono.error(e);
                    } else {
                        logger.error("DoArkiv_Service - postNewJournalpost - FAIL - Error during POST request for: {}", journalPost.getTittel());
                        return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DoArkiv_Service - postNewJournalpost - En uventet feil oppstod, vennligst prøv igjen senere.", e));
                    }
                    });
    }



    ////////////////////////////Handle Beta Metode avansert//////////////////////////////////////////////////////



}
