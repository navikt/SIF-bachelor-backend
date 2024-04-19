package com.bachelor.vju_vm_apla2.Controller;

import com.bachelor.vju_vm_apla2.Config.ErrorHandling;
import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.CreateJournalpost_DTO;
import com.bachelor.vju_vm_apla2.Service.DokService;
import no.nav.security.token.support.core.api.Protected;
import no.nav.security.token.support.core.api.Unprotected;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Protected
@RestController
public class DokArkivController {

    private static final Logger logger = LogManager.getLogger(DokService.class);

    private final DokService dokService;

    @Autowired
    public DokArkivController(DokService dokService){
        this.dokService = dokService;
    }


    //TODO: Opprett kontroller CreateJournalpost

    /////////////////////////////////// ALPHA METODE FOR CREATE JOURNALPOST///////////////////////////////////////////
    @Unprotected
//@RequestMapping("/dokarkivAPI")
    @CrossOrigin
    @PostMapping("/createJournalpost")
    public Mono<ResponseEntity<ResponseEntity<String>>> createJournalpost(@RequestBody CreateJournalpost_DTO meta) {
        logger.info("Received JSON data: {}", meta);

        // Call the service and then handle its response asynchronously
        return dokService.createJournalpost(meta)
                .map(response -> {
                    logger.info("Response received from service: {}", response);
                    // Assuming 'response' is the combinedResponse string from service
                    return ResponseEntity.ok().body(response);
                })
                .onErrorResume(e -> {
                    logger.error("Feil ved lagring av nye journalposter: {}", e.getMessage());
                    return ErrorHandling.handleError(e);
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
