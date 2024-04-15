package com.bachelor.vju_vm_apla2.Service;

import com.bachelor.vju_vm_apla2.Config.CustomClientException;
import com.bachelor.vju_vm_apla2.Models.DTO.Saf.GetJournalpostList_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.Saf.ReturnFromGraphQl_DTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DokService {
    private static final Logger logger = LogManager.getLogger(SimpleService.class);

    private final WebClient webClient;
    @Value("${wiremock-dok.combined}")
    private String url;
    //setter opp HTTP syntax slik at vi kan gjøre kall på serverere (Serevere er erstattet med Wiremock)
    public DokService() {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }
    
    //------------------------------------------------------------------------------------

    public Mono<String> fetchHello() {
        return webClient.get()
                .uri(url + "/hello")
                .retrieve()
                .bodyToMono(String.class);
    }



    /////////////////////////////// HandleDok BETA METODER ///////////////////////////
    //TODO: HandleDok(DTO_createJP)







    //////////////////////////////////////////////////////////////////////////////////







    //TODO: GetDok_dokarkiv(FysiskdokId)

    //TODO: Create_Dok(DTO old/New)
}
