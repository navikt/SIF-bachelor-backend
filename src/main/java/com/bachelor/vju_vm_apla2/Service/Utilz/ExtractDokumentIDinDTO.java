package com.bachelor.vju_vm_apla2.Service.Utilz;

import com.bachelor.vju_vm_apla2.ErrorHandling.CustomClientException;
import com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv.CreateJournalpost;
import com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv.Dokumenter;
import com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv.Dokumentvariant;
import com.bachelor.vju_vm_apla2.Service.DokArkiv_Service.HentDokumenter_READ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExtractDokumentIDinDTO {

    private static final Logger logger = LogManager.getLogger(ExtractDokumentIDinDTO.class);

    private final HentDokumenter_READ hentDokumenter_READ;

    @Autowired
    public ExtractDokumentIDinDTO(HentDokumenter_READ hentDokumenter_READ){
        this.hentDokumenter_READ = hentDokumenter_READ;
    }

    public Mono<Void> updateDocumentIdsInDto(CreateJournalpost dto, String journalpostID) {
        logger.info("DoArkiv_Service - updateDocumentIdsInDto(). Starting to update Document IDs in DTO: {}", dto);
        Mono<List<String>> cachedDocumentIds = extractAndReplaceDocumentIds(dto,journalpostID ).cache();
        return cachedDocumentIds
                .flatMap(newIds -> replaceDocumentIdsInDto(dto, newIds))
                .then()
                .doOnSuccess(done -> logger.info("DoArkiv_Service - updateDocumentIdsInDto() - SUCCESS - Tilbake til updateDocumentIdsInDto etter at DTO har fått nye fysiskID. Vi skal tilbake til craetejournapost"))
                .onErrorResume(e -> {
                    logger.error("ERROR: updateDocumentIdsInDto ", e.getMessage());
                    if (e instanceof ResponseStatusException) {
                        return Mono.error(e);
                    } else if (e instanceof CustomClientException) {
                        return Mono.error(e);
                    } else {
                        return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "updateDocumentIdsInDto - En uventet feil oppstod ved behandling, vennligst prøv igjen.", e));
                    }
                });
    }

    private Mono<Void> replaceDocumentIdsInDto(CreateJournalpost dto, List<String> newIds) {
        logger.info("9. Vi er i replaceDocumentIdsInDto og kommet inn med DTO: {} og nye IDer: {}", dto, newIds);
        int count = 0;
        for (Dokumenter dokument : dto.getDokumenter()) {
            for (Dokumentvariant variant : dokument.getDokumentvarianter()) {
                if (count < newIds.size()) {
                    String oldId = variant.getFysiskDokument();
                    variant.setFysiskDokument(newIds.get(count));
                    logger.info("Erstattet gammel ID: {} med ny ID: {} for dokumentvariant", oldId, newIds.get(count));
                    count++;
                }
            }
        }
        logger.info("10. Ferdig med å erstatte IDer i DTO, oppdatert DTO: {}", dto);
        return Mono.empty();  // Returnerer en tom Mono for å signalisere fullførelse.
    }


    private Mono<List<String>> extractAndReplaceDocumentIds(CreateJournalpost dto, String journalpostID) {
        logger.info("INFO: extractAndReplaceDocumentIds - Starter ID extraction for: {}", dto);

        //TODO: Huske på å bytte journalpostID med riktig
        String journalpostId = "1";  // Replace with dynamic ID retrieval logic
        //String journalpostId = journalpostID;  // Replace with dynamic ID retrieval logic


        return processDocuments(dto, journalpostId)
                .map(ids -> {
                    return ids;
                })
                .onErrorResume(e -> {
                    // Log and handle the error specifically if needed, or just re-throw it.
                    logger.error("ERROR: extractAndReplaceDocumentIds - Feil ved behandling av dokument IDer: {}", e.getMessage());
                    if (e instanceof ResponseStatusException) {
                        return Mono.error(e);
                    } else if (e instanceof CustomClientException) {
                        return Mono.error(e);
                    } else {
                        return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "extractAndReplaceDocumentIds - En uventet feil oppstod ved behandling, vennligst prøv igjen.", e));
                    }
                });
    }


    private Mono<List<String>> processDocuments(CreateJournalpost metadata, String journalpostId) {
        logger.info("OppdateringAvJournalposter_UPDATE - processDocuments - Prosesserer meta og journalpostID: {} ", metadata + " " +  journalpostId);

        List<Mono<String>> documentIdMonos = new ArrayList<>();
        for (Dokumenter dokument : metadata.getDokumenter()) {
            for (Dokumentvariant variant : dokument.getDokumentvarianter()) {
                Mono<String> documentIdMono = hentDokumenter_READ.hentDokument_SAF(variant.getFysiskDokument(), journalpostId)
                        .doOnNext(newId -> logger.info("OppdateringAvJournalposter_UPDATE - processDocuments - Mottatt BASE64STRING fra DokAkriv" ) )
                        .cache()
                        .onErrorResume(e -> {
                            logger.error("ERROR: OppdateringAvJournalposter_UPDATE - processDocuments -> ", e.getMessage());
                            return Mono.error(e);
                        });
                documentIdMonos.add(documentIdMono);
            }
        }

        return Mono.zip(documentIdMonos, results ->
                        Stream.of(results).map(result -> (String) result).collect(Collectors.toList()))
                .doOnNext(allIds -> logger.info("INFO: OppdateringAvJournalposter_UPDATE - processDocuments - Alle dokumentID har blitt prosessert og hentet: {}", allIds))
                .onErrorResume(e -> {
                    logger.error("ERROR: OppdateringAvJournalposter_UPDATE - processDocuments - FAIL - Feil oppstått ved prosessering av dokumenter: {}", e.getMessage());
                    return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "OppdateringAvJournalposter_UPDATE - processDocuments - En uventet feil oppstod ved prosessering, vennligst prøv igjen.", e));
                });
    }

}
