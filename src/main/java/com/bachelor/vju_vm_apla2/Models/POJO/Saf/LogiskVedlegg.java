package com.bachelor.vju_vm_apla2.Models.POJO.Saf;


/**
 * Et dokument som forekommer inne i en fysisk fil med et annet navn og hovedinnhold.
 * * Dette skjer hyppig under skanning av papirpost, fordi dokumenter mottatt i samme konvolutt skannes i én operasjon, og ender opp som én fil i Joark.
 * * En bruker sender inn en papirsøknad om foreldrepenger, med en legeerklæring som vedlegg. Journalposten vil da ha ett dokument med navn *"Søknad om foreldrepenger ved fødsel"*, og det logiske vedlegget *"Legerklæring"*.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public class LogiskVedlegg implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String logiskVedleggId;
    private String tittel;

    public LogiskVedlegg() {
    }

    public LogiskVedlegg(String logiskVedleggId, String tittel) {
        this.logiskVedleggId = logiskVedleggId;
        this.tittel = tittel;
    }

    /**
     * Unik identifikator per logisk vedlegg
     */
    public String getLogiskVedleggId() {
        return logiskVedleggId;
    }
    /**
     * Unik identifikator per logisk vedlegg
     */
    public void setLogiskVedleggId(String logiskVedleggId) {
        this.logiskVedleggId = logiskVedleggId;
    }

    /**
     * Tittel på det logiske vedlegget, f.eks. *"Legeerklæring"*
     */
    public String getTittel() {
        return tittel;
    }
    /**
     * Tittel på det logiske vedlegget, f.eks. *"Legeerklæring"*
     */
    public void setTittel(String tittel) {
        this.tittel = tittel;
    }



}
