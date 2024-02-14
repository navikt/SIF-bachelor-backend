package com.bachelor.vju_vm_apla2.Service;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SimpleService {

    private final WebClient webClient;

    //setter opp HTTP syntax slik at vi kan gjøre kall på GraphQL server (erstattet med Wiremock)
    public SimpleService() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8081")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


    /*
    public SimpleService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build(); // Anta at WireMock kjører på port 8081
    }
     */


////////////////////////////////////////////HOVED METODER///////////////////////////////////////////////////////////////////////////////

    //tar innkomende data fra JournalPostController og parser dette til webclient object
    //Gjør HTTP kall gjennom WebClient Objekt med GraphQL server (erstattet med Wiremock)
    public String handleJournalPostData(String journalpostData, HttpHeaders originalHeader){
        String response = this.webClient.post()
                .uri("/mock-journalpost")
                .headers(headers -> headers.addAll(originalHeader))
                .bodyValue(journalpostData)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Bruk block() for å utføre kallet synkront, fjern for asynkron bruk

        System.out.println("Respons fra wiremock: " + response);

        return response; //returnrer data tilbake til kontroller.
    }

    public String hentJournalpostListe(String query,HttpHeaders orignalHeader ){

        String response = this.webClient.post()
                .uri("/mock-journalpost")
                .headers(headers -> headers.addAll(orignalHeader))
                .bodyValue(query)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Bruk block() for å utføre kallet synkront, fjern for asynkron bruk

        System.out.println("Respons fra wiremock: " + response);

        return response; //returnrer data tilbake til kontroller.

    }


////////////////////////////////////////////////////// TEST METODER /////////////////////////////////////////////////////////////////////


    //PDF TEST
    /*
    public Flux<DataBuffer> fetchPdfContent() {
        return this.webClient.get()
                .uri("/getpdf") // Endre URI etter behov
                .retrieve()
                .bodyToFlux(DataBuffer.class); // Hent PDF som en flux av DataBuffer
    }
     */

    //pdf Test med størrelse
    public Flux<DataBuffer> fetchPdfContent() {
        AtomicLong totalSize = new AtomicLong(0); // For å holde styr på den totale størrelsen

        return this.webClient.get()
                .uri("/getpdf") // Endre URI etter behov
                .retrieve()
                .bodyToFlux(DataBuffer.class) // Hent PDF som en flux av DataBuffer
                .map(dataBuffer -> {
                    long currentBufferLength = dataBuffer.readableByteCount();
                    totalSize.addAndGet(currentBufferLength); // Legger til størrelsen av den nåværende bufferen til totalen
                    System.out.println("Service - Mottatt buffer med størrelse: " + currentBufferLength + " bytes");
                    return dataBuffer;
                })
                .doOnComplete(() -> {
                    // Logg den totale størrelsen når alle data er mottatt
                    System.out.println("Service - Total størrelse på mottatt fil: " + totalSize.get() + " bytes");
                });
    }
}
