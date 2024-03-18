package com.bachelor.vju_vm_apla2.Service;

import com.bachelor.vju_vm_apla2.Models.DTO.FraGrapQl_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.FraKlient_DTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class SimpleService {
    private static final Logger logger = LogManager.getLogger(SimpleService.class);
    private final WebClient webClient;

    //setter opp HTTP syntax slik at vi kan gjøre kall på serverere (Serevere er erstattet med Wiremock)
    public SimpleService() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }

////////////////////////////////////////////HOVED METODER///////////////////////////////////////////////////////////////////////////////



    //tar innkomende data fra JournalPostController og parser dette til webclient object
    //Gjør HTTP kall gjennom WebClient Objekt med GraphQL server (erstattet med Wiremock)
    public Mono<FraGrapQl_DTO> hentJournalpostListe(FraKlient_DTO query, HttpHeaders originalHeader) {
        System.out.println("Service - hentjournalpostListe_1: vi skal nå inn i wiremock med forespørsel: " + query);
        return this.webClient.post()
                .uri("/mock/graphql")
                .headers(headers -> headers.addAll(originalHeader))
                .bodyValue(query)
                .retrieve()
                .bodyToMono(FraGrapQl_DTO.class)
                .doOnNext(response -> System.out.println("Service - hentJournalpostListe - gir response fra wiremock til kontroller: " + response))
                // Exception for when
                .onErrorResume(e -> {
                    String errorMessage = "An error occurred trying to retrieve the journalpost metadata at SAF in the service layer hentJournalpostListe method. ";
                    String errorMessageForClient = "SAF API error in retrieving the metadata, please try again later.";
                    logger.error(errorMessage, e);
                    // Return error DTO
                    FraGrapQl_DTO errorDto = new FraGrapQl_DTO();
                    errorDto.setErrorMessage(errorMessageForClient);

                    return Mono.just(errorDto); // Return a Mono containing the error DTO
                });
    }


    //Metode for å gjøre kall mot Rest-SAF for å hente indivduelle dokuemnter for journalpostId "001"
    //Metoden tar i mot bare dokumentID. Det skal endres til at den også tar i mot journalpostID
    public Mono<Resource> hentDokument(String dokumentInfoId, HttpHeaders originalHeader) {
        System.out.println("Vi er inne i service og har hentent dokumentID " + dokumentInfoId);
        String url = "/mock/rest/hentdokument/journalpostid/" + dokumentInfoId;

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(byte[].class) // Konverter responsen til en byte array
                .map(ByteArrayResource::new) // Konverter byte array til en ByteArrayResource
                .cast(Resource.class) // Cast the ByteArrayResource to Resource
                .onErrorResume(e -> {
                    // Log the exception for debugging purposes
                    String errorMessage = "Error in retrieving the document with document info id: " + dokumentInfoId;
                    String errorMessageForClient = "SAF API error in retrieving the documents, please try again later.";
                    logger.error(errorMessage, e);

                    // The errorMessageForClient is sanitized to escape any double quotes to prevent breaking the JSON format.
                    String jsonErrorMessage = "{\"errorMessage\": \"" + errorMessageForClient.replace("\"", "\\\"") + "\"}";
                    // Create a ByteArrayResource containing the error message
                    ByteArrayResource errorResource = new ByteArrayResource(jsonErrorMessage.getBytes(StandardCharsets.UTF_8));

                    // Return the ByteArrayResource wrapped in a Mono to match the expected return type
                    return Mono.just(errorResource);
                });
    }



    private String createGraphQLQuery(FraKlient_DTO query) {
        // Formatter for å konvertere datoer til ønsket format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Konverterer fraDato og tilDato fra ISO 8601 String til LocalDate via Instant, og deretter tilbake til formatert String
        String formattedFraDato = query.getFraDato() != null ? LocalDate.ofInstant(Instant.parse(query.getFraDato()), ZoneId.systemDefault()).format(formatter) : "null";
        String formattedTilDato = query.getTilDato() != null ? LocalDate.ofInstant(Instant.parse(query.getTilDato()), ZoneId.systemDefault()).format(formatter) : "null";

        // Bygger GraphQL-forespørselen med de formaterte datoverdiene
        String graphQLQuery = String.format("""
            query {
              dokumentoversiktBruker(
                brukerId: { id: "%s", type: "%s" }
                fraDato: "%s"
                tilDato: "%s"
                journalposttyper: %s
                journalstatuser: %s
                tema: %s
              ) {
                journalposter {
                    journalpostId
                    tittel
                    journalposttype
                    journalstatus
                    tema
                  dokumenter {
                    tittel
                    dokumentInfoId
                  }
                }
              }
            }""", query.getBrukerId().getId(), query.getBrukerId().getType(),
                formattedFraDato, formattedTilDato,
                query.getJournalposttyper().toString(),
                query.getJournalstatuser().toString(),
                query.getTema().toString());

        return graphQLQuery;
    }




////////////////////////////////////////////////////// TEST METODER /////////////////////////////////////////////////////////////////////


}