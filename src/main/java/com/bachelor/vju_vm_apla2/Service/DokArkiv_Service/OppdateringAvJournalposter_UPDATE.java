package com.bachelor.vju_vm_apla2.Service.DokArkiv_Service;

import com.bachelor.vju_vm_apla2.ErrorHandling.CustomClientException;
import com.bachelor.vju_vm_apla2.Models.DTO.Request.PostOppdaterJournalpost_DTO;
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

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OppdateringAvJournalposter_UPDATE {

    @Value("${wiremock-dok.combined}")
    private String url;

    private final WebClient webClient;
    private static final Logger logger = LogManager.getLogger(OppdateringAvJournalposter_UPDATE.class);

    private final HentDokumenter_READ hentDokumenter_READ;

    @Autowired
    public OppdateringAvJournalposter_UPDATE(HentDokumenter_READ hentDokumenter_READ) {
        this.hentDokumenter_READ = hentDokumenter_READ;
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }




    // Method to format date
    public String formatIsoDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return "{\"date\": \"" + sdf.format(date) + "\"}";
    }

    public Mono<ResponseEntity<Boolean>> oppdaterMottattDato(PostOppdaterJournalpost_DTO jpMetadata, HttpHeaders originalHeader) {
        logger.info("Vi er nå inn i oppdaterJournalpost for å oppdatere Journalpost med mottattDato");

        String journalpostID = jpMetadata.getJournalpostID();

        String endpoint = "/rest/journalpostapi/v1/journalpost/" + journalpostID;

        Date currentDate = jpMetadata.getMottattDato();

        String jsonPayload = formatIsoDate(currentDate);

        System.out.println("Service - oppdaterMottattDato: vi skal nå inn i wiremock med forespørsel: ");
        System.out.println("Original headers:");

        return this.webClient.put()
                .uri(url + endpoint)
                .header(HttpHeaders.AUTHORIZATION, originalHeader.getFirst(HttpHeaders.AUTHORIZATION))
                //.headers(headers -> headers.addAll(headersForRequest))
                .bodyValue(jsonPayload)
                .retrieve()
                .onStatus(status -> status.isError(), clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            int statusValue = clientResponse.statusCode().value();
                            String origin = "OppdateringAvJournalposter_UPDATE - oppdaterMottattDato";
                            String errorMessage = String.format("Feil ved kall til ekstern tjeneste (DokArkiv): %d - %s", statusValue, errorBody);
                            logger.error("ERROR: " + origin + errorMessage);
                            return Mono.error(new CustomClientException(statusValue, errorMessage, origin));
                        }))
                .bodyToMono(Boolean.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(new ResponseEntity<>(true, HttpStatus.OK)) // Handle 204 No Content
                .onErrorResume(e -> {
                    if (e instanceof CustomClientException){
                        return Mono.error(e);
                    } else {
                        logger.error("ERROR: OppdateringAvJournalposter_UPDATE - oppdaterMottattDato - Error handling request", e);
                        return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "OppdateringAvJournalposter_UPDATE - oppdaterMottattDato - En uventet feil oppstod, vennligst prøv igjen senere.", e));

                    }
                });

    }
}

