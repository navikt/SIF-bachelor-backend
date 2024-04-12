package com.bachelor.vju_vm_apla2.Service;

import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.ControllerReponse_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.ResponeReturnFromDokArkiv_DTO;
import com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv.Dokumenter;
import com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv.Dokumenter_return;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class DokService {
    private static final Logger logger = LogManager.getLogger(SimpleService.class);
    private final WebClient webClient;
    @Value("${wiremock.combined}")
    private String url;
    //setter opp HTTP syntax slik at vi kan gjøre kall på serverere (Serevere er erstattet med Wiremock)
    public DokService() {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }
    
    //------------------------------------------------------------------------------------
    //public Mono<String>
    /*
    1: extract dokid fra fysisk dokument
    1.1.1: uses -> hentdokument -> from hash to doknavn?
    1.1: returns convertdokId

     */
        //checks the documentvalues (extra check?)
        /*
        [
    {
      "brevkode": "NAV 04-01.04",
      "dokumentvarianter": [
        {
          "filtype": "PDFA",
          "fysiskDokument": "U8O4a25hZCBvbSBkYWdwZW5nZXIgdmVkIHBlcm1pdHRlcmluZw==",
          "variantformat": "ARKIV"
        }
      ],
         */
    public List<String> ConvertArrayToBase64DocumentId (ControllerReponse_DTO oldMetadata){
        List<String>base64s = new ArrayList<>();
        for (int i=0; i <= oldMetadata.getNewJournalposts().size() - 1; i++){
           base64s.add(i, tobase64(oldMetadata.getNewJournalposts().get(i).getDokumenter().get(i).getDokumentvarianter().get(i).getFilnavn())); //creates a base64 thingamajig and puts it in the right place in a new array
        }
        return base64s;
    }
    private static String tobase64 (String input){ //private until we decide otherwise
        return Base64.getEncoder().encodeToString(input.getBytes());
    }
}
