package com.bachelor.vju_vm_apla2.Controller;

import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.ControllerReponse_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.CreateJournalpost_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.ResponeReturnFromDokArkiv_DTO;
import com.bachelor.vju_vm_apla2.Service.DokService;
import no.nav.security.token.support.core.api.Protected;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.http.HttpHeaders;

@Protected
@RestController
public class DokArkivController {

    private static final Logger logger = LogManager.getLogger(DokService.class);

    private final DokService dokService;

    @Autowired
    public DokArkivController(DokService dokService){
        this.dokService = dokService;
    }

    @RequestMapping("/dokarkivAPI")
    @CrossOrigin
    @PostMapping("/createJournalpost")
    public Mono<ResponseEntity<ControllerReponse_DTO[]>> createJournalpost(@RequestBody CreateJournalpost_DTO meta, @RequestHeader HttpHeaders header){

        logger.info("Inne i metoden hentJournalpostListe med data: {}", meta);

        //denne skal returnere
        return null;
    }
}
