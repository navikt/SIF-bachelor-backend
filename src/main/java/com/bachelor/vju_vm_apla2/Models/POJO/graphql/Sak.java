package com.bachelor.vju_vm_apla2.Models.POJO.graphql;


/**
 * En sak i NAV har flere saksnumre (fagsaksnummer og arkivsaksnummer).
 * * Fagsaken viser til saken slik denne er definert i et fagsystem. Saken identifiseres ved fagsakId + fagsaksystem.
 * * Arkivsaksnummer er "skyggesaken" som man tradisjonelt har journalført mot i Joark. Denne skal nå anses som en intern nøkkel i Joark.
 * #
 * I tilfeller der informasjonen skal journalføres, men ikke passer inn på en fagsak, er det mulig å journalføre mot "generell sak". Generell sak kan anses som brukerens mappe på et tema.
 * Dersom arkivsaksystemet er PSAK, returneres den samme verdien som både arkivsaksnummer og fagsakId.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public class Sak implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Deprecated
    private String arkivsaksnummer;
    @Deprecated
    private Arkivsaksystem arkivsaksystem;
    private java.util.Date datoOpprettet;
    private String fagsakId;
    private String fagsaksystem;
    private Sakstype sakstype;
    private Tema tema;

    public Sak() {
    }

    public Sak(String arkivsaksnummer, Arkivsaksystem arkivsaksystem, java.util.Date datoOpprettet, String fagsakId, String fagsaksystem, Sakstype sakstype, Tema tema) {
        this.arkivsaksnummer = arkivsaksnummer;
        this.arkivsaksystem = arkivsaksystem;
        this.datoOpprettet = datoOpprettet;
        this.fagsakId = fagsakId;
        this.fagsaksystem = fagsaksystem;
        this.sakstype = sakstype;
        this.tema = tema;
    }

    /**
     * Saksnummeret i PSAK eller SAK-tabellen i Joark (tidligere GSAK).
     * * NB: Arkivsak skal anses som Joark-internt. Fagsystemene skal kun bruke denne etter avtale.
     */
    @Deprecated
    public String getArkivsaksnummer() {
        return arkivsaksnummer;
    }
    /**
     * Saksnummeret i PSAK eller SAK-tabellen i Joark (tidligere GSAK).
     * * NB: Arkivsak skal anses som Joark-internt. Fagsystemene skal kun bruke denne etter avtale.
     */
    @Deprecated
    public void setArkivsaksnummer(String arkivsaksnummer) {
        this.arkivsaksnummer = arkivsaksnummer;
    }

    /**
     * Sier hvorvidt arkivsaksnummeret peker på en sak i PSAK eller i SAK-tabellen i Joark (tidligere GSAK). For pensjons- og uføresaker vil arkivsaksystemet være PSAK. For alle andre sakstyper er arkivsaksystem GSAK.
     * * NB: Arkivsak skal anses som Joark-internt. Fagsystemene skal kun bruke denne etter avtale.
     */
    @Deprecated
    public Arkivsaksystem getArkivsaksystem() {
        return arkivsaksystem;
    }
    /**
     * Sier hvorvidt arkivsaksnummeret peker på en sak i PSAK eller i SAK-tabellen i Joark (tidligere GSAK). For pensjons- og uføresaker vil arkivsaksystemet være PSAK. For alle andre sakstyper er arkivsaksystem GSAK.
     * * NB: Arkivsak skal anses som Joark-internt. Fagsystemene skal kun bruke denne etter avtale.
     */
    @Deprecated
    public void setArkivsaksystem(Arkivsaksystem arkivsaksystem) {
        this.arkivsaksystem = arkivsaksystem;
    }

    /**
     * Tidspunktet saken først ble opprettet/brukt i arkivet. Fagsaken kan være opprettet i fagsystemet på et annet tidspunkt.
     */
    public java.util.Date getDatoOpprettet() {
        return datoOpprettet;
    }
    /**
     * Tidspunktet saken først ble opprettet/brukt i arkivet. Fagsaken kan være opprettet i fagsystemet på et annet tidspunkt.
     */
    public void setDatoOpprettet(java.util.Date datoOpprettet) {
        this.datoOpprettet = datoOpprettet;
    }

    /**
     * Saksnummeret i fagsystemet
     */
    public String getFagsakId() {
        return fagsakId;
    }
    /**
     * Saksnummeret i fagsystemet
     */
    public void setFagsakId(String fagsakId) {
        this.fagsakId = fagsakId;
    }

    /**
     * Kode som indikerer hvilket fagsystem, eventuelt nummerserie for fagsaker, som fagsaken befinner seg i.
     */
    public String getFagsaksystem() {
        return fagsaksystem;
    }
    /**
     * Kode som indikerer hvilket fagsystem, eventuelt nummerserie for fagsaker, som fagsaken befinner seg i.
     */
    public void setFagsaksystem(String fagsaksystem) {
        this.fagsaksystem = fagsaksystem;
    }

    /**
     * Sier hvorvidt saken inngår i et fagsystem (FAGSAK) eller ikke (GENERELL_SAK).
     */
    public Sakstype getSakstype() {
        return sakstype;
    }
    /**
     * Sier hvorvidt saken inngår i et fagsystem (FAGSAK) eller ikke (GENERELL_SAK).
     */
    public void setSakstype(Sakstype sakstype) {
        this.sakstype = sakstype;
    }

    /**
     * Temaet/Fagområdet som journalposten og tilhørende sak tilhører, f.eks. "FOR".
     * * For sakstilknyttede journalposter, er det tema på SAK- eller PSAK-saken som er gjeldende tema.
     * * For journalposter som enda ikke har fått sakstilknytning, returneres tema på journalposten.inneholder Joark informasjon om antatt tema for journalposten.
     */
    public Tema getTema() {
        return tema;
    }
    /**
     * Temaet/Fagområdet som journalposten og tilhørende sak tilhører, f.eks. "FOR".
     * * For sakstilknyttede journalposter, er det tema på SAK- eller PSAK-saken som er gjeldende tema.
     * * For journalposter som enda ikke har fått sakstilknytning, returneres tema på journalposten.inneholder Joark informasjon om antatt tema for journalposten.
     */
    public void setTema(Tema tema) {
        this.tema = tema;
    }



}
