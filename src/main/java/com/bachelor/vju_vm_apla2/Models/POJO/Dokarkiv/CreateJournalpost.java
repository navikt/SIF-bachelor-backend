package com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv;

import com.bachelor.vju_vm_apla2.Models.POJO.Saf.AvsenderMottaker;
import lombok.Data;

@Data
public class CreateJournalpost {

    private AvsenderMottaker avsenderMottaker;
    private String journalposttype;
    private String versjon;
    private Bruker bruker;
    private String tittel;
    private Tema tema;
    private String datoDokument;
    private java.util.List<Dokumenter> dokumenter;
}
