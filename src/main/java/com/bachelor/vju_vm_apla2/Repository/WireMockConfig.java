package com.bachelor.vju_vm_apla2.Repository;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.boot.autoconfigure.AutoConfigurationPackages.get;

@Configuration
public class WireMockConfig {

   static private WireMockServer wireMockServer;

    @Autowired
    public WireMockConfig(WireMockServer wireMockServer) {
        this.wireMockServer = wireMockServer;
    }

    @PostConstruct
    public void configureStubs() {
        wireMockServer.start();

        //GET KALL FOR TEST
        wireMockServer.stubFor(WireMock.get(urlEqualTo("/greeting"))
                .willReturn(aResponse()
                        .withHeader("Access-Control-Allow-Origin", "*")
                        .withStatus(200)
                        .withBody("Velkommen til VJU WireMock fjert")));

        //Journalpost POST//
        wireMockServer.stubFor(post(urlEqualTo("/journalpost"))
                .withRequestBody(containing("453857319")) // Sjekk om forespørselskroppen inneholder denne strengen
                .willReturn(aResponse()
                        .withHeader("Access-Control-Allow-Origin", "*") // Tillat forespørsler fra alle opprinnelser
                        .withHeader("Content-Type", "application/json") // Sett riktig Content-Type for respons
                        .withStatus(200) // Returner HTTP 200 OK
                        .withBody("{\n" +
                                "    \"data\": {\n" +
                                "        \"journalpost\": {\n" +
                                "            \"journalposttype\": \"I\",\n" +
                                "            \"journalstatus\": \"MOTTATT\",\n" +
                                "            \"tema\": \"OMS\",\n" +
                                "            \"tittel\": \"Søknad om utbetaling av omsorgspenger for arbeidstaker\",\n" +
                                "            \"dokumenter\": [\n" +
                                "                {\n" +
                                "                    \"dokumentInfoId\": \"648126654\",\n" +
                                "                    \"tittel\": \"Innvilgelse.pdf\"\n" +
                                "                }\n" +
                                "            ],\n" +
                                "            \"avsenderMottaker\": {\n" +
                                "                \"navn\": \"UTYDELIG, SKÅL\"\n" +
                                "            }\n" +
                                "        }\n" +
                                "    }\n" +
                                "}")));

        /*/OPTION CALL
        server.stubFor(options(urlEqualTo("/journalpost"))
                .willReturn(aResponse()
                        .withHeader("Access-Control-Allow-Origin", "*")
                        .withHeader("Access-Control-Allow-Methods", "POST, OPTIONS")
                        .withHeader("Access-Control-Allow-Headers", "Content-Type, Authorization")
                        .withHeader("Access-Control-Max-Age", "3600") // Tillater caching av preflight-responsen for 1 time.
                        .withStatus(200))); // 200 OK status for preflight forespørsel.

         */



        //Journalpost SERIVCE - POST//
        wireMockServer.stubFor(post(urlEqualTo("/journalpost-mock"))
                .withRequestBody(containing("453857319")) // Sjekk om forespørselskroppen inneholder denne strengen
                .willReturn(aResponse()
                        .withHeader("Access-Control-Allow-Origin", "*") // Tillat forespørsler fra alle opprinnelser
                        .withHeader("Content-Type", "application/json;charset=UTF-8") // Sett riktig Content-Type for respons
                        .withStatus(200) // Returner HTTP 200 OK
                        .withBody("Vi har fått svar fra Service Mock kall fra WireMock")));


    }

    @PreDestroy
    public void stopWireMockServer() {
        wireMockServer.stop();
    }
}
