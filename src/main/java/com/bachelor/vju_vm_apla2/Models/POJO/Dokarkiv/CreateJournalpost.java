package com.bachelor.vju_vm_apla2.Models.POJO.Dokarkiv;

import lombok.Data;

@Data
public class CreateJournalpost {

    private Journalposttype journalposttype;
    private Bruker bruker;
    private String tittel;
    private Tema tema;
    private String datoDokument;
    private java.util.List<Dokumenter> dokumenter;
}
