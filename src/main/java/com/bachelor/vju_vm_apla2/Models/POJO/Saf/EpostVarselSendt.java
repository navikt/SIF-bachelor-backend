package com.bachelor.vju_vm_apla2.Models.POJO.Saf;


/**
 * Epostvarsel sendt til bruker ved distribusjon til nav.no
 * Vil kun være satt hvis brevet er distribuert til nav.no
 * Deprecated: bruk VarselSendt
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public class EpostVarselSendt implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String tittel;
    @jakarta.validation.constraints.NotNull
    private String adresse;
    @jakarta.validation.constraints.NotNull
    private String varslingstekst;

    public EpostVarselSendt() {
    }

    public EpostVarselSendt(String tittel, String adresse, String varslingstekst) {
        this.tittel = tittel;
        this.adresse = adresse;
        this.varslingstekst = varslingstekst;
    }

    /**
     * Tittel på eposten som ble sendt til bruker.
     */
    public String getTittel() {
        return tittel;
    }
    /**
     * Tittel på eposten som ble sendt til bruker.
     */
    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    /**
     * Epostadressen som varselet ble sendt til.
     */
    public String getAdresse() {
        return adresse;
    }
    /**
     * Epostadressen som varselet ble sendt til.
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Teksten i varslet. Kan inneholde HTML.
     */
    public String getVarslingstekst() {
        return varslingstekst;
    }
    /**
     * Teksten i varslet. Kan inneholde HTML.
     */
    public void setVarslingstekst(String varslingstekst) {
        this.varslingstekst = varslingstekst;
    }



}
