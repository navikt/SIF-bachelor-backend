package com.bachelor.vju_vm_apla2.Repository;

import com.github.tomakehurst.wiremock.WireMockServer;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import static com.bachelor.vju_vm_apla2.Repository.WireMockResponseList.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Configuration
public class WireMockStubs {

   static private WireMockServer wireMockServer;

    @Autowired
    public WireMockStubs(WireMockServer wireMockServer) {
        this.wireMockServer = wireMockServer;
    }
    @PostConstruct
    public void configureStubs() {
        wireMockServer.start();
        // todo :  adding edge cases for calls that needs to be authenticated
        // todo : Add errorcode handling

        //////////////////////////////////////////////////////////////STUBS FOR SØKEFELT UTEN FILTER/////////////////////////////////////////////////////////////


        //Mock for søkeresultat "001". Gir response basert på brukerID input fra clienten.
        wireMockServer.stubFor(post(urlEqualTo("/mock/saf.dev.intern.nav.no/graphql"))
                .withRequestBody(equalToJson("{\"brukerId\": {\"id\": \"001\"}}", true, true))
                .withHeader("Authorization", containing("Bearer"))
                .willReturn(aResponse()
                        .withHeader("Access-Control-Allow-Origin", "*") // Tillat forespørsler fra alle opprinnelser
                        .withHeader("Content-Type", "application/json") // Sett riktig Content-Type for respons
                        .withStatus(200) // Returner HTTP 200 OK
                        .withBody("{\n" +
                                "      \"dokumentoversikt\":{\n" +
                                "         \"journalposter\":[\n" +
                                "            {\n" +
                                "               \"journalpostId\":\"429111291\",\n" +
                                "               \"tittel\":\"Svak Postkasse\",\n" +
                                "               \"journalposttype\":\"U\",\n" +
                                "               \"journalstatus\":\"FERDIGSTILT\",\n" +
                                "               \"tema\":\"OPP\",\n" +
                                "               \"dokumenter\":[\n" +
                                "                  {\n" +
                                "                     \"dokumentInfoId\":\"00001111\",\n" +
                                "                     \"tittel\":\"MASKERT_FELT\"\n" +
                                "                  },\n" +
                                "                  {\n" +
                                "                     \"dokumentInfoId\":\"00002222\",\n" +
                                "                     \"tittel\":\"MASKERT_FELT\"\n" +
                                "                  },\n" +
                                "                  {\n" +
                                "                     \"dokumentInfoId\":\"00003333\",\n" +
                                "                     \"tittel\":\"MASKERT_FELT\"\n" +
                                "                  }\n" +
                                "               ]\n" +
                                "            },\n" +
                                "            {\n" +
                                "               \"journalpostId\":\"429108246\",\n" +
                                "               \"tittel\":\"Rusten Veikryss\",\n" +
                                "               \"journalposttype\":\"U\",\n" +
                                "               \"journalstatus\":\"FERDIGSTILT\",\n" +
                                "               \"tema\":\"OPP\",\n" +
                                "               \"dokumenter\":[\n" +
                                "                  {\n" +
                                "                     \"dokumentInfoId\":\"00004444\",\n" +
                                "                     \"tittel\":\"MASKERT_FELT\"\n" +
                                "                  },\n" +
                                "                  {\n" +
                                "                     \"dokumentInfoId\":\"00005555\",\n" +
                                "                     \"tittel\":\"MASKERT_FELT\"\n" +
                                "                  }\n" +
                                "               ]\n" +
                                "            },\n" +
                                "           {\n" +
                                "               \"journalpostId\":\"428965411\",\n" +
                                "               \"tittel\":\"Rusten Veikryss\",\n" +
                                "               \"journalposttype\":\"U\",\n" +
                                "               \"journalstatus\":\"EKSPEDERT\",\n" +
                                "               \"tema\":\"OPP\",\n" +
                                "               \"dokumenter\":[\n" +
                                "                  {\n" +
                                "                     \"dokumentInfoId\":\"00006666\",\n" +
                                "                     \"tittel\":\"MASKERT_FELT\"\n" +
                                "                  }\n" +
                                "               ]\n" +
                                "            },\n" +
                                "            {\n" +
                                "               \"journalpostId\":\"429101111\",\n" +
                                "               \"tittel\":\"Heisann sveisann\",\n" +
                                "               \"journalposttype\":\"I\",\n" +
                                "               \"journalstatus\":\"JOURNALFOERT\",\n" +
                                "               \"tema\":\"SYM\",\n" +
                                "               \"dokumenter\":[\n" +
                                "                  {\n" +
                                "                     \"dokumentInfoId\":\"00007777\",\n" +
                                "                     \"tittel\":\"MASKERT_FELT\"\n" +
                                "                  },\n" +
                                "                  {\n" +
                                "                     \"dokumentInfoId\":\"00008888\",\n" +
                                "                     \"tittel\":\"MASKERT_FELT\"\n" +
                                "                  },\n" +
                                "                  {\n" +
                                "                     \"dokumentInfoId\":\"00009999\",\n" +
                                "                     \"tittel\":\"MASKERT_FELT\"\n" +
                                "                  },\n" +
                                "                  {\n" +
                                "                     \"dokumentInfoId\":\"00010000\",\n" +
                                "                     \"tittel\":\"MASKERT_FELT\"\n" +
                                "                  }\n" +
                                "               ]\n" +
                                "            }\n" +
                                "         ]\n" +
                                "   }\n" +
                                "}"
                        )
                )
        );



        //Mock for å returnere pdf filer baset på søk etter journalpostID/dokumentinfoID.
        //Nå tar den bare i mot dokumentinfoid som input og returnerer pdf md samme verdi.
        // Eksempel på en spesifikk stub for dokumentID "00001111"
        wireMockServer.stubFor(get(urlPathMatching("/mock/rest/hentdokument/001/(.*)"))
                .willReturn(aResponse()
                        .withHeader("Access-Control-Allow-Origin", "*")
                        .withHeader("Content-Type", "application/pdf")
                        .withStatus(200)
                        .withHeader("Content-Disposition", "inline; filename=\"example.pdf\"")
                        .withTransformers("dynamic-pdf-response-transformer")));




                        //Mock for søkeresultat "003". Gir response basert på brukerID input fra clienten.
        wireMockServer.stubFor(post(urlEqualTo("/mock/saf.dev.intern.nav.no/graphql"))
                .withRequestBody(equalToJson("{\"brukerId\": {\"id\": \"003\"}}", true, true))
                .withHeader("Authorization", containing("Bearer"))
                .willReturn(aResponse()
                        .withHeader("Access-Control-Allow-Origin", "*") // Tillat forespørsler fra alle opprinnelser
                        .withHeader("Content-Type", "application/json") // Sett riktig Content-Type for respons
                        .withStatus(200) // Returner HTTP 200 OK
                        .withBody("{\n" +
                                "   \"dokumentoversikt\":{\n" +
                                "      \"journalposter\":[\n" +
                                "         {\n" +
                                "            \"journalpostId\":\"666111111\",\n" +
                                "            \"tittel\":\"Hemmelig dokument\",\n" +
                                "            \"journalposttype\":\"U\",\n" +
                                "            \"journalstatus\":\"FERDIGSTILT\",\n" +
                                "            \"tema\":\"OPP\",\n" +
                                "            \"datoOpprettet\":\"2024-03-01T12:00:00Z\",\n" + // Eksempel på inkludering av datoOpprettet for første journalpost
                                "            \"dokumenter\":[\n" +
                                "               {\n" +
                                "                  \"dokumentInfoId\":\"00006666\",\n" +
                                "                  \"tittel\":\"Topphemmelig.pdf\"\n" +
                                "               }\n" +
                                "            ]\n" +
                                "         },\n" +
                                "         {\n" +
                                "            \"journalpostId\":\"666222222\",\n" +
                                "            \"tittel\":\"Enda et hemmelig dokument\",\n" +
                                "            \"journalposttype\":\"I\",\n" +
                                "            \"journalstatus\":\"JOURNALFOERT\",\n" +
                                "            \"tema\":\"OPP\",\n" +
                                "            \"datoOpprettet\":\"2024-03-02T12:00:00Z\",\n" + // Eksempel på inkludering av datoOpprettet for andre journalpost
                                "            \"dokumenter\":[\n" +
                                "               {\n" +
                                "                  \"dokumentInfoId\":\"00007777\",\n" +
                                "                  \"tittel\":\"VeldigHemmelig.pdf\"\n" +
                                "               }\n" +
                                "            ]\n" +
                                "         }\n" +
                                "      ]\n" +
                                "   }\n" +
                                "}")));


        //Mock for søkeresultat "004". Gir response basert på brukerID input fra clienten.
        wireMockServer.stubFor(post(urlEqualTo("/mock/saf.dev.intern.nav.no/graphql"))
                .withRequestBody(equalToJson("{\"brukerId\": {\"id\": \"004\"}}", true, true))
                .withHeader("Authorization", containing("Bearer"))
                .willReturn(aResponse()
                        .withHeader("Access-Control-Allow-Origin", "*") // Tillat forespørsler fra alle opprinnelser
                        .withHeader("Content-Type", "application/json") // Sett riktig Content-Type for respons
                        .withStatus(200) // Returner HTTP 200 OK
                        .withBody("{\n" +
                                "   \"dokumentoversikt\":{\n" +
                                "      \"journalposter\":[\n" +
                                "         {\n" +
                                "            \"journalpostId\":\"666111111\",\n" +
                                "            \"tittel\":\"Hemmelig dokument\",\n" +
                                "            \"journalposttype\":\"U\",\n" +
                                "            \"journalstatus\":\"FERDIGSTILT\",\n" +
                                "            \"tema\":\"OPP\",\n" +
                                "            \"datoOpprettet\":\"2024-03-01T12:00:00Z\",\n" +
                                "            \"dokumenter\":[\n" +
                                "               {\n" +
                                "                  \"dokumentInfoId\":\"00006666\",\n" +
                                "                  \"tittel\":\"Topphemmelig.pdf\"\n" +
                                "               }\n" +
                                "            ]\n" +
                                "         },\n" +
                                "         {\n" +
                                "            \"journalpostId\":\"666222222\",\n" +
                                "            \"tittel\":\"Enda et hemmelig dokument\",\n" +
                                "            \"journalposttype\":\"I\",\n" +
                                "            \"journalstatus\":\"JOURNALFOERT\",\n" +
                                "            \"tema\":\"OPP\",\n" +
                                "            \"datoOpprettet\":\"2024-03-02T12:00:00Z\",\n" +
                                "            \"dokumenter\":[\n" +
                                "               {\n" +
                                "                  \"dokumentInfoId\":\"00007777\",\n" +
                                "                  \"tittel\":\"VeldigHemmelig.pdf\"\n" +
                                "               }\n" +
                                "            ]\n" +
                                "         },\n" +
                                "         {\n" +
                                "            \"journalpostId\":\"429111291\",\n" +
                                "            \"tittel\":\"Svak Postkasse\",\n" +
                                "            \"journalposttype\":\"U\",\n" +
                                "            \"journalstatus\":\"FERDIGSTILT\",\n" +
                                "            \"tema\":\"OPP\",\n" +
                                "            \"datoOpprettet\":\"2024-07-01T12:00:00Z\",\n" +
                                "            \"dokumenter\":[\n" +
                                "               {\n" +
                                "                  \"dokumentInfoId\":\"00001111\",\n" +
                                "                  \"tittel\":\"MASKERT_FELT\"\n" +
                                "               },\n" +
                                "               {\n" +
                                "                  \"dokumentInfoId\":\"00002222\",\n" +
                                "                  \"tittel\":\"MASKERT_FELT\"\n" +
                                "               },\n" +
                                "               {\n" +
                                "                  \"dokumentInfoId\":\"00003333\",\n" +
                                "                  \"tittel\":\"MASKERT_FELT\"\n" +
                                "               }\n" +
                                "            ]\n" +
                                "         },\n" +
                                "         {\n" +
                                "            \"journalpostId\":\"429108246\",\n" +
                                "            \"tittel\":\"Rusten Veikryss\",\n" +
                                "            \"journalposttype\":\"U\",\n" +
                                "            \"journalstatus\":\"FERDIGSTILT\",\n" +
                                "            \"tema\":\"OPP\",\n" +
                                "            \"datoOpprettet\":\"2021-11-01T12:00:00Z\",\n" +
                                "            \"dokumenter\":[\n" +
                                "               {\n" +
                                "                  \"dokumentInfoId\":\"00004444\",\n" +
                                "                  \"tittel\":\"MASKERT_FELT\"\n" +
                                "               },\n" +
                                "               {\n" +
                                "                  \"dokumentInfoId\":\"00005555\",\n" +
                                "                  \"tittel\":\"MASKERT_FELT\"\n" +
                                "               }\n" +
                                "            ]\n" +
                                "         },\n" +
                                "         {\n" +
                                "            \"journalpostId\":\"428965411\",\n" +
                                "            \"tittel\":\"Rusten Veikryss\",\n" +
                                "            \"journalposttype\":\"U\",\n" +
                                "            \"journalstatus\":\"EKSPEDERT\",\n" +
                                "            \"tema\":\"OPP\",\n" +
                                "            \"datoOpprettet\":\"2023-11-01T12:00:00Z\",\n" +
                                "            \"dokumenter\":[\n" +
                                "               {\n" +
                                "                  \"dokumentInfoId\":\"00006666\",\n" +
                                "                  \"tittel\":\"MASKERT_FELT\"\n" +
                                "               }\n" +
                                "            ]\n" +
                                "         },\n" +
                                "         {\n" +
                                "            \"journalpostId\":\"429101111\",\n" +
                                "            \"tittel\":\"Heisann sveisann\",\n" +
                                "            \"journalposttype\":\"I\",\n" +
                                "            \"journalstatus\":\"JOURNALFOERT\",\n" +
                                "            \"tema\":\"SYM\",\n" +
                                "            \"datoOpprettet\":\"2020-01-01T12:00:00Z\",\n" +
                                "            \"dokumenter\":[\n" +
                                "               {\n" +
                                "                  \"dokumentInfoId\":\"00007777\",\n" +
                                "                  \"tittel\":\"MASKERT_FELT\"\n" +
                                "               },\n" +
                                "               {\n" +
                                "                  \"dokumentInfoId\":\"00008888\",\n" +
                                "                  \"tittel\":\"MASKERT_FELT\"\n" +
                                "               },\n" +
                                "               {\n" +
                                "                  \"dokumentInfoId\":\"00009999\",\n" +
                                "                  \"tittel\":\"MASKERT_FELT\"\n" +
                                "               },\n" +
                                "               {\n" +
                                "                  \"dokumentInfoId\":\"00010000\",\n" +
                                "                  \"tittel\":\"MASKERT_FELT\"\n" +
                                "               }\n" +
                                "            ]\n" +
                                "         }\n" +
                                "      ]\n" +
                                "   }\n" +
                                "}\n" +
                                "}")));



        /////////////////////////////////////////////////////////////////////////FILTER STUBS/////////////////////////////////////////////////////////////////////////////////////////////////////


        //INGEN FILTER
        //Mock for søkeresultat "002". Gir response basert på brukerID input fra clienten.
        wireMockServer.stubFor(post(urlEqualTo("/mock/saf.dev.intern.nav.no/graphql"))
                .withRequestBody(equalToJson(
                        "{\"brukerId\": {\"id\": \"002\"}}"
                        , true, true))
                .withHeader("Authorization", containing("Bearer"))
                .willReturn(aResponse()
                        .withHeader("Access-Control-Allow-Origin", "*") // Tillat forespørsler fra alle opprinnelser
                        .withHeader("Content-Type", "application/json") // Sett riktig Content-Type for respons
                        .withStatus(200) // Returner HTTP 200 OK
                        .withBody(
                                brukerID_002
                                )));

        //FILTER
        //Mock for søkeresultat "002", FERDIGSTILT
        wireMockServer.stubFor(post(urlEqualTo("/mock/saf.dev.intern.nav.no/graphql"))
                .withRequestBody(equalToJson(
                        "{\n" +
                                "  \"brukerId\": {\"id\": \"002\"},\n" + // Legg merke til kommaet her
                                "  \"journalstatuser\": [\"FERDIGSTILT\"]\n" +
                                "}", true, true))

                .withHeader("Authorization", containing("Bearer"))
                .willReturn(aResponse()
                        .withHeader("Access-Control-Allow-Origin", "*") // Tillat forespørsler fra alle opprinnelser
                        .withHeader("Content-Type", "application/json") // Sett riktig Content-Type for respons
                        .withStatus(200) // Returner HTTP 200 OK
                        .withBody(
                                brukerID_002_FERDIGSTILT
                        )));

        //FILTER
        //Mock for søkeresultat "002", JOURNALFOERT
        wireMockServer.stubFor(post(urlEqualTo("/mock/saf.dev.intern.nav.no/graphql"))
                .withRequestBody(equalToJson(
                        "{\n" +
                                "  \"brukerId\": {\"id\": \"002\"},\n" + // Legg merke til kommaet her
                                "  \"journalstatuser\": [\"JOURNALFOERT\"]\n" +
                                "}", true, true))

                .withHeader("Authorization", containing("Bearer"))
                .willReturn(aResponse()
                        .withHeader("Access-Control-Allow-Origin", "*") // Tillat forespørsler fra alle opprinnelser
                        .withHeader("Content-Type", "application/json") // Sett riktig Content-Type for respons
                        .withStatus(200) // Returner HTTP 200 OK
                        .withBody(
                                brukerID_002_JOURNALFOERT
                        )));


        //FILTER
        //Mock for søkeresultat "002", EKSPEDERT
        wireMockServer.stubFor(post(urlEqualTo("/mock/saf.dev.intern.nav.no/graphql"))
                .withRequestBody(equalToJson(
                        "{\n" +
                                "  \"brukerId\": {\"id\": \"002\"},\n" + // Legg merke til kommaet her
                                "  \"journalstatuser\": [\"EKSPEDERT\"]\n" +
                                "}", true, true))

                .withHeader("Authorization", containing("Bearer"))
                .willReturn(aResponse()
                        .withHeader("Access-Control-Allow-Origin", "*") // Tillat forespørsler fra alle opprinnelser
                        .withHeader("Content-Type", "application/json") // Sett riktig Content-Type for respons
                        .withStatus(200) // Returner HTTP 200 OK
                        .withBody(
                                brukerID_002_EKSPEDERT
                        )));

        //FILTER
        //Mock for søkeresultat "002", FERDIGSTILT, JOURNALFOERT
        wireMockServer.stubFor(post(urlEqualTo("/mock/saf.dev.intern.nav.no/graphql"))
                .withRequestBody(equalToJson(
                        "{\n" +
                                "  \"brukerId\": {\"id\": \"002\"},\n" + // Legg merke til kommaet her
                                "  \"journalstatuser\": [\"FERDIGSTILT\", \"JOURNALFOERT\"]\n" +
                                "}", true, true))

                .withHeader("Authorization", containing("Bearer"))
                .willReturn(aResponse()
                        .withHeader("Access-Control-Allow-Origin", "*") // Tillat forespørsler fra alle opprinnelser
                        .withHeader("Content-Type", "application/json") // Sett riktig Content-Type for respons
                        .withStatus(200) // Returner HTTP 200 OK
                        .withBody(
                                brukerID_002_FERDIGSTILT_JOURNALFOERT
                        )));

        //FILTER
        //Mock for søkeresultat "002", FERDIGSTILT, EKSPEDERT
        wireMockServer.stubFor(post(urlEqualTo("/mock/saf.dev.intern.nav.no/graphql"))
                .withRequestBody(equalToJson(
                        "{\n" +
                                "  \"brukerId\": {\"id\": \"002\"},\n" + // Legg merke til kommaet her
                                "  \"journalstatuser\": [\"FERDIGSTILT\", \"EKSPEDERT\"]\n" +
                                "}", true, true))

                .withHeader("Authorization", containing("Bearer"))
                .willReturn(aResponse()
                        .withHeader("Access-Control-Allow-Origin", "*") // Tillat forespørsler fra alle opprinnelser
                        .withHeader("Content-Type", "application/json") // Sett riktig Content-Type for respons
                        .withStatus(200) // Returner HTTP 200 OK
                        .withBody(
                                brukerID_002_FERDIGSTILT_EKSPEDERT
                        )));

        //FILTER
        //Mock for søkeresultat "002", JOURNALFOERT, EKSPEDERT
        wireMockServer.stubFor(post(urlEqualTo("/mock/saf.dev.intern.nav.no/graphql"))
                .withRequestBody(equalToJson(
                        "{\n" +
                                "  \"brukerId\": {\"id\": \"002\"},\n" + // Legg merke til kommaet her
                                "  \"journalstatuser\": [\"JOURNALFOERT\", \"EKSPEDERT\"]\n" +
                                "}", true, true))

                .withHeader("Authorization", containing("Bearer"))
                .willReturn(aResponse()
                        .withHeader("Access-Control-Allow-Origin", "*") // Tillat forespørsler fra alle opprinnelser
                        .withHeader("Content-Type", "application/json") // Sett riktig Content-Type for respons
                        .withStatus(200) // Returner HTTP 200 OK
                        .withBody(
                                brukerID_002_JOURNALFOERT_EKSPEDERT
                        )));





    }



    @PreDestroy
    public void stopWireMockServer() {
        wireMockServer.stop();
    }
}
