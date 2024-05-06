package com.bachelor.vju_vm_apla2.Service.DokArkiv_Service;

import com.bachelor.vju_vm_apla2.Config.CustomClientException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service

public class HentDokumenter_READ {

    private static final Logger logger = LogManager.getLogger(HentDokumenter_READ.class);

    @Value("${wiremock-dok.combined}")
    private String url;


    private final WebClient webClient;

    @Autowired
    public HentDokumenter_READ() {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
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
        String endpoint = String.format("/rest/hentdokument/%s/%s", journalpostId, dokumentInfoId);
        logger.info("Fetching document with ID: {} for journal post ID: {}", dokumentInfoId, journalpostId);

        return webClient.get()
                .uri(url + endpoint)
                .retrieve()
                .onStatus(status -> status.isError(), clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            int statusValue = clientResponse.statusCode().value();
                            String errorMessage = "Error fetching document: " + statusValue + " - " + errorBody;
                            logger.error(errorMessage);
                            return Mono.error(new CustomClientException(statusValue, errorMessage, "hentDokument_DokArkiv"));
                        }))
                .bodyToMono(byte[].class)  // Convert the response to a byte array
                .map(bytes -> Base64.getEncoder().encodeToString(bytes))  // Convert the byte array to a Base64 string
                .onErrorResume(e -> {
                    if (!(e instanceof CustomClientException)) {
                        logger.error("An unexpected error occurred: ", e);
                        String errorMessageForClient = "API error in retrieving documents, please try again later.";
                        return Mono.just(Base64.getEncoder().encodeToString((errorMessageForClient).getBytes(StandardCharsets.UTF_8)));
                    }
                    return Mono.error((new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DoArkiv_Service - HentDokumenter_ - FAIL - Feil oppstått ved prosessering av opprettnyejournalposter", e)));
                });
    }
}
