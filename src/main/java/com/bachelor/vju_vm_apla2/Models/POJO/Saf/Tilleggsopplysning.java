package com.bachelor.vju_vm_apla2.Models.POJO.Saf;


/**
 * * Fagsystemene som arkiverer kan legge til egne fagspesifikke attributter per journalpost.
 * * Disse er representert som et skjemaløst nøkkel-verdi-sett og valideres ikke av Joark. Et eksempel på et slikt sett kan være (bucid, 21521).
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public class Tilleggsopplysning implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String nokkel;
    private String verdi;

    public Tilleggsopplysning() {
    }

    public Tilleggsopplysning(String nokkel, String verdi) {
        this.nokkel = nokkel;
        this.verdi = verdi;
    }

    /**
     * Nøkkelen til det fagspesifikke attributtet.
     */
    public String getNokkel() {
        return nokkel;
    }
    /**
     * Nøkkelen til det fagspesifikke attributtet.
     */
    public void setNokkel(String nokkel) {
        this.nokkel = nokkel;
    }

    /**
     * Verdien til det fagspesifikke attributtet.
     */
    public String getVerdi() {
        return verdi;
    }
    /**
     * Verdien til det fagspesifikke attributtet.
     */
    public void setVerdi(String verdi) {
        this.verdi = verdi;
    }



}
