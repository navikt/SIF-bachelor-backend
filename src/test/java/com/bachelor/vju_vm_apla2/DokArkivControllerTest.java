package com.bachelor.vju_vm_apla2;

import com.bachelor.vju_vm_apla2.Config.CustomClientException;
import com.bachelor.vju_vm_apla2.Service.DokArkiv_Service.FeilRegistrer_DELETE;
import com.bachelor.vju_vm_apla2.Service.DokArkiv_Service.HentDokumenter_READ;
import com.bachelor.vju_vm_apla2.Service.DokArkiv_Service.OpprettNyeJournalposter_CREATE;
import com.bachelor.vju_vm_apla2.Service.DokArkiv_Service.SplittingAvJournalposter_UPDATE;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.nio.charset.CharsetEncoder;

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
//crud operations tests
@Test
    public void createDokArkiv() {};
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
