package com.bachelor.vju_vm_apla2.Controller;

import com.bachelor.vju_vm_apla2.Models.DTO.FraGrapQl_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.FraKlient_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.FraKlient_DTO_test;
import com.bachelor.vju_vm_apla2.Service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import no.nav.security.token.support.core.api.Protected;
import no.nav.security.token.support.core.api.Unprotected;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.apache.logging.log4j.Logger;
import java.nio.charset.StandardCharsets;

import java.sql.SQLOutput;
import org.apache.logging.log4j.LogManager;


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
    @CrossOrigin(origins = "http://localhost:3000") // Tillater CORS-forespørsler fra React-appen
    @PostMapping("/hentJournalpostListe")
    public Mono<ResponseEntity<FraGrapQl_DTO>> hentJournalpostListe(@RequestBody FraKlient_DTO query, @RequestHeader HttpHeaders headers) {
        System.out.println("Kontroller - Mottatt query: " + query +
                "\n" + "Kontroller - Mottatt headers: " + headers);
        System.out.println();
        return simpleService.hentJournalpostListe(query, headers)
                .map(response -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                        .body(response))
                .defaultIfEmpty(ResponseEntity.notFound().build())
                /* Added error handling below, which triggers if something wrong happens during processing of the stream, which
                   represents fetching the journalpost metadata. If that happens, then we will call the errorResume. This will
                   then return an INTERNAL SERVER ERROR. Why use this? Try catch is blocking, whilst this method doesn't block the
                   main thread. */
                .onErrorResume(e -> {
                    String errorMessage = "Error trying to fetch journalpost metadata in the controller ";
                    logger.error(errorMessage, e);

                    return Mono.just(
                            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new FraGrapQl_DTO(errorMessage, e.getMessage()))
                    );
                });
    }

    //Metode for å hente dokumentID basert på response fra SAF - graphql
    //Denne metoden innholder ikke mulighet til å legge til journalpostID enda i URL. Vi søker dokumenter for journalostID 001
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/hentDokumenter")
    public Mono<ResponseEntity<Resource>> hentDokument(@RequestParam("dokumentInfoId") String dokumentInfoId, @RequestHeader HttpHeaders headers) {
        System.out.println("Kontroller - Mottatt query: " + dokumentInfoId +
                "\n" + "Nå går vi inn i service klassen");
        return simpleService.hentDokument(dokumentInfoId, headers)
                .map(pdfResource ->
                        ResponseEntity.ok()
                                .contentType(MediaType.APPLICATION_PDF)
                                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"document.pdf\"")
                                .body(pdfResource))
                // Error handling below
                .onErrorResume(e -> {
                    // Log the exception for debugging purposes
                    String errorMessage = "Error in retrieving the document with document info id: " + dokumentInfoId;
                    logger.error(errorMessage, e);

                    ByteArrayResource errorResource = new ByteArrayResource(errorMessage.getBytes(StandardCharsets.UTF_8));
                    return Mono.just(
                            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .contentType(MediaType.TEXT_PLAIN)
                                    .body(errorResource)
                    );
                });

    }

    //////////////////////////////////////////////////////////////// PROTECTED API TEST ENDPOINTS///////////////////////////////////////////

    @CrossOrigin(origins = "http://localhost:3000")
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



