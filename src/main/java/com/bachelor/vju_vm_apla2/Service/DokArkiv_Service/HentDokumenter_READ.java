package com.bachelor.vju_vm_apla2.Service.DokArkiv_Service;

import com.bachelor.vju_vm_apla2.ErrorHandling.CustomClientException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Base64;

@Service
public class HentDokumenter_READ {

    private static final Logger logger = LogManager.getLogger(HentDokumenter_READ.class);

    @Value("${wiremock-dok.combined}")
    private String url;


    private final WebClient webClient;

    public HentDokumenter_READ() {
        this.webClient = WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(clientDefaultCodecsConfigurer -> {
                            clientDefaultCodecsConfigurer.defaultCodecs().maxInMemorySize(500 * 1024 * 1024);
                        })
                        .build())
                .baseUrl(url)
                .build();
    }

    //TODO: SKAL DET VÆRE TOKEN I HEADER?


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

        logger.info("INFO: HentDokument_READ - hentDokument_DokArkiv - med denne meta" + dokumentInfoId + "og " + journalpostId);
        String endpoint = String.format("/rest/hentdokument/%s/%s", journalpostId, dokumentInfoId);


        return webClient.get()
                .uri(url + endpoint)
                .retrieve()
                //TODO:Legge til header for token?
                .onStatus(status -> status.isError(), clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            int statusValue = clientResponse.statusCode().value();
                            String origin = "HentDokumenter_READ - hentDokument_DokArkiv" ;
                            String errorMessage = String.format("Feil ved kall til ekstern tjeneste (DokArkiv): %d - %s", statusValue, errorBody);
                            logger.error("ERROR: " + origin + errorMessage);
                            return Mono.just(new CustomClientException(statusValue, errorBody, origin));
                        }))
                .bodyToMono(byte[].class)
                .map(bytes -> Base64.getEncoder().encodeToString(bytes))
                .onErrorResume(e -> {
                    if (e instanceof CustomClientException) {
                        return Mono.error(e);
                    } else {
                        logger.error("FAIL: HentDokumenter_READ - hentDokument_DokArkiv - En uventet feil oppstod: ", e);
                        return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "HentDokumenter_READ - hentDokument_DokArkiv - En uventet feil oppstod, vennligst prøv igjen senere.", e));
                    }
                });
    }
}
