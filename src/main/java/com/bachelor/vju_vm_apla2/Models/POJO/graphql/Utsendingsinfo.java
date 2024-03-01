package com.bachelor.vju_vm_apla2.Models.POJO.graphql;


/**
 * Metadata om distribusjon av utgående journalpost.
 * * Forteller hvilken adresse en utgående forsendelse er distribuert til (digital postkasse eller fysisk post)
 * * Eller hvilken epost/telefonnummer og varseltekst, varsel fra nav.no er sendt til
 * * Returneres kun for utgående journalposter
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public class Utsendingsinfo implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private java.util.List<VarselSendt> varselSendt;
    @Deprecated
    private EpostVarselSendt epostVarselSendt;
    @Deprecated
    private SmsVarselSendt smsVarselSendt;
    private FysiskpostSendt fysiskpostSendt;
    private DigitalpostSendt digitalpostSendt;

    public Utsendingsinfo() {
    }

    public Utsendingsinfo(java.util.List<VarselSendt> varselSendt, EpostVarselSendt epostVarselSendt, SmsVarselSendt smsVarselSendt, FysiskpostSendt fysiskpostSendt, DigitalpostSendt digitalpostSendt) {
        this.varselSendt = varselSendt;
        this.epostVarselSendt = epostVarselSendt;
        this.smsVarselSendt = smsVarselSendt;
        this.fysiskpostSendt = fysiskpostSendt;
        this.digitalpostSendt = digitalpostSendt;
    }

    /**
     * Informasjon om varsel som er sendt til mottaker ved digital distribusjon.
     * Sortert slik at nyeste varsel kommer først
     */
    public java.util.List<VarselSendt> getVarselSendt() {
        return varselSendt;
    }
    /**
     * Informasjon om varsel som er sendt til mottaker ved digital distribusjon.
     * Sortert slik at nyeste varsel kommer først
     */
    public void setVarselSendt(java.util.List<VarselSendt> varselSendt) {
        this.varselSendt = varselSendt;
    }

    /**
     * Epostvarsel sendt til bruker ved distribusjon til nav.no
     * Vil kun være satt hvis brevet er distribuert til nav.no
     * Dette feltet vil fjernes og erstattes med varselSendt
     */
    @Deprecated
    public EpostVarselSendt getEpostVarselSendt() {
        return epostVarselSendt;
    }
    /**
     * Epostvarsel sendt til bruker ved distribusjon til nav.no
     * Vil kun være satt hvis brevet er distribuert til nav.no
     * Dette feltet vil fjernes og erstattes med varselSendt
     */
    @Deprecated
    public void setEpostVarselSendt(EpostVarselSendt epostVarselSendt) {
        this.epostVarselSendt = epostVarselSendt;
    }

    /**
     * SMS varsel sendt til bruker ved distribusjon til nav.no
     * Vil kun være satt hvis brevet er distribuert til nav.no
     * Dette feltet vil fjernes og erstattes med varselSendt
     */
    @Deprecated
    public SmsVarselSendt getSmsVarselSendt() {
        return smsVarselSendt;
    }
    /**
     * SMS varsel sendt til bruker ved distribusjon til nav.no
     * Vil kun være satt hvis brevet er distribuert til nav.no
     * Dette feltet vil fjernes og erstattes med varselSendt
     */
    @Deprecated
    public void setSmsVarselSendt(SmsVarselSendt smsVarselSendt) {
        this.smsVarselSendt = smsVarselSendt;
    }

    /**
     * Informasjon om distribusjon til fysisk post gjennom Posten Norge
     * Vil kun være satt hvis brevet er distribuert som fysisk post gjennom sentral utskrift
     */
    public FysiskpostSendt getFysiskpostSendt() {
        return fysiskpostSendt;
    }
    /**
     * Informasjon om distribusjon til fysisk post gjennom Posten Norge
     * Vil kun være satt hvis brevet er distribuert som fysisk post gjennom sentral utskrift
     */
    public void setFysiskpostSendt(FysiskpostSendt fysiskpostSendt) {
        this.fysiskpostSendt = fysiskpostSendt;
    }

    /**
     * Informasjon om distribusjon til Digital postkasse til Innbyggere(DPI)
     * Vil kun være satt hvis brevet er distribuert til DPI
     */
    public DigitalpostSendt getDigitalpostSendt() {
        return digitalpostSendt;
    }
    /**
     * Informasjon om distribusjon til Digital postkasse til Innbyggere(DPI)
     * Vil kun være satt hvis brevet er distribuert til DPI
     */
    public void setDigitalpostSendt(DigitalpostSendt digitalpostSendt) {
        this.digitalpostSendt = digitalpostSendt;
    }



}
