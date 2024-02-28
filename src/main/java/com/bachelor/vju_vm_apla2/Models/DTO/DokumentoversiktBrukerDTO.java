package com.bachelor.vju_vm_apla2.Models.DTO;

import com.bachelor.vju_vm_apla2.Models.POJO.graphql.*;
import lombok.Data;

import java.util.List;

@Data
public class DokumentoversiktBrukerDTO {
    private BrukerIdInput brukerId;
    private String fraDato;
    private String tilDato;
    private List<Journalposttype> journalposttyper;
    private List<Journalstatus> journalstatuser;
    private List<Tema> tema;
    private Integer foerste;
    private String etter;

    private List<Journalpost> journalposter;
}
