package com.bachelor.vju_vm_apla2.Controller;

import com.bachelor.vju_vm_apla2.ErrorHandling.ErrorHandling;
import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.CreateJournalpost_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.Request.PostOppdaterJournalpost_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.ResponeReturnFromDokArkiv_DTO;
import com.bachelor.vju_vm_apla2.Service.DokArkiv_Service.FeilRegistrer_DELETE;
import com.bachelor.vju_vm_apla2.Service.DokArkiv_Service.OpprettNyeJournalposter_CREATE;
import com.bachelor.vju_vm_apla2.Service.DokArkiv_Service.OppdateringAvJournalposter_UPDATE;
import no.nav.security.token.support.core.api.Protected;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Protected
@RestController
public class DokArkivController {

    private static final Logger logger = LogManager.getLogger(DokArkivController.class);

    private final OpprettNyeJournalposter_CREATE opprettNyeJournalposter_CREATE;
    private final OppdateringAvJournalposter_UPDATE oppdaterJournalposter_UPDATE;
    private final FeilRegistrer_DELETE feilRegistrerService;

    @Autowired
    public DokArkivController(OpprettNyeJournalposter_CREATE opprettNyeJournalposterCREATE, FeilRegistrer_DELETE feilRegistrerService, OppdateringAvJournalposter_UPDATE oppdaterJournalposter_UPDATE){
        this.opprettNyeJournalposter_CREATE = opprettNyeJournalposterCREATE;
        this.feilRegistrerService = feilRegistrerService;
        this.oppdaterJournalposter_UPDATE = oppdaterJournalposter_UPDATE;
    }



    @CrossOrigin
    @PostMapping("/createJournalpost")
    public Mono<ResponseEntity<List<ResponeReturnFromDokArkiv_DTO>>> createJournalpost_Controller(@RequestBody CreateJournalpost_DTO meta, @RequestHeader HttpHeaders headers) {
        logger.info("Controller - Nå går vi inn i createjournalpost med " + meta);
        return opprettNyeJournalposter_CREATE.createJournalpost_Service(meta, headers)
                .flatMap(response -> feilRegistrerService.feilRegistrer_Service(meta.getJournalpostID(), meta.getOldMetadata().getJournalposttype(), headers)
                        .flatMap(feilRegistrerResponse -> {
                            return Mono.just(ResponseEntity.ok().body(response.getBody()));
                        })
                        .defaultIfEmpty(ResponseEntity.notFound().build())
                        .onErrorResume(ErrorHandling::handleError))
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(ErrorHandling::handleError)
                .doOnSuccess(response -> logger.info("DokArkivController - createJournalpost() - Success - Response sent to client: {}", response.getStatusCode()));

    }


    @CrossOrigin
    @GetMapping("/feilregistrer")
    public Mono<ResponseEntity<Boolean>> feilregistrer_Controller(@RequestParam("journalpostId") String journalpostId, @RequestParam("type") String type, @RequestHeader HttpHeaders headers){
        return feilRegistrerService.feilRegistrer_Service(journalpostId, type, headers)
                .onErrorResume(e -> {
                    logger.error("ERROR: DokArkivController - feilregister() - Fail -  Feil ved feilregistrering: {}", e.getMessage());
                    return ErrorHandling.handleError(e);
                })
                .doOnSuccess(response -> logger.info("INFO: DokArkivController - feilregister() -  Success - Response sent to client: {}", response.getStatusCode()));
    }

    @CrossOrigin
    @PostMapping("/oppdaterJournalpost")
    public Mono<ResponseEntity<Boolean>> oppdaterJournalpost(@RequestBody PostOppdaterJournalpost_DTO meta, @RequestHeader HttpHeaders headers){
        return oppdaterJournalposter_UPDATE.oppdaterMottattDato(meta, headers)
                .onErrorResume(e -> {
                    logger.error("Feil ved oppdatering av Journalpost: {}", e.getMessage());
                    return ErrorHandling.handleError(e);
                })
                .doOnSuccess(response -> logger.info("Response sent to client: {}", response.getStatusCode()));
    }

}
