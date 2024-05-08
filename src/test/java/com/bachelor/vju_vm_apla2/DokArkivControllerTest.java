package com.bachelor.vju_vm_apla2;

import com.bachelor.vju_vm_apla2.Config.CustomClientException;
import com.bachelor.vju_vm_apla2.Controller.DokArkivController;
import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.CreateJournalpost_DTO;
import com.bachelor.vju_vm_apla2.Models.DTO.DokArkiv.ResponeReturnFromDokArkiv_DTO;
import com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv.Bruker;
import com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv.CreateJournalpost;
import com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv.Dokumenter;
import com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv.Dokumentvariant;
import com.bachelor.vju_vm_apla2.Models.POJO.Saf.BrukerIdType;
import com.bachelor.vju_vm_apla2.Models.POJO.Saf.Tema;
import com.bachelor.vju_vm_apla2.Service.DokArkiv_Service.FeilRegistrer_DELETE;
import com.bachelor.vju_vm_apla2.Service.DokArkiv_Service.HentDokumenter_READ;
import com.bachelor.vju_vm_apla2.Service.DokArkiv_Service.OpprettNyeJournalposter_CREATE;
import com.bachelor.vju_vm_apla2.Service.DokArkiv_Service.SplittingAvJournalposter_UPDATE;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DokArkivControllerTest {
    //mocks for CRUD operations
@Mock
    FeilRegistrer_DELETE feilRegistrer_delete;
@Mock
    HentDokumenter_READ hentDokumenter_read;
@Mock
    OpprettNyeJournalposter_CREATE opprettNyeJournalposter_create;
@Mock
    SplittingAvJournalposter_UPDATE splittingAvJournalposter_update;
@InjectMocks
DokArkivController dokArkivController;
//crud operations tests
@Test
    public void createDokArkiv() {
    Bruker bruker = new Bruker("1", BrukerIdType.FNR);
    List<Dokumenter> docs = new ArrayList<>();
    List<Dokumentvariant>varainter = new ArrayList<>();
    varainter.add(new Dokumentvariant());
    docs.add(new Dokumenter("hei", "hallo", varainter ));
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer");
    CreateJournalpost jp = new CreateJournalpost();
    CreateJournalpost jp2 = new CreateJournalpost();
    String journalpostid = "aa";
    CreateJournalpost_DTO journalpost = new CreateJournalpost_DTO();
    Mono<ResponseEntity<List<ResponeReturnFromDokArkiv_DTO>>> ret;
    ret = Mono.just(new ResponseEntity<List<ResponeReturnFromDokArkiv_DTO>>());
    Mockito.when(opprettNyeJournalposter_create.createJournalpost(journalpost, headers )).thenReturn(ret);

};
@Test
    public void updateDokArkiv() {};
@Test
    public void deleteDokArkiv() {};
@Test
    public void readDokArkiv() {};
@Test(expected = CustomClientException.class)
    public void readDokArkiv_exception() {};

    @Test(expected = CustomClientException.class)
    public void creatDokArkiv_exception() {};

    @Test(expected = CustomClientException.class)
    public void updateDokArkiv_exception() {};

    @Test(expected = CustomClientException.class)
    public void deleteDokArkiv_exception() {};
}
