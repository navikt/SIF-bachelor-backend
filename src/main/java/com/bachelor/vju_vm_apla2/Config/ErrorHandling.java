package com.bachelor.vju_vm_apla2.Config;

import com.bachelor.vju_vm_apla2.Controller.SafController;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

public class ErrorHandling {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(SafController.class);

    /**
     * Håndterer feil og lager en passende HTTP-respons med feilinformasjon.
     * Denne metoden vil sjekke typen av feilen og lage en tilpasset respons basert på feilens art.
     *
     * @param e Feilen som har oppstått.
     * @return Mono som representerer den ferdige ResponseEntity med feilinformasjon.
     */
    public static <T> Mono<ResponseEntity<T>> handleError(Throwable e) {
        // Logger-instansen for å logge informasjon og feil.

        if (e instanceof CustomClientException) {
            CustomClientException cce = (CustomClientException) e;
            logger.error("En klientspesifikk feil oppstod: {}", cce.getMessage());
            return Mono.just(ResponseEntity
                    .status(cce.getStatusCode())
                    .body(createErrorResource(cce.getMessage(), MediaType.APPLICATION_JSON)));
        } else {
            logger.error("En generell serverfeil oppstod", e);
            return Mono.just(
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(createErrorResource("En serverfeil har oppstått, vennligst prøv igjen senere.", MediaType.APPLICATION_JSON))
            );
        }
    }

    /**
     * Oppretter en feilressurs som kan sendes tilbake til klienten.
     * Denne metoden vil formatere feilmeldingen til riktig MediaType format, for eksempel JSON.
     *
     * @param errorMessage Feilmeldingen som skal sendes til klienten.
     * @param contentType  MediaType for den ønskede feilressursen.
     * @return En feilressurs som kan sendes i en ResponseEntity.
     */
    public static <T> T createErrorResource(String errorMessage, MediaType contentType) {
        if (contentType.equals(MediaType.APPLICATION_JSON)) {
            // Logger-instansen for å logge informasjon og feil.
            logger.info("Oppretter JSON feilressurs med melding: {}", errorMessage);

            // Sanitize the errorMessage to escape any double quotes
            String jsonErrorMessage = "{\"errorMessage\": \"" + errorMessage.replace("\"", "\\\"") + "\"}";
            return (T) new ByteArrayResource(jsonErrorMessage.getBytes(StandardCharsets.UTF_8));
        } else {
            // Hvis innholdstypen ikke er JSON, oppretter vi en generisk ByteArrayResource.
            return (T) new ByteArrayResource(errorMessage.getBytes(StandardCharsets.UTF_8));
        }
    }
}

