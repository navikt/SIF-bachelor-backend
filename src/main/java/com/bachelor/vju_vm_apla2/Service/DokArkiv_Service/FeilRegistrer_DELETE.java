package com.bachelor.vju_vm_apla2.Service.DokArkiv_Service;

import com.bachelor.vju_vm_apla2.ErrorHandling.CustomClientException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class FeilRegistrer_DELETE {
    private static final Logger logger = LogManager.getLogger(HentDokumenter_READ.class);

    @Value("${mock-oauth2-server.combined}")
    private String url;

    private final WebClient webClient;

    public FeilRegistrer_DELETE() {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    public Mono<ResponseEntity<Boolean>> feilRegistrer_Service(String journalpostId, String type, HttpHeaders originalHeader) {
        logger.info("17. Vi er nå på å feilregistere fra opprett journalpost");
        String endpoint = "";

        String endpoint_test = "/mock/rest/test";

        if (type.equals("I")) {
            endpoint = "/rest/journalpostapi/v1/journalpost/" + journalpostId + "/feilregistrer/settStatusUtgaar";
        } else if (type.equals("U")) {
            endpoint = "/rest/journalpostapi/v1/journalpost/" + journalpostId + "/feilregistrer/settStatusAvbryt";
        } else {
            return Mono.just(ResponseEntity.badRequest().body(false)); // Returning a bad request if type is not handled
        }

        System.out.println("Service - feilregistrer: vi skal nå inn i wiremock med forespørsel: ");
        System.out.println("Original headers:");

        return this.webClient.patch()
                .uri(url + endpoint)
                .header(HttpHeaders.AUTHORIZATION, originalHeader.getFirst(HttpHeaders.AUTHORIZATION))
                //.headers(headers -> headers.addAll(headersForRequest))
                .retrieve()
                .onStatus(status -> status.isError(), clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            int statusValue = clientResponse.statusCode().value();
                            String origin = "HentDokumenter_READ - hentDokument_DokArkiv" ;
                            String errorMessage = String.format("Feil ved kall til ekstern tjeneste (DokArkiv): %d - %s", statusValue, errorBody);
                            logger.error("ERROR: " + origin + errorMessage);
                            return Mono.error(new CustomClientException(statusValue, errorMessage, origin));
                        }))
                .bodyToMono(Boolean.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(new ResponseEntity<>(true, HttpStatus.OK)) // Handle 204 No Content
                .doOnSuccess(response -> logger.info("INFO: FeilRegister_Delete - feilRegistrer_Service - Operation completed with status: {}", response.getStatusCode()))
                .onErrorResume(e -> {
                    if (e instanceof CustomClientException) {
                        return Mono.error(e);
                    } else {
                        logger.error("ERROR: FeilRegister_Delete - feilRegistrer_Service - En uventet feil oppstod: ", e);
                        return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "FeilRegister_Delete - feilRegistrer_Service - En uventet feil oppstod, vennligst prøv igjen senere.", e));
                    }
                });

    }
}
