package com.bachelor.vju_vm_apla2;

import com.bachelor.vju_vm_apla2.Controller.JournalpostController;
import com.bachelor.vju_vm_apla2.Service.SimpleService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import wiremock.org.checkerframework.checker.index.qual.IndexFor;

import java.net.http.HttpResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class JournalPostControllerTest {

    @Mock
    SimpleService simpleService = new SimpleService();
    @Mock
    JournalpostController jpController = new JournalpostController(simpleService);

    @InjectMocks
    JournalpostController jpContrllerInject = new JournalpostController(simpleService);
    /*@Test
    public void sendToController(){

        //simpel test to test the test method in the controller
        Mockito.when(jpController.test()).thenReturn("vi kan hente fra kontroller");
        String res = jpController.test();
        assertEquals("vi kan hente fra kontroller", res);
    }*/

    /*@Test
    public void hentJournalpostTest (){
Mockito.when(jpController.hentJournalpostListe(anyString(), ArgumentMatchers.any(HttpHeaders.class))).thenReturn(null);
String brukerid = "asdsd12123312";
HttpHeaders headers = new HttpHeaders();
assertNull(jpController.hentJournalpostListe(brukerid, headers));
    }*/
}
