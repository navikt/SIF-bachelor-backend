package com.bachelor.vju_vm_apla2.Models.POJO.Saf;


/**
 * Et sett med metadata som er nødvendig for å gjenfinne et dokument i arkivet. En journalpost kan ha ett eller flere dokumenter.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public class Journalpost implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String journalpostId;
    private String tittel;
    private Journalposttype journalposttype;
    private Journalstatus journalstatus;
    private Tema tema;
    private String temanavn;
    private String behandlingstema;
    private String behandlingstemanavn;
    private Sak sak;
    private Bruker bruker;
    private AvsenderMottaker avsenderMottaker;
    @Deprecated
    private String avsenderMottakerId;
    @Deprecated
    private String avsenderMottakerNavn;
    @Deprecated
    private String avsenderMottakerLand;
    @Deprecated
    private String journalforendeEnhet;
    private String journalfoerendeEnhet;
    private String journalfortAvNavn;
    private String opprettetAvNavn;
    private Kanal kanal;
    private String kanalnavn;
    private String skjerming;
    @jakarta.validation.constraints.NotNull
    private java.util.Date datoOpprettet;
    private java.util.List<RelevantDato> relevanteDatoer;
    private String antallRetur;
    private String eksternReferanseId;
    private Utsendingsinfo utsendingsinfo;
    private java.util.List<Tilleggsopplysning> tilleggsopplysninger;
    private java.util.List<DokumentInfo> dokumenter;

    public Journalpost() {
    }

    public Journalpost(String journalpostId, String tittel, Journalposttype journalposttype, Journalstatus journalstatus, Tema tema, String temanavn, String behandlingstema, String behandlingstemanavn, Sak sak, Bruker bruker, AvsenderMottaker avsenderMottaker, String avsenderMottakerId, String avsenderMottakerNavn, String avsenderMottakerLand, String journalforendeEnhet, String journalfoerendeEnhet, String journalfortAvNavn, String opprettetAvNavn, Kanal kanal, String kanalnavn, String skjerming, java.util.Date datoOpprettet, java.util.List<RelevantDato> relevanteDatoer, String antallRetur, String eksternReferanseId, Utsendingsinfo utsendingsinfo, java.util.List<Tilleggsopplysning> tilleggsopplysninger, java.util.List<DokumentInfo> dokumenter) {
        this.journalpostId = journalpostId;
        this.tittel = tittel;
        this.journalposttype = journalposttype;
        this.journalstatus = journalstatus;
        this.tema = tema;
        this.temanavn = temanavn;
        this.behandlingstema = behandlingstema;
        this.behandlingstemanavn = behandlingstemanavn;
        this.sak = sak;
        this.bruker = bruker;
        this.avsenderMottaker = avsenderMottaker;
        this.avsenderMottakerId = avsenderMottakerId;
        this.avsenderMottakerNavn = avsenderMottakerNavn;
        this.avsenderMottakerLand = avsenderMottakerLand;
        this.journalforendeEnhet = journalforendeEnhet;
        this.journalfoerendeEnhet = journalfoerendeEnhet;
        this.journalfortAvNavn = journalfortAvNavn;
        this.opprettetAvNavn = opprettetAvNavn;
        this.kanal = kanal;
        this.kanalnavn = kanalnavn;
        this.skjerming = skjerming;
        this.datoOpprettet = datoOpprettet;
        this.relevanteDatoer = relevanteDatoer;
        this.antallRetur = antallRetur;
        this.eksternReferanseId = eksternReferanseId;
        this.utsendingsinfo = utsendingsinfo;
        this.tilleggsopplysninger = tilleggsopplysninger;
        this.dokumenter = dokumenter;
    }

    /**
     * Unik identifikator per Journalpost
     */
    public String getJournalpostId() {
        return journalpostId;
    }
    /**
     * Unik identifikator per Journalpost
     */
    public void setJournalpostId(String journalpostId) {
        this.journalpostId = journalpostId;
    }

    /**
     * Beskriver innholdet i journalposten samlet, f.eks. "Ettersendelse til søknad om foreldrepenger"
     */
    public String getTittel() {
        return tittel;
    }
    /**
     * Beskriver innholdet i journalposten samlet, f.eks. "Ettersendelse til søknad om foreldrepenger"
     */
    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    /**
     * Sier hvorvidt journalposten er et inngående dokument, et utgående dokument eller et notat.
     */
    public Journalposttype getJournalposttype() {
        return journalposttype;
    }
    /**
     * Sier hvorvidt journalposten er et inngående dokument, et utgående dokument eller et notat.
     */
    public void setJournalposttype(Journalposttype journalposttype) {
        this.journalposttype = journalposttype;
    }

    /**
     * Status på journalposten i joark, f.eks. MOTTATT eller JOURNALFØRT. Journalstatusen gir et indikasjon på hvor i journalførings- eller dokumentproduksjonsprosessen journalposten befinner seg.
     * * Journalposter som er resultat av en feilsituasjon og ikke skal hensyntas for saksbehandling har egne koder, som UTGAAR eller AVBRUTT.
     */
    public Journalstatus getJournalstatus() {
        return journalstatus;
    }
    /**
     * Status på journalposten i joark, f.eks. MOTTATT eller JOURNALFØRT. Journalstatusen gir et indikasjon på hvor i journalførings- eller dokumentproduksjonsprosessen journalposten befinner seg.
     * * Journalposter som er resultat av en feilsituasjon og ikke skal hensyntas for saksbehandling har egne koder, som UTGAAR eller AVBRUTT.
     */
    public void setJournalstatus(Journalstatus journalstatus) {
        this.journalstatus = journalstatus;
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

    /**
     * Dekode av `Tema`, f.eks. "Foreldrepenger"
     */
    public String getTemanavn() {
        return temanavn;
    }
    /**
     * Dekode av `Tema`, f.eks. "Foreldrepenger"
     */
    public void setTemanavn(String temanavn) {
        this.temanavn = temanavn;
    }

    /**
     * Detaljering av tema på journalpost og tilhørende sak, f.eks. "ab0072".
     */
    public String getBehandlingstema() {
        return behandlingstema;
    }
    /**
     * Detaljering av tema på journalpost og tilhørende sak, f.eks. "ab0072".
     */
    public void setBehandlingstema(String behandlingstema) {
        this.behandlingstema = behandlingstema;
    }

    /**
     * Dekode av behandlingstema, f.eks "Foreldrepenger ved adopsjon"
     */
    public String getBehandlingstemanavn() {
        return behandlingstemanavn;
    }
    /**
     * Dekode av behandlingstema, f.eks "Foreldrepenger ved adopsjon"
     */
    public void setBehandlingstemanavn(String behandlingstemanavn) {
        this.behandlingstemanavn = behandlingstemanavn;
    }

    /**
     * Sier hvilken sak journalposten er knyttet til. En journalpost kan maksimalt være knyttet til én sak, men et dokument kan være knyttet til flere journalposter og dermed flere saker.
     */
    public Sak getSak() {
        return sak;
    }
    /**
     * Sier hvilken sak journalposten er knyttet til. En journalpost kan maksimalt være knyttet til én sak, men et dokument kan være knyttet til flere journalposter og dermed flere saker.
     */
    public void setSak(Sak sak) {
        this.sak = sak;
    }

    /**
     * * Personen eller organisasjonen som dokumentene i journalposten gjelder.
     * * Dersom journalposten er sakstilknyttet, henter SAF bruker fra GSAK/PSAK. Alternativt henter SAF den fra Joark.
     */
    public Bruker getBruker() {
        return bruker;
    }
    /**
     * * Personen eller organisasjonen som dokumentene i journalposten gjelder.
     * * Dersom journalposten er sakstilknyttet, henter SAF bruker fra GSAK/PSAK. Alternativt henter SAF den fra Joark.
     */
    public void setBruker(Bruker bruker) {
        this.bruker = bruker;
    }

    /**
     * Personen eller organisasjonen som er avsender eller mottaker av dokumentene i journalposten.
     */
    public AvsenderMottaker getAvsenderMottaker() {
        return avsenderMottaker;
    }
    /**
     * Personen eller organisasjonen som er avsender eller mottaker av dokumentene i journalposten.
     */
    public void setAvsenderMottaker(AvsenderMottaker avsenderMottaker) {
        this.avsenderMottaker = avsenderMottaker;
    }

    /**
     * Identifikatoren til parten som er avsender eller mottaker av dokumentene på journalposten. Enten fødselsnummer eller organisasjonsnummer.
     */
    @Deprecated
    public String getAvsenderMottakerId() {
        return avsenderMottakerId;
    }
    /**
     * Identifikatoren til parten som er avsender eller mottaker av dokumentene på journalposten. Enten fødselsnummer eller organisasjonsnummer.
     */
    @Deprecated
    public void setAvsenderMottakerId(String avsenderMottakerId) {
        this.avsenderMottakerId = avsenderMottakerId;
    }

    /**
     * Navnet på personen eller organisasjonen som er avsender eller mottaker av dokumentene på journalposten.
     */
    @Deprecated
    public String getAvsenderMottakerNavn() {
        return avsenderMottakerNavn;
    }
    /**
     * Navnet på personen eller organisasjonen som er avsender eller mottaker av dokumentene på journalposten.
     */
    @Deprecated
    public void setAvsenderMottakerNavn(String avsenderMottakerNavn) {
        this.avsenderMottakerNavn = avsenderMottakerNavn;
    }

    /**
     * Landet forsendelsen er mottatt fra eller sendt til. Feltet skal i utgangspunktet kun være populert dersom avsender eller mottaker er en institusjon med adresse i utlandet.
     */
    @Deprecated
    public String getAvsenderMottakerLand() {
        return avsenderMottakerLand;
    }
    /**
     * Landet forsendelsen er mottatt fra eller sendt til. Feltet skal i utgangspunktet kun være populert dersom avsender eller mottaker er en institusjon med adresse i utlandet.
     */
    @Deprecated
    public void setAvsenderMottakerLand(String avsenderMottakerLand) {
        this.avsenderMottakerLand = avsenderMottakerLand;
    }

    /**
     * NAV-enheten som har journalført forsendelsen. I noen tilfeller brukes journalfEnhet til å rute journalføringsoppgaven til korrekt enhet i NAV. I slike tilfeller vil journalfEnhet være satt også for ikke-journalførte dokumenter.
     */
    @Deprecated
    public String getJournalforendeEnhet() {
        return journalforendeEnhet;
    }
    /**
     * NAV-enheten som har journalført forsendelsen. I noen tilfeller brukes journalfEnhet til å rute journalføringsoppgaven til korrekt enhet i NAV. I slike tilfeller vil journalfEnhet være satt også for ikke-journalførte dokumenter.
     */
    @Deprecated
    public void setJournalforendeEnhet(String journalforendeEnhet) {
        this.journalforendeEnhet = journalforendeEnhet;
    }

    /**
     * NAV-enheten som har journalført forsendelsen. I noen tilfeller brukes journalfEnhet til å rute journalføringsoppgaven til korrekt enhet i NAV. I slike tilfeller vil journalfEnhet være satt også for ikke-journalførte dokumenter.
     */
    public String getJournalfoerendeEnhet() {
        return journalfoerendeEnhet;
    }
    /**
     * NAV-enheten som har journalført forsendelsen. I noen tilfeller brukes journalfEnhet til å rute journalføringsoppgaven til korrekt enhet i NAV. I slike tilfeller vil journalfEnhet være satt også for ikke-journalførte dokumenter.
     */
    public void setJournalfoerendeEnhet(String journalfoerendeEnhet) {
        this.journalfoerendeEnhet = journalfoerendeEnhet;
    }

    /**
     * Personen eller systembrukeren i NAV som har journalført forsendelsen.
     * * Bruken av feltet varierer, og kan inneholde den ansattes navn eller NAV-ident. Dersom forsendelsen er automatisk journalført, kan innholdet være f.eks. en servicebruker eller et batchnavn.
     */
    public String getJournalfortAvNavn() {
        return journalfortAvNavn;
    }
    /**
     * Personen eller systembrukeren i NAV som har journalført forsendelsen.
     * * Bruken av feltet varierer, og kan inneholde den ansattes navn eller NAV-ident. Dersom forsendelsen er automatisk journalført, kan innholdet være f.eks. en servicebruker eller et batchnavn.
     */
    public void setJournalfortAvNavn(String journalfortAvNavn) {
        this.journalfortAvNavn = journalfortAvNavn;
    }

    /**
     * Personen eller systembrukeren i NAV som har opprettet journalposten.
     * * Bruken av feltet varierer, og kan inneholde den ansattes navn eller NAV-ident. For inngående dokumenter kan innholdet være f.eks. en servicebruker eller et batchnavn.
     */
    public String getOpprettetAvNavn() {
        return opprettetAvNavn;
    }
    /**
     * Personen eller systembrukeren i NAV som har opprettet journalposten.
     * * Bruken av feltet varierer, og kan inneholde den ansattes navn eller NAV-ident. For inngående dokumenter kan innholdet være f.eks. en servicebruker eller et batchnavn.
     */
    public void setOpprettetAvNavn(String opprettetAvNavn) {
        this.opprettetAvNavn = opprettetAvNavn;
    }

    /**
     * Kanalen dokumentene ble mottatt i eller sendt ut på f.eks. "SENTRAL_UTSKRIFT" eller "ALTINN".
     * * Dersom journalposten ikke har noen kjent kanal, returneres verdien "UKJENT"
     */
    public Kanal getKanal() {
        return kanal;
    }
    /**
     * Kanalen dokumentene ble mottatt i eller sendt ut på f.eks. "SENTRAL_UTSKRIFT" eller "ALTINN".
     * * Dersom journalposten ikke har noen kjent kanal, returneres verdien "UKJENT"
     */
    public void setKanal(Kanal kanal) {
        this.kanal = kanal;
    }

    /**
     * Dekode av `Kanal`, f.eks "Sentral utskrift"
     */
    public String getKanalnavn() {
        return kanalnavn;
    }
    /**
     * Dekode av `Kanal`, f.eks "Sentral utskrift"
     */
    public void setKanalnavn(String kanalnavn) {
        this.kanalnavn = kanalnavn;
    }

    /**
     * Utrykker at tilgangen til alle journalpost data for denne journalposten er begrenset, og at dataene ikke skal brukes i ordinær saksbehandling.
     */
    public String getSkjerming() {
        return skjerming;
    }
    /**
     * Utrykker at tilgangen til alle journalpost data for denne journalposten er begrenset, og at dataene ikke skal brukes i ordinær saksbehandling.
     */
    public void setSkjerming(String skjerming) {
        this.skjerming = skjerming;
    }

    /**
     * Datoen journalposten ble opprettet i arkivet. Datoen settes automatisk og kan ikke overskrives. Selv om hver journalpost har mange datoer (se `RelevantDato`) er datoOpprettet å anse som "fasit" på journalpostens alder.
     */
    public java.util.Date getDatoOpprettet() {
        return datoOpprettet;
    }
    /**
     * Datoen journalposten ble opprettet i arkivet. Datoen settes automatisk og kan ikke overskrives. Selv om hver journalpost har mange datoer (se `RelevantDato`) er datoOpprettet å anse som "fasit" på journalpostens alder.
     */
    public void setDatoOpprettet(java.util.Date datoOpprettet) {
        this.datoOpprettet = datoOpprettet;
    }

    /**
     * Liste over datoer som kan være relevante for denne journalposten, f.eks. DATO_EKSPEDERT. Hvilke relevante datoer som returneres, avhenger av journalposttypen.
     */
    public java.util.List<RelevantDato> getRelevanteDatoer() {
        return relevanteDatoer;
    }
    /**
     * Liste over datoer som kan være relevante for denne journalposten, f.eks. DATO_EKSPEDERT. Hvilke relevante datoer som returneres, avhenger av journalposttypen.
     */
    public void setRelevanteDatoer(java.util.List<RelevantDato> relevanteDatoer) {
        this.relevanteDatoer = relevanteDatoer;
    }

    /**
     * Antall ganger brevet har vært forsøkt sendt til bruker og deretter kommet i retur til NAV. Vil kun være satt for utgående forsendelser.
     */
    public String getAntallRetur() {
        return antallRetur;
    }
    /**
     * Antall ganger brevet har vært forsøkt sendt til bruker og deretter kommet i retur til NAV. Vil kun være satt for utgående forsendelser.
     */
    public void setAntallRetur(String antallRetur) {
        this.antallRetur = antallRetur;
    }

    /**
     * Brukes for sporing og feilsøking på tvers av systemer.
     * Eksempler på eksternReferanseId kan være sykmeldingsId for sykmeldinger, Altinn ArchiveReference for Altinn-skjema eller SEDid for SED.
     */
    public String getEksternReferanseId() {
        return eksternReferanseId;
    }
    /**
     * Brukes for sporing og feilsøking på tvers av systemer.
     * Eksempler på eksternReferanseId kan være sykmeldingsId for sykmeldinger, Altinn ArchiveReference for Altinn-skjema eller SEDid for SED.
     */
    public void setEksternReferanseId(String eksternReferanseId) {
        this.eksternReferanseId = eksternReferanseId;
    }

    /**
     * Metadata om distribusjon av utgående journalpost.
     * * Forteller hvilken adresse en utgående forsendelse er distribuert til (digital postkasse eller fysisk post)
     * * Eller hvilken epost/telefonnummer og varseltekst, varsel fra nav.no er sendt til
     * * Returneres kun for utgående journalposter
     */
    public Utsendingsinfo getUtsendingsinfo() {
        return utsendingsinfo;
    }
    /**
     * Metadata om distribusjon av utgående journalpost.
     * * Forteller hvilken adresse en utgående forsendelse er distribuert til (digital postkasse eller fysisk post)
     * * Eller hvilken epost/telefonnummer og varseltekst, varsel fra nav.no er sendt til
     * * Returneres kun for utgående journalposter
     */
    public void setUtsendingsinfo(Utsendingsinfo utsendingsinfo) {
        this.utsendingsinfo = utsendingsinfo;
    }

    /**
     * Liste over fagspesifikke metadata som er tilknyttet journalpost.
     */
    public java.util.List<Tilleggsopplysning> getTilleggsopplysninger() {
        return tilleggsopplysninger;
    }
    /**
     * Liste over fagspesifikke metadata som er tilknyttet journalpost.
     */
    public void setTilleggsopplysninger(java.util.List<Tilleggsopplysning> tilleggsopplysninger) {
        this.tilleggsopplysninger = tilleggsopplysninger;
    }

    /**
     * Liste over dokumentinfo tilknyttet journalposten.
     * * Dokumentene returneres i følgende sorteringsrekkefølge: Hoveddokumentet først, deretter vedleggene i tilfeldig rekkefølge.
     */
    public java.util.List<DokumentInfo> getDokumenter() {
        return dokumenter;
    }
    /**
     * Liste over dokumentinfo tilknyttet journalposten.
     * * Dokumentene returneres i følgende sorteringsrekkefølge: Hoveddokumentet først, deretter vedleggene i tilfeldig rekkefølge.
     */
    public void setDokumenter(java.util.List<DokumentInfo> dokumenter) {
        this.dokumenter = dokumenter;
    }



}
