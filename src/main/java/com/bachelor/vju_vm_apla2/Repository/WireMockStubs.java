package com.bachelor.vju_vm_apla2.Repository;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

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

        //Mock for søkeresultat "69". Gir response basert på brukerID input fra clienten.
        wireMockServer.stubFor(post(urlEqualTo("/mock-journalpost"))
                .withRequestBody(equalToJson("{\"dokumentoversiktBruker\":\"001\"}", true, true))
//                .withHeader("Authorizaton", containing("Bearer"))
                .willReturn(aResponse()
                        .withHeader("Access-Control-Allow-Origin", "*") // Tillat forespørsler fra alle opprinnelser
                        .withHeader("Content-Type", "application/json") // Sett riktig Content-Type for respons
                        .withStatus(200) // Returner HTTP 200 OK
                        .withBody("{\n" +
                                "   \"data\":{\n" +
                                "      \"dokumentoversiktBruker\":{\n" +
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
                                "      }\n" +
                                "   }\n" +
                                "}"
                        )
                )
        );

        //Denne gir feil 401 not authorized dersom du prøver å kalle!!

        /*
        wireMockServer.stubFor(post(urlEqualTo("/mock-journalpost"))
                        .withRequestBody(equalToJson("{\"dokumentoversiktBruker\":\"69\"}", true, true)).willReturn(aResponse().withStatus(401).withBody("nei skam deg, her går du inn i steder du ikke har lov"))
        );




        wireMockServer.stubFor(post(urlEqualTo("/mock-journalpost"))
                .willReturn(aResponse().withStatus(401).withBody("nei skam deg, her går du inn i steder du ikke har lov"))
        ); //different end point scenarios,
        */


        //Mock for søkeresultat "666". Gir response basert på brukerID input fra clienten.
        wireMockServer.stubFor(post(urlEqualTo("/mock-journalpost"))
                .withRequestBody(equalToJson("{\"dokumentoversiktBruker\":\"002\"}", true, true))
                .willReturn(aResponse()
                        .withHeader("Access-Control-Allow-Origin", "*") // Tillat forespørsler fra alle opprinnelser
                        .withHeader("Content-Type", "application/json") // Sett riktig Content-Type for respons
                        .withStatus(200) // Returner HTTP 200 OK
                        .withBody("{" +
                                "\"data\":{\n" +
                                        "      \"dokumentoversiktBruker\":{\n" +
                                        "         \"journalposter\":[\n" +
                                        "            {\n" +
                                        "               \"journalpostId\":\"666111111\",\n" +
                                        "               \"tittel\":\"Hemmelig dokument\",\n" +
                                        "               \"journalposttype\":\"U\",\n" +
                                        "               \"journalstatus\":\"FERDIGSTILT\",\n" +
                                        "               \"tema\":\"XYZ\",\n" +
                                        "               \"dokumenter\":[\n" +
                                        "                  {\n" +
                                        "                     \"dokumentInfoId\":\"00006666\",\n" +
                                        "                     \"tittel\":\"Topphemmelig.pdf\"\n" +
                                        "                  }\n" +
                                        "               ]\n" +
                                        "            },\n" +
                                        "            {\n" +
                                        "               \"journalpostId\":\"666222222\",\n" +
                                        "               \"tittel\":\"Enda et hemmelig dokument\",\n" +
                                        "               \"journalposttype\":\"I\",\n" +
                                        "               \"journalstatus\":\"JOURNALFOERT\",\n" +
                                        "               \"tema\":\"ABC\",\n" +
                                        "               \"dokumenter\":[\n" +
                                        "                  {\n" +
                                        "                     \"dokumentInfoId\":\"00007777\",\n" +
                                        "                     \"tittel\":\"VeldigHemmelig.pdf\"\n" +
                                        "                  }\n" +
                                        "               ]\n" +
                                        "            }\n" +
                                        "         ]\n" +
                                        "      }\n" +
                                        "   }" +
                                "}")));

        //Journalpost SERIVCE - POST//
        wireMockServer.stubFor(post(urlEqualTo("/journalpost-mock"))
                .withRequestBody(containing("453857319")) // Sjekk om forespørselskroppen inneholder denne strengen
                .willReturn(aResponse()
                        .withHeader("Access-Control-Allow-Origin", "*") // Tillat forespørsler fra alle opprinnelser
                        .withHeader("Content-Type", "application/json;charset=UTF-8") // Sett riktig Content-Type for respons
                        .withStatus(200) // Returner HTTP 200 OK
                        .withBody("Vi har fått svar fra Service Mock kall fra WireMock")));

        //GET PDF
        wireMockServer.stubFor(WireMock.get(urlEqualTo("https://saf.dev.intern.nav.no/rest/hentdokument/620233453/648126654/ARKIV"))
                .willReturn(aResponse()
                        .withHeader("Access-Control-Allow-Origin", "*")
                        .withHeader("Content-Type", "application/pdf")
                        .withStatus(200)
                        .withBodyFile("example.pdf")));

    }

    @PreDestroy
    public void stopWireMockServer() {
        wireMockServer.stop();
    }
}
