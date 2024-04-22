package com.bachelor.vju_vm_apla2.Controller;

import com.bachelor.vju_vm_apla2.Models.DTO.Saf.GetJournalpostList_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.Saf.ReturnFromGraphQl_DTO;
import com.bachelor.vju_vm_apla2.Service.DokArkiv_Service.OpprettNyeJournalposter_CREATE;
import com.bachelor.vju_vm_apla2.Service.SimpleService;
import com.bachelor.vju_vm_apla2.Config.ErrorHandling;
import no.nav.security.token.support.core.api.Protected;
import no.nav.security.token.support.core.api.Unprotected;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Protected
@RestController
public class SafController {
    private final SimpleService simpleService;
    private final OpprettNyeJournalposter_CREATE dokService;
    @Autowired
    public SafController(SimpleService simpleService, OpprettNyeJournalposter_CREATE dokService) {
        this.simpleService = simpleService;
        this.dokService = dokService;
    }

    // Logger-instansen for å logge informasjon og feil.
    private static final Logger logger = LogManager.getLogger(SafController.class);

    /**
     * Henter en liste over journalposter basert på innsendte søkekriterier.
     * Bruker RequestBody for å ta imot søkedata fra klienten og HttpHeaders for å behandle HTTP-headerinformasjon.
     * Returnerer en liste med journalposter pakket i en ResponseEntity med JSON-innholdstype.
     */


    @CrossOrigin
    @Unprotected
    @GetMapping("/hello")
    public Mono<ResponseEntity<String>> hello() {
        return dokService.fetchHello()
                .map(response -> {
                logger.info("HEI PA DEG");
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                        .body(response);
                })
                    .defaultIfEmpty(ResponseEntity.notFound().build())
                    .onErrorResume(e -> {
                        logger.error("Feil ved henting av journalposter: {}", e.getMessage());
                        return ErrorHandling.handleError(e);
                    });
    }
    @CrossOrigin
    @PostMapping("/hentJournalpostListe")
    public Mono<ResponseEntity<ReturnFromGraphQl_DTO>> hentJournalpostListe(@RequestBody GetJournalpostList_DTO query, @RequestHeader HttpHeaders headers) {
        logger.info("Inne i metoden hentJournalpostListe med data: {}", query);
        return simpleService.hentJournalpostListe_Test_ENVIRONMENT(query, headers)
                .map(response -> {
                    logger.info("Journalposter hentet og sendes tilbake til klienten");
                    return ResponseEntity.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                            .body(response);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(e -> {
                    logger.error("Feil ved henting av journalposter: {}", e.getMessage());
                    return ErrorHandling.handleError(e);
                });
    }

    /**
     * Henter et spesifikt dokument basert på dokumentInfoId og journalpostId.
     * Bruker RequestParam for å motta disse identifikatorene og HttpHeaders for å behandle HTTP-headerinformasjon.
     * Returnerer dokumentet som en PDF i en ResponseEntity.
     */
    @CrossOrigin
    @GetMapping("/hentDokumenter")
    public Mono<ResponseEntity<Resource>> hentDokument(@RequestParam("dokumentInfoId") String dokumentInfoId, @RequestParam("journalpostId") String journalpostId, @RequestHeader HttpHeaders headers) {
        logger.info("Inne i metoden hentDokument for dokumentInfoId: {}, journalpostId: {}", dokumentInfoId, journalpostId);
        return simpleService.hentDokument(dokumentInfoId, journalpostId, headers)
                .map(pdfResource -> {
                    logger.info("Dokument hentet og sendes tilbake til klienten");
                    return ResponseEntity.ok()
                            .contentType(MediaType.APPLICATION_PDF)
                            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"document.pdf\"")
                            .body(pdfResource);
                })
                .onErrorResume(e -> {
                    logger.error("Feil ved henting av dokument: {}", e.getMessage());
                    return ErrorHandling.handleError(e);
                });
    }
}





