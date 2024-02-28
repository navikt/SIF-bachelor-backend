package com.bachelor.vju_vm_apla2.Models.POJO.graphql;


/**
 * SMS varsel sendt til bruker ved distribusjon til nav.no
 * Vil kun være satt hvis brevet er distribuert til nav.no
 * Deprecated: bruk VarselSendt
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public class SmsVarselSendt implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String adresse;
    @jakarta.validation.constraints.NotNull
    private String varslingstekst;

    public SmsVarselSendt() {
    }

    public SmsVarselSendt(String adresse, String varslingstekst) {
        this.adresse = adresse;
        this.varslingstekst = varslingstekst;
    }

    /**
     * Mobilnummeret som varselet ble sendt til.
     */
    public String getAdresse() {
        return adresse;
    }
    /**
     * Mobilnummeret som varselet ble sendt til.
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Teksten i varselet.
     */
    public String getVarslingstekst() {
        return varslingstekst;
    }
    /**
     * Teksten i varselet.
     */
    public void setVarslingstekst(String varslingstekst) {
        this.varslingstekst = varslingstekst;
    }



}
