package com.bachelor.vju_vm_apla2.Models.POJO.Saf;


/**
 * Metadata tilknyttet et bestemt dokument i Joark (evt til flere varianter av samme dokument).
 * * Dokumentinfo viser ikke til den fysiske filen, men til metadata som omhandler alle eventuelle varianter av dokumentet.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public class DokumentInfo implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String dokumentInfoId;
    private String tittel;
    private String brevkode;
    private Dokumentstatus dokumentstatus;
    private java.util.Date datoFerdigstilt;
    private String originalJournalpostId;
    private String skjerming;
    @jakarta.validation.constraints.NotNull
    private java.util.List<LogiskVedlegg> logiskeVedlegg;
    @jakarta.validation.constraints.NotNull
    private java.util.List<Dokumentvariant> dokumentvarianter;

    public DokumentInfo() {
    }

    public DokumentInfo(String dokumentInfoId, String tittel, String brevkode, Dokumentstatus dokumentstatus, java.util.Date datoFerdigstilt, String originalJournalpostId, String skjerming, java.util.List<LogiskVedlegg> logiskeVedlegg, java.util.List<Dokumentvariant> dokumentvarianter) {
        this.dokumentInfoId = dokumentInfoId;
        this.tittel = tittel;
        this.brevkode = brevkode;
        this.dokumentstatus = dokumentstatus;
        this.datoFerdigstilt = datoFerdigstilt;
        this.originalJournalpostId = originalJournalpostId;
        this.skjerming = skjerming;
        this.logiskeVedlegg = logiskeVedlegg;
        this.dokumentvarianter = dokumentvarianter;
    }

    /**
     * Unik identifikator per dokumentinfo
     */
    public String getDokumentInfoId() {
        return dokumentInfoId;
    }
    /**
     * Unik identifikator per dokumentinfo
     */
    public void setDokumentInfoId(String dokumentInfoId) {
        this.dokumentInfoId = dokumentInfoId;
    }

    /**
     * Dokumentets tittel, f.eks. *"Søknad om foreldrepenger ved fødsel"* eller *"Legeerklæring"*.
     */
    public String getTittel() {
        return tittel;
    }
    /**
     * Dokumentets tittel, f.eks. *"Søknad om foreldrepenger ved fødsel"* eller *"Legeerklæring"*.
     */
    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    /**
     * Kode som sier noe om dokumentets innhold og oppbygning.
     * * For inngående skjema er brevkoden normalt en NAV-skjemaID f.eks. *"NAV 14-05.09"*. Enkelte vedlegg har en vedleggskode som sier noe om innholdet.
     * * For utgående dokumenter sier brevkoden noe om hvilken dokumentmal som er benyttet og hvordan dokumentet skal distribueres.
     */
    public String getBrevkode() {
        return brevkode;
    }
    /**
     * Kode som sier noe om dokumentets innhold og oppbygning.
     * * For inngående skjema er brevkoden normalt en NAV-skjemaID f.eks. *"NAV 14-05.09"*. Enkelte vedlegg har en vedleggskode som sier noe om innholdet.
     * * For utgående dokumenter sier brevkoden noe om hvilken dokumentmal som er benyttet og hvordan dokumentet skal distribueres.
     */
    public void setBrevkode(String brevkode) {
        this.brevkode = brevkode;
    }

    /**
     * Dokumentstatus gir et indikasjon på hvorvidt dokumentet er ferdigstilt eller under arbeid, eventuelt avbrutt. Dersom dokumentet ikke har noen dokumentstatus, er dokumentet komplett / ferdigstilt.
     */
    public Dokumentstatus getDokumentstatus() {
        return dokumentstatus;
    }
    /**
     * Dokumentstatus gir et indikasjon på hvorvidt dokumentet er ferdigstilt eller under arbeid, eventuelt avbrutt. Dersom dokumentet ikke har noen dokumentstatus, er dokumentet komplett / ferdigstilt.
     */
    public void setDokumentstatus(Dokumentstatus dokumentstatus) {
        this.dokumentstatus = dokumentstatus;
    }

    /**
     * Dato dokumentet ble ferdigstilt.
     */
    public java.util.Date getDatoFerdigstilt() {
        return datoFerdigstilt;
    }
    /**
     * Dato dokumentet ble ferdigstilt.
     */
    public void setDatoFerdigstilt(java.util.Date datoFerdigstilt) {
        this.datoFerdigstilt = datoFerdigstilt;
    }

    /**
     * Et dokumentInfo-objekt kan være gjenbrukt på flere journalposter. OriginalJournalpostId peker på den journalposten som dokumentene var knyttet til på arkiveringstidspunktet.
     */
    public String getOriginalJournalpostId() {
        return originalJournalpostId;
    }
    /**
     * Et dokumentInfo-objekt kan være gjenbrukt på flere journalposter. OriginalJournalpostId peker på den journalposten som dokumentene var knyttet til på arkiveringstidspunktet.
     */
    public void setOriginalJournalpostId(String originalJournalpostId) {
        this.originalJournalpostId = originalJournalpostId;
    }

    /**
     * Uttrykker at tilgangen til metadata for dette dokumentet er begrenset, og at dataene ikke skal brukes i ordinær saksbehandling.
     */
    public String getSkjerming() {
        return skjerming;
    }
    /**
     * Uttrykker at tilgangen til metadata for dette dokumentet er begrenset, og at dataene ikke skal brukes i ordinær saksbehandling.
     */
    public void setSkjerming(String skjerming) {
        this.skjerming = skjerming;
    }

    /**
     * Liste over andre dokumenter som også befinner seg inne i den fysiske filen som dokumentinfo-objektet peker på.
     */
    public java.util.List<LogiskVedlegg> getLogiskeVedlegg() {
        return logiskeVedlegg;
    }
    /**
     * Liste over andre dokumenter som også befinner seg inne i den fysiske filen som dokumentinfo-objektet peker på.
     */
    public void setLogiskeVedlegg(java.util.List<LogiskVedlegg> logiskeVedlegg) {
        this.logiskeVedlegg = logiskeVedlegg;
    }

    /**
     * Liste over tilgjengelige varianter av dokumentet.
     */
    public java.util.List<Dokumentvariant> getDokumentvarianter() {
        return dokumentvarianter;
    }
    /**
     * Liste over tilgjengelige varianter av dokumentet.
     */
    public void setDokumentvarianter(java.util.List<Dokumentvariant> dokumentvarianter) {
        this.dokumentvarianter = dokumentvarianter;
    }



}
