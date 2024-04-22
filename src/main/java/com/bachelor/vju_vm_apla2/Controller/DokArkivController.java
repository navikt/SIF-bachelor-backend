package com.bachelor.vju_vm_apla2.Controller;

import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.CreateJournalpost_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.ResponeReturnFromDokArkiv_DTO;
import com.bachelor.vju_vm_apla2.Service.DokArkiv_Service.OpprettNyeJournalposter_CREATE;
import no.nav.security.token.support.core.api.Protected;
import no.nav.security.token.support.core.api.Unprotected;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Protected
@RestController
public class DokArkivController {

    private static final Logger logger = LogManager.getLogger(OpprettNyeJournalposter_CREATE.class);

    private final OpprettNyeJournalposter_CREATE opprettNyeJournalposterCREATE;

    @Autowired
    public DokArkivController(OpprettNyeJournalposter_CREATE opprettNyeJournalposterCREATE){
        this.opprettNyeJournalposterCREATE = opprettNyeJournalposterCREATE;
    }


    //TODO: Opprett kontroller CreateJournalpost

    /////////////////////////////////// ALPHA METODE FOR CREATE JOURNALPOST///////////////////////////////////////////
    @Unprotected
//@RequestMapping("/dokarkivAPI")
    @CrossOrigin
    @PostMapping("/createJournalpost")
    public Mono<ResponseEntity<List<ResponeReturnFromDokArkiv_DTO>>> createJournalpost(@RequestBody CreateJournalpost_DTO meta) {
        logger.info("Received JSON data: {}", meta);

        // Call the service and handle its response asynchronously
        return opprettNyeJournalposterCREATE.createJournalpost(meta)
                .map(response -> {
                    // Directly return the ResponseEntity created in the service layer
                    logger.info("Response received from service: {}", response.getBody());
                    return ResponseEntity.ok().body(response.getBody());  // Ensure it's not wrapped again
                })
                .onErrorResume(e -> {
                    // Handle any errors that occur during the service call
                    logger.error("Feil ved lagring av nye journalposter: {}", e.getMessage());
                    return Mono.just(ResponseEntity.internalServerError().body(new ArrayList<>()));  // Provide an empty list on error
                })
                .doOnSuccess(response -> logger.info("Response sent to client: {}", response.getStatusCode()));
    }

    /*
    @RequestMapping("/dokarkivAPI")
    @CrossOrigin
    @PostMapping("/alpha_createJournalpost")
    public Mono<ResponseEntity<ControllerReponse_DTO[]>> createJournalpost(@RequestBody CreateJournalpost_DTO meta, @RequestHeader HttpHeaders header){

        logger.info("Inne i metoden hentJournalpostListe med data: {}", meta);

        //denne skal returnere
        return null;
    }

     */


}
