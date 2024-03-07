package com.bachelor.vju_vm_apla2;

import com.bachelor.vju_vm_apla2.Controller.JournalpostController;
import com.bachelor.vju_vm_apla2.Models.DTO.FraGrapQl_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.FraKlient_DTO;
import com.bachelor.vju_vm_apla2.Models.POJO.graphql.*;
import com.bachelor.vju_vm_apla2.Service.SimpleService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class JournalPostControllerTest {

    @Mock
    SimpleService serviceMock;
    @InjectMocks
    JournalpostController jpController;


    @InjectMocks
    JournalpostController jpContrllerInject = new JournalpostController(serviceMock);

    @Mock
    WebClient wc;
    @Test
    public void hentJournalpostTest() {

        HttpHeaders headers = new HttpHeaders();
        Journalpost jp1 = new Journalpost();
        List<Journalpost> JPtester = new ArrayList<>();
        JPtester.add(jp1);

        List <Journalposttype> jpts = new ArrayList<>();
        Journalposttype JT1 = Journalposttype.N;
        jpts.add(JT1);
        List <Journalstatus> jptts = new ArrayList<>();
        jptts.add(Journalstatus.MOTTATT);
        List<Tema>tt = new ArrayList<>();

        tt.add(Tema.AAP);
        BrukerIdInput bIdInput= new BrukerIdInput("001",BrukerIdType.FNR);
        FraKlient_DTO brukerId = new FraKlient_DTO(bIdInput, "2024-12-12", "2025-12-12", jpts, jptts, tt);
        FraGrapQl_DTO fgqlTest = new FraGrapQl_DTO("200", "hello world");
        Mono <FraGrapQl_DTO> MfgglTest = Mono.just(fgqlTest);
        Mockito.when(serviceMock.hentJournalpostListe(any(FraKlient_DTO.class), any(HttpHeaders.class))).thenReturn(MfgglTest); //headers and stuff dont get sendt, thats why error is getting there


        headers.add("Authorization", "Bearer ");
       String res = String.valueOf(jpController.hentJournalpostListe(brukerId, headers));
        String cmp = String.valueOf(Mono.just(ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json").header("Content-Disposition", "inline").body(fgqlTest)));
        assertEquals(cmp,res );
    }

    @Test
    public void hentJournalpostTestFail() {

        HttpHeaders headers = new HttpHeaders();
        Journalpost jp1 = new Journalpost();
        List<Journalpost> JPtester = new ArrayList<>();
        JPtester.add(jp1);

        List <Journalposttype> jpts = new ArrayList<>();
        Journalposttype JT1 = Journalposttype.N;
        jpts.add(JT1);
        List <Journalstatus> jptts = new ArrayList<>();
        jptts.add(Journalstatus.MOTTATT);
        List<Tema>tt = new ArrayList<>();

        tt.add(Tema.AAP);
        BrukerIdInput bIdInput= new BrukerIdInput("001",BrukerIdType.FNR);
        FraKlient_DTO brukerId = new FraKlient_DTO(bIdInput, "2024-12-12", "2025-12-12", jpts, jptts, tt);
        FraGrapQl_DTO fgqlTest = new FraGrapQl_DTO("200", "hello world");
        Mono <FraGrapQl_DTO> MfgglTest = Mono.just(Objects.requireNonNull(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).contentType(MediaType.APPLICATION_JSON).body(fgqlTest).getBody()));
        Mockito.when(serviceMock.hentJournalpostListe(any(FraKlient_DTO.class), any(HttpHeaders.class))).thenReturn(MfgglTest); //headers and stuff dont get sendt, thats why error is getting there


        headers.add("Authorization", "Bearer ");
        String res = String.valueOf(jpController.hentJournalpostListe(brukerId, headers));
        String cmp = String.valueOf(Mono.just(ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json").header("Content-Disposition", "inline").body(fgqlTest)));
        assertEquals("MonoOnErrorResume",res );
    }

    @SneakyThrows
    @Test
    public void hentDokumenterTest() {

//        String pathToRes = resources.toFile().getPath();
        String dokumentId = "000001";
        HttpHeaders headers = new HttpHeaders();
        byte[] fakePDF = "Cool pdfs".getBytes(StandardCharsets.UTF_8);
        ByteArrayResource fakePDFresource = new ByteArrayResource(fakePDF);

        Mockito.when(serviceMock.hentDokument(dokumentId, headers)).thenReturn(Mono.just(fakePDFresource));
        String cmp = String.valueOf(Mono.just(ResponseEntity.status(HttpStatus.OK).headers(headers).contentType(MediaType.APPLICATION_PDF).header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"document.pdf\"").body(fakePDFresource)).block());
        String res = String.valueOf(jpController.hentDokument(dokumentId, headers).block());

        assertEquals(cmp, res);
    }

    @Test
    public void protectedTest() {
        assertEquals("I am protected", jpController.protectedPath());
    }

    @Test
    public void unprotectedTest() {
        assertEquals("I am unprotected", jpController.unProtectedPath());
    }
}
