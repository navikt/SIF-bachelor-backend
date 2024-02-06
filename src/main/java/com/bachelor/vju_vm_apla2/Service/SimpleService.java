package com.bachelor.vju_vm_apla2.Service;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;

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

    //tar innkomende data fra JournalPostController og parser dette til webclient object
    //Gjør HTTP kall gjennom WebClient Objekt med GraphQL server (erstattet med Wiremock)
    public String handleJournalPostData(String journalpostData, HttpHeaders originalHeader){

        String response = this.webClient.post()
                .uri("/journalpost")
                .headers(headers -> headers.addAll(originalHeader))
                .bodyValue(journalpostData)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Bruk block() for å utføre kallet synkront, fjern for asynkron bruk

        System.out.println("Respons fra wiremock: " + response);

        return response; //returnrer data tilbake til kontroller.

    }
}
