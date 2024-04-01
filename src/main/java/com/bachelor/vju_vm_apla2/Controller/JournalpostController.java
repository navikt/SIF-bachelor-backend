package com.bachelor.vju_vm_apla2.Controller;

import com.bachelor.vju_vm_apla2.Config.CustomClientException;
import com.bachelor.vju_vm_apla2.Models.DTO.FraGrapQl_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.FraKlient_DTO;
import com.bachelor.vju_vm_apla2.Service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import no.nav.security.token.support.core.api.Protected;
import no.nav.security.token.support.core.api.Unprotected;
import reactor.core.publisher.Mono;
import org.apache.logging.log4j.Logger;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import wiremock.org.checkerframework.common.returnsreceiver.qual.This;


/* By using the @Protected annotation, we are securing access to this class, which was already configured in our
   SecurityConfig class with the @EnableJwtTokenValidation annotation. If the class doesn't need to be protected
   by checking requests for valid JWT token, we can use @Unprotected s */
@Protected
@RestController
public class JournalpostController {

    private final SimpleService simpleService;


    private static final Logger logger = LogManager.getLogger(JournalpostController.class);

    @Autowired
    public JournalpostController(SimpleService simpleService) {

        this.simpleService = simpleService;
    }


    //////////////////////////////////////////////////// HOVED METODER ///////////////////////////////////////////////////////////////////////

    //Søker på BrukerID og skal returnere en liste med journalposter

    //POST API, leverer liste med journalposter basert på query(uten filter) fra klienten. Henter liste fra Service klasse

    @CrossOrigin// Allows CORS communication from the frontend, if you want to add extra, you can do that in CorsConfig
    @PostMapping("/hentJournalpostListe")
    public Mono<ResponseEntity<FraGrapQl_DTO>> hentJournalpostListe(@RequestBody FraKlient_DTO query, @RequestHeader HttpHeaders headers) {
        System.out.println("Kontroller - Mottatt query: " + query +
                "\n" + "Kontroller - Mottatt headers: " + headers);
        return simpleService.hentJournalpostListe_Test_ENVIRONMENT(query, headers)
                .map(response -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                        .body(response))
                .onErrorResume(e -> {
                    if (e instanceof CustomClientException) {
                        System.out.println("Vi er inne i kontroller-klassen som skal gi spesikk error kode:");
                        // 2. Håndtere statusfeil fra service
                        CustomClientException cce = (CustomClientException) e;
                        return Mono.just(ResponseEntity
                                .status(cce.getStatusCode())
                                .body(new FraGrapQl_DTO(cce.getMessage())));
                    } else {
                        // 3. Generell feilhåndtering
                        System.out.println("Vi er inne i kontroller-klassen som skal gi Generisk feil:");
                        return Mono.just(ResponseEntity
                                .internalServerError()
                                .body(new FraGrapQl_DTO("En uventet feil oppstod, vennligst prøv igjen senere.")));
                    }
                });
    }
//   public String allowed = URL + ":" + PORT;

    /*
    //Metode for å hente dokumentID basert på response fra SAF - graphql s
    //Denne metoden innholder ikke mulighet til å legge til journalpostID enda i URL. Vi søker dokumenter for journalostID 001
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/hentDokumenter")
    public Mono<ResponseEntity<Resource>> hentDokument(@RequestParam("dokumentInfoId") String dokumentInfoId,@RequestParam("journalpostId") String journalpostId, @RequestHeader HttpHeaders headers) {
        System.out.println("Kontroller - Mottatt query: " + dokumentInfoId +
                "\n" + "Nå går vi inn i service klassen");
        return simpleService.hentDokument(dokumentInfoId, journalpostId,  headers)
                .map(pdfResource ->
                        ResponseEntity.ok()
                                .contentType(MediaType.APPLICATION_PDF)
                                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"document.pdf\"")
                                .body(pdfResource))
                .onErrorResume(e -> {
                    if (e instanceof CustomClientException) {
                        CustomClientException cce = (CustomClientException) e;
                        System.out.println("Kontroller - Spesifikk feil ved henting av dokument: " + cce.getMessage());
                        // Oppretter en ResponseEntity med statuskode og feilmelding fra CustomClientException
                        return Mono.just(ResponseEntity
                                .status(cce.getStatusCode())
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(new ByteArrayResource(cce.getMessage().getBytes())));
                    } else {
                        System.out.println("Kontroller - Generell feil ved henting av dokument");
                        String errorMessageForClient = "A server error has occurred in retrieving the expected documents, please try again later.";
                        // Sanitize the errorMessageForClient to escape any double quotes
                        String jsonErrorMessage = "{\"errorMessage\": \"" + errorMessageForClient.replace("\"", "\\\"") + "\"}";
                        ByteArrayResource errorResource = new ByteArrayResource(jsonErrorMessage.getBytes(StandardCharsets.UTF_8));
                        return Mono.just(
                                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(errorResource)
                        );
                    }
                });
    }

     */

    ////////////////////////////////////////////TEST ENVIRONMENT METODER///////////////////////////////////////////////////////////////////////////////

    //Metode for å hente dokumentID basert på response fra SAF - graphql s
    //Denne metoden innholder ikke mulighet til å legge til journalpostID enda i URL. Vi søker dokumenter for journalostID 001
    @CrossOrigin
    @GetMapping("/hentDokumenter")
    public Mono<ResponseEntity<Resource>> hentDokument(@RequestParam("dokumentInfoId") String dokumentInfoId, @RequestHeader HttpHeaders headers) {
        System.out.println("Kontroller - Mottatt query: " + dokumentInfoId +
                "\n" + "Nå går vi inn i service klassen");
        return simpleService.hentDokument_Test_ENVIRONMENT(dokumentInfoId, headers)
                .map(pdfResource ->
                        ResponseEntity.ok()
                                .contentType(MediaType.APPLICATION_PDF)
                                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"document.pdf\"")
                                .body(pdfResource))
                .onErrorResume(e -> {
                    if (e instanceof CustomClientException) {
                        CustomClientException cce = (CustomClientException) e;
                        System.out.println("Kontroller - Spesifikk feil ved henting av dokument: " + cce.getMessage());
                        // Oppretter en ResponseEntity med statuskode og feilmelding fra CustomClientException
                        return Mono.just(ResponseEntity
                                .status(cce.getStatusCode())
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(new ByteArrayResource(cce.getMessage().getBytes())));
                    } else {
                        System.out.println("Kontroller - Generell feil ved henting av dokument");
                        String errorMessageForClient = "A server error has occurred in retrieving the expected documents, please try again later.";
                        // Sanitize the errorMessageForClient to escape any double quotes
                        String jsonErrorMessage = "{\"errorMessage\": \"" + errorMessageForClient.replace("\"", "\\\"") + "\"}";
                        ByteArrayResource errorResource = new ByteArrayResource(jsonErrorMessage.getBytes(StandardCharsets.UTF_8));
                        return Mono.just(
                                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(errorResource)
                        );
                    }
                });
    }


    //////////////////////////////////////////////////////////////// PROTECTED API TEST ENDPOINTS///////////////////////////////////////////
    @CrossOrigin
    @GetMapping("/test/protected")
    public String protectedPath() {
        return "I am protected";
    }

    @Unprotected
    @GetMapping("/test/unprotected")
    public String unProtectedPath() {
        return "I am unprotected";
    }


    //////////////////////////////////////////////////////////////// ARKIVERTE METODER ////////////////////////////////////////////////////////////////

}



