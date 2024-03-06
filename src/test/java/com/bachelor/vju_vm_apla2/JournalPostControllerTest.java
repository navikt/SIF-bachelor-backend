package com.bachelor.vju_vm_apla2;

import com.bachelor.vju_vm_apla2.Controller.JournalpostController;
import com.bachelor.vju_vm_apla2.Service.SimpleService;
import com.github.tomakehurst.wiremock.http.Body;
import org.json.HTTP;
import org.junit.Test;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;
import wiremock.org.checkerframework.checker.index.qual.IndexFor;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.net.http.HttpResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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


    @Test
    public void hentJournalpostTest() {
        String brukerid = "asdsd12123312";
        HttpHeaders headers = new HttpHeaders();

        Mockito.when(serviceMock.hentJournalpostListe(anyString(), any(HttpHeaders.class))).thenReturn("en banan, to banan");


        headers.add("Authorization", "Bearer ");
        ResponseEntity<String> res = jpController.hentJournalpostListe(brukerid, headers);
        ResponseEntity<String> cmp = ResponseEntity.status(org.springframework.http.HttpStatus.OK).header("Content-Type", "application/json").header("Content-Disposition", "inline").body("en banan, to banan");
        assertEquals(cmp, res);
    }

    @Test
    public void hentDokumenterTest() {
        String dokumentId = "000001";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer");
//        Todo: fix test resource to grab :O
//

// byte [] bytes = ;
        Resource r = Mockito.mock(Resource.class);
        Mockito.when(serviceMock.hentDokument(dokumentId, headers)).thenReturn(r.getFile(new byte[] a ));
       // Mono<ResponseEntity<Resource>> cmp = ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/pdf").header("Content-Disposition", "inline").body();
      Mono <ResponseEntity<Resource>> res = jpController.hentDokument(dokumentId, headers);
        assertNull(jpController.hentDokument(dokumentId, headers));
    }
}
