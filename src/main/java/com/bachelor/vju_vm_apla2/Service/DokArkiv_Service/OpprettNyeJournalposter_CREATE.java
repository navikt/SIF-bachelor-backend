package com.bachelor.vju_vm_apla2.Service.DokArkiv_Service;

import com.bachelor.vju_vm_apla2.ErrorHandling.CustomClientException;
import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.CreateJournalpost_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.ResponeReturnFromDokArkiv_DTO;
import com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv.CreateJournalpost;
import com.bachelor.vju_vm_apla2.Service.Utilz.ExtractDokumentIDinDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OpprettNyeJournalposter_CREATE {
    private static final Logger logger = LogManager.getLogger(OpprettNyeJournalposter_CREATE.class);


    private final ExtractDokumentIDinDTO extractDokumentIDinDTO;

    private final WebClient webClient;
    @Value("${wiremock-dok.combined}")
    private String url;
    //setter opp HTTP syntax slik at vi kan gjøre kall på serverere (Serevere er erstattet med Wiremock)
    @Autowired
    public OpprettNyeJournalposter_CREATE(ExtractDokumentIDinDTO extractDokumentIDinDTO) {
        this.extractDokumentIDinDTO = extractDokumentIDinDTO;
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }
    private ObjectMapper objectMapper = new ObjectMapper();


    //------------------------------------------------------------------------------------

    public Mono<String> fetchHello() {
        return webClient.get()
                .uri(url + "/hello")
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<ResponseEntity<List<ResponeReturnFromDokArkiv_DTO>>> createJournalpost_Service(CreateJournalpost_DTO meta, HttpHeaders originalHeader) {
        logger.info("DokArkiv_Service - createJournalpost() -  Inne i createJournalpost - Received JSON data: {}", meta);


        // Setter versjon på metadata. Det er for stubs slik at de kan skille mellom dem
        meta.getOldMetadata().setVersjon("old");
        meta.getNewMetadata().setVersjon("new");



        Mono<CreateJournalpost> updatedOldMeta = extractDokumentIDinDTO.updateDocumentIdsInDto(meta.getOldMetadata(), meta.getJournalpostID()).thenReturn(meta.getOldMetadata());
        Mono<CreateJournalpost> updatedNewMeta = extractDokumentIDinDTO.updateDocumentIdsInDto(meta.getNewMetadata(), meta.getJournalpostID()).thenReturn(meta.getNewMetadata());

        return Mono.zip(updatedOldMeta, updatedNewMeta)
                .doOnSuccess(item -> logger.info("DoArkiv_Service - createJournalpost_Service - SUCCSESS - Parsing updateOldMeta/updateNewMeta vellykket -  Nå skal begge DTO være klare for å sende til dokarkiv gjennom postNewJournalpost (opprette nye)"))
                .flatMap(tuple -> {
                    Mono<ResponeReturnFromDokArkiv_DTO> responseForOldMeta = postNewJournalpost(tuple.getT1(), originalHeader);
                    Mono<ResponeReturnFromDokArkiv_DTO> responseForNewMeta = postNewJournalpost(tuple.getT2(), originalHeader);
                    return Mono.zip(responseForOldMeta, responseForNewMeta, List::of)
                    .onErrorResume(e -> {
                        logger.error("FAIL: DoArkiv_Service - createJournalpost_Service -  Feil oppstått ved prosessering av opprettnyejournalposter: {}", e.getMessage());
                        return Mono.error((e));
                    });

                })
                .map(responses -> {
                    logger.info("INFO: DoArkiv_Service - createJournalpost_Service - SUCCSESS - Vi er tilbake fra opprette nye journaposter med  {}, {}", responses.get(0), responses.get(1));
                    return ResponseEntity.ok().body(responses);
                })
                .onErrorResume(e -> {
                    if (e instanceof CustomClientException) {
                        return Mono.error(e);
                    } else {
                        logger.error("FAIL - DoArkiv_Service - createJournalpost_Service - Feil oppstått ved prosessering av opprettnyejournalposter: {}", e.getMessage());
                        return Mono.error((new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DoArkiv_Service - OpprettNyeJournalposter_CREATE - FAIL - Feil oppstått ved prosessering av opprettnyejournalposter", e)));
                    }
                });

    }



    private Mono<ResponeReturnFromDokArkiv_DTO> postNewJournalpost(CreateJournalpost journalPost, HttpHeaders originalHeader) {
        logger.info("DoArkiv_Service - serializeAndSendJournalpost() - Inne i metoden med " + journalPost + " og prøver å gjøre kall til wiremock nå");

        String jsonPayload;
        try {
            jsonPayload = objectMapper.writeValueAsString(journalPost);
            logger.info("Info: DoArkiv_Service - postNewJournalpost() - Vi skal inn i wiremock dokarkiv nå Sending JSON data: {}", jsonPayload);
        } catch (JsonProcessingException e) {
            logger.error("DoArkiv_Service - postNewJournalpost() - FAIL - Error serializing DTO to JSON", e);
            return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DoArkiv_Service - serializeAndSendJournalpost() - FAIL - Error parsing DTO to JSON", e));
        }

        return webClient.post()
                .uri(url+"/rest/journapostapi/v1/journalpost?forsoekFerdigstill=false")
                .headers(h -> h.addAll(originalHeader))
                .bodyValue(jsonPayload)  // Attach the JSON payload to the POST request
                .retrieve()  // Initiate the retrieval of the response
                .onStatus(status -> status.isError(), clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            int statusValue = clientResponse.statusCode().value();
                            logger.error("DoArkiv_Service - postNewJournalpost() - FAIL - Error calling external service:  {}", errorBody);
                            return Mono.just(new CustomClientException(statusValue, errorBody, "postNewJournalpost"));
                        }))
                .bodyToMono(ResponeReturnFromDokArkiv_DTO.class)
                .onErrorResume(e -> {
                    if (e instanceof CustomClientException){
                        return Mono.error(e);
                    } else {
                        logger.error("DoArkiv_Service - postNewJournalpost - FAIL - Error during POST request for: {}", journalPost.getTittel());
                        return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DoArkiv_Service - postNewJournalpost - En uventet feil oppstod, vennligst prøv igjen senere.", e));
                    }
                    });
    }




}
