package com.bachelor.vju_vm_apla2.Controller;

import com.bachelor.vju_vm_apla2.Config.ErrorHandling;
import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.CreateJournalpost_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.ResponeReturnFromDokArkiv_DTO;
import com.bachelor.vju_vm_apla2.Service.DokArkiv_Service.FeilRegistrer_DELETE;
import com.bachelor.vju_vm_apla2.Service.DokArkiv_Service.OpprettNyeJournalposter_CREATE;
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
    private final FeilRegistrer_DELETE feilRegistrerService;

    @Autowired
    public DokArkivController(OpprettNyeJournalposter_CREATE opprettNyeJournalposter_CREATE, FeilRegistrer_DELETE feilRegistrerService){
        this.opprettNyeJournalposter_CREATE = opprettNyeJournalposter_CREATE;
        this.feilRegistrerService = feilRegistrerService;
    }


    @CrossOrigin
    @PostMapping("/createJournalpost")
    public Mono<ResponseEntity<List<ResponeReturnFromDokArkiv_DTO>>> createJournalpost(@RequestBody CreateJournalpost_DTO meta, @RequestHeader HttpHeaders headers) {
        logger.info("Controller - Nå går vi inn i createjournalpost med " + meta);
        return opprettNyeJournalposter_CREATE.createJournalpost(meta, headers)
                .flatMap(response -> feilRegistrerService.feilRegistrer(meta.getJournalpostID(), meta.getOldMetadata().getJournalposttype(), headers)
                        .flatMap(feilRegistrerResponse -> {
                            return Mono.just(ResponseEntity.ok().body(response.getBody()));
                        })
                        .defaultIfEmpty(ResponseEntity.notFound().build())  // Hvis ingen respons fra createJournalpost
                        .onErrorResume(ErrorHandling::handleError))
                .defaultIfEmpty(ResponseEntity.notFound().build())  // Hvis ingen respons fra createJournalpost
                .onErrorResume(ErrorHandling::handleError)
                .doOnSuccess(response -> logger.info("DokArkivController - createJournalpost() - Success - Response sent to client: {}", response.getStatusCode()));

    }



    @CrossOrigin
    @GetMapping("/feilregistrer")
    public Mono<ResponseEntity<Boolean>> feilregistrer(@RequestParam("journalpostId") String journalpostId, @RequestParam("type") String type, @RequestHeader HttpHeaders headers){
        return feilRegistrerService.feilRegistrer(journalpostId, type, headers)
                .onErrorResume(e -> {
                    // Handle any errors that occur during the service call
                    logger.error("DokArkivController - feilregister() - Fail -  Feil ved lagring av nye journalposter: {}", e.getMessage());
                    return ErrorHandling.handleError(e);
                })
                .doOnSuccess(response -> logger.info("DokArkivController - feilregister() -  Success - Response sent to client: {}", response.getStatusCode()));
    }

}
