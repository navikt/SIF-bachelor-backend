package com.bachelor.vju_vm_apla2.Service;

//import com.bachelor.vju_vm_apla2.Models.DTO.FraKlient_DTO_test;
import com.bachelor.vju_vm_apla2.Models.DTO.FraGrapQl_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.FraKlient_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.FraKlient_DTO_test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SimpleService {

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
        System.out.println("Service - hentjournalpostListe_1: vi skal nå inn i wiremock");
        return this.webClient.post()
                .uri("/mock/graphql")
                .headers(headers -> headers.addAll(originalHeader))
                .bodyValue(query)
                .retrieve()
                .bodyToMono(FraGrapQl_DTO.class)
                .doOnNext(response -> System.out.println("Service - hentJournalpostListe - gir response fra wiremock til kontroller: " + response));


    }


    //Metode for å gjøre kall mot Rest-SAF for å hente indivduelle dokuemnter for journalpostId "001"
    //Metoden tar i mot bare dokumentID. Det skal endres til at den også tar i mot journalpostID
    public Mono<Resource> hentDokument(String dokumentInfoId, HttpHeaders originalHeader) {
        System.out.println("Vi er inne i service og har hentent dokumentID " + dokumentInfoId);
        String url = "/mock/rest/hentdokument/001/" + dokumentInfoId;

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(byte[].class) // Konverter responsen til en byte array
                .map(ByteArrayResource::new); // Konverter byte array til en ByteArrayResource
    }


////////////////////////////////////////////////////// TEST METODER /////////////////////////////////////////////////////////////////////


}