package com.bachelor.vju_vm_apla2.Service;

import ch.qos.logback.core.net.server.Client;
import com.bachelor.vju_vm_apla2.Config.CustomClientException;
import com.bachelor.vju_vm_apla2.Models.DTO.FraGrapQl_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.FraKlient_DTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.web.client.RestClient.Builder;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class SimpleService {
    private static final Logger logger = LogManager.getLogger(SimpleService.class);
    //private final WebClient webClient;
    private Builder builder;
    @Value("${db.Service}")
    private String url;
    //setter opp HTTP syntax slik at vi kan gjøre kall på serverere (Serevere er erstattet med Wiremock)
    //TODO: work with clientauth
   /* public SimpleService() {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }*/
    private WebClient webclient = (WebClient) this.builder.baseUrl(url).build();


////////////////////////////////////////////REAL ENVIRONMENT METODER///////////////////////////////////////////////////////////////////////////////

    //tar innkomende data fra JournalPostController og parser dette til webclient object
    //Gjør HTTP kall gjennom WebClient Objekt med GraphQL server (erstattet med Wiremock)
    public Mono<FraGrapQl_DTO> hentJournalpostListe(FraKlient_DTO query, HttpHeaders originalHeader) {
        String graphQLQuery = createGraphQLQuery(query); // Generer GraphQL-forespørselen
        System.out.println("Service - hentjournalpostListe_1: vi skal nå inn i wiremock med forespørsel: " + graphQLQuery);
        return webclient.post()
                .uri(url+"/graphql")
                .headers(headers -> headers.addAll(originalHeader))
                .bodyValue(graphQLQuery) // Genererte GraphQL-forespørselen
                .retrieve()
                .onStatus(status -> status.isError(), clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            System.out.println("Vi er inne i Servoce-klassen som skal gi spesikk error kode:");
                            int statusValue = clientResponse.statusCode().value();
                            String errorMessage = "Feil ved kall til ekstern tjeneste: " + statusValue + " - " + errorBody;
                            return Mono.error(new CustomClientException(statusValue, errorMessage));
                        }))
                .bodyToMono(FraGrapQl_DTO.class)
                .onErrorResume(e -> {
                    // Håndter generelle feil som ikke er knyttet til HTTP-statuskoder
                    if (!(e instanceof CustomClientException)) {
                        System.out.println("Vi er inne i Servoce-klassen som skal gi GENERISK error kode:");
                        // Logg feilen og returner en generisk feilrespons
                        logger.error("En uventet feil oppstod: ", e);
                        return Mono.just(new FraGrapQl_DTO("En uventet feil oppstod, vennligst prøv igjen senere."));
                    }
                    // Viderefør CustomClientException slik at den kan håndteres oppstrøms
                    return Mono.error(e);
                });
    }

    //tar innkomende data fra JournalPostController og parser dette til webclient object
    //Gjør HTTP kall gjennom WebClient Objekt med GraphQL server (erstattet med Wiremock)
    public Mono<FraGrapQl_DTO> hentJournalpostListe_Test_ENVIRONMENT(FraKlient_DTO query, HttpHeaders headers) {
        return webclient.post()
                .uri(url+"/graphql")
                .headers(h -> h.addAll(headers))
                .bodyValue(query)
                .retrieve()
                .onStatus(status -> status.isError(), clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            System.out.println("Vi er inne i Servoce-klassen som skal gi spesikk error kode:");
                            int statusValue = clientResponse.statusCode().value();
                            String errorMessage = "Feil ved kall til ekstern tjeneste: " + statusValue + " - " + errorBody;
                            return Mono.error(new CustomClientException(statusValue, errorMessage));
                        }))
                .bodyToMono(FraGrapQl_DTO.class)
                .onErrorResume(e -> {
                    // Håndter generelle feil som ikke er knyttet til HTTP-statuskoder
                    if (!(e instanceof CustomClientException)) {
                        System.out.println("Vi er inne i Servoce-klassen som skal gi GENERISK error kode:");
                        // Logg feilen og returner en generisk feilrespons
                        logger.error("En uventet feil oppstod: ", e);
                        return Mono.just(new FraGrapQl_DTO("En uventet feil oppstod, vennligst prøv igjen senere."));
                    }
                    // Viderefør CustomClientException slik at den kan håndteres oppstrøms
                    return Mono.error(e);
                });
    }


    //Bygger en GraphQL-forespørsel som en streng basert på input-data fra klienten
    private String createGraphQLQuery(FraKlient_DTO query) {
        // Formatter for å konvertere datoer til ønsket format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Konverterer fraDato og tilDato fra ISO 8601 String til LocalDate via Instant, og deretter tilbake til formatert String
        String formattedFraDato = query.getFraDato() != null ? LocalDate.ofInstant(Instant.parse(query.getFraDato()), ZoneId.systemDefault()).format(formatter) : "null";
        String formattedTilDato = query.getTilDato() != null ? LocalDate.ofInstant(Instant.parse(query.getTilDato()), ZoneId.systemDefault()).format(formatter) : "null";

        // Bygger GraphQL-forespørselen med de formaterte datoverdiene

        return String.format(""" 
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
                    originalJournalpostId
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
    }

    //Metode for å gjøre kall mot Rest-SAF for å hente indivduelle dokuemnter for journalpostId "001"
    //Metoden tar i mot bare dokumentID. Det skal endres til at den også tar i mot journalpostID
    public Mono<Resource> hentDokument(String dokumentInfoId, String journalpostId, HttpHeaders originalHeader) {
        System.out.println("Vi er inne i service og har hentent dokumentID " + dokumentInfoId);
        String endpoint = "/rest/hentdokument/"+journalpostId+"/" + dokumentInfoId;

        return webclient.get()
                .uri(url+endpoint)
                .headers(h -> h.addAll(originalHeader))
                .retrieve()
                .onStatus(status -> status.isError(), clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            System.out.println("Vi er inne i Service-klassen som skal gi spesifikk error kode:");
                            int statusValue = clientResponse.statusCode().value();
                            String errorMessage = "Feil ved kall til ekstern tjeneste: " + "endpoint" + statusValue+ " - " + errorBody;
                            return Mono.error(new CustomClientException(statusValue, errorMessage));
                        }))
                .bodyToMono(byte[].class) // Konverter responsen til en byte array
                .map(ByteArrayResource::new) // Konverter byte array til en ByteArrayResource
                .cast(Resource.class) // Cast the ByteArrayResource to Resource
                .onErrorResume(e -> {
                    if (!(e instanceof CustomClientException)) {
                        System.out.println("Vi er inne i Service-klassen som skal gi GENERISK error kode:");
                        // Logg feilen og returner en generisk feilrespons
                        logger.error("En uventet feil oppstod: ", e);
                        String errorMessageForClient = "SAF API error in retrieving the documents, please try again later.";
                        String jsonErrorMessage = "{\"errorMessage\": \"" + errorMessageForClient.replace("\"", "\\\"") + "\"}";
                        ByteArrayResource errorResource = new ByteArrayResource(jsonErrorMessage.getBytes(StandardCharsets.UTF_8));
                        return Mono.just(errorResource);
                    }
                    // Viderefør CustomClientException slik at den kan håndteres oppstrøms
                    return Mono.error(e);
                });
    }




////////////////////////////////////////////TEST ENVIRONMENT METODER///////////////////////////////////////////////////////////////////////////////



    //Metode for å gjøre kall mot Rest-SAF for å hente indivduelle dokuemnter for journalpostId "001"
    //Metoden tar i mot bare dokumentID. Det skal endres til at den også tar i mot journalpostID
    public Mono<Resource> hentDokument_Test_ENVIRONMENT(String dokumentInfoId, HttpHeaders originalHeader) {
        System.out.println("Vi er inne i service og har hentet dokumentID " + dokumentInfoId);
        String endpoint = "/rest/hentdokument/journalpostid/" + dokumentInfoId;

        return webclient.get()
                .uri(url+endpoint)
                .headers(h -> h.addAll(originalHeader))
                .retrieve()
                .onStatus(status -> status.isError(), clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            System.out.println("Vi er inne i Service-klassen som skal gi spesifikk error kode:");
                            int statusValue = clientResponse.statusCode().value();
                            String errorMessage = "Feil ved kall til ekstern tjeneste: " + statusValue + " - " + errorBody;
                            return Mono.error(new CustomClientException(statusValue, errorMessage));
                        }))
                .bodyToMono(byte[].class) // Konverter responsen til en byte array
                .map(ByteArrayResource::new) // Konverter byte array til en ByteArrayResource
                .cast(Resource.class) // Cast the ByteArrayResource to Resource
                .onErrorResume(e -> {
                    if (!(e instanceof CustomClientException)) {
                        System.out.println("Vi er inne i Service-klassen som skal gi GENERISK error kode:");
                        // Logg feilen og returner en generisk feilrespons
                        logger.error("En uventet feil oppstod: ", e);
                        String errorMessageForClient = "SAF API error in retrieving the documents, please try again later.";
                        String jsonErrorMessage = "{\"errorMessage\": \"" + errorMessageForClient.replace("\"", "\\\"") + "\"}";
                        ByteArrayResource errorResource = new ByteArrayResource(jsonErrorMessage.getBytes(StandardCharsets.UTF_8));
                        return Mono.just(errorResource);
                    }
                    // Viderefør CustomClientException slik at den kan håndteres oppstrøms
                    return Mono.error(e);
                });
    }

////////////////////////////////////////////////////// TEST METODER /////////////////////////////////////////////////////////////////////

}