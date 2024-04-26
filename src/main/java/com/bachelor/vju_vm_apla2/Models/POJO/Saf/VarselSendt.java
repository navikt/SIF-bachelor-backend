package com.bachelor.vju_vm_apla2.Models.POJO.Saf;


/**
 * Informasjon om sms- og epost-varsel som er sendt til mottaker ved digital distribusjon
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public class VarselSendt implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String type;
    private String tittel;
    @jakarta.validation.constraints.NotNull
    private String adresse;
    @jakarta.validation.constraints.NotNull
    private String varslingstekst;
    private java.util.Date varslingstidspunkt;

    public VarselSendt() {
    }

    public VarselSendt(String type, String tittel, String adresse, String varslingstekst, java.util.Date varslingstidspunkt) {
        this.type = type;
        this.tittel = tittel;
        this.adresse = adresse;
        this.varslingstekst = varslingstekst;
        this.varslingstidspunkt = varslingstidspunkt;
    }

    /**
     * Type varsel som er sendt til mottaker.
     * "EPOST" dersom epost-varsel eller "SMS" dersom sms-varsel
     */
    public String getType() {
        return type;
    }
    /**
     * Type varsel som er sendt til mottaker.
     * "EPOST" dersom epost-varsel eller "SMS" dersom sms-varsel
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Tittel på epost hvis epost-varsel. Vil være null hvis sms-varsel.
     */
    public String getTittel() {
        return tittel;
    }
    /**
     * Tittel på epost hvis epost-varsel. Vil være null hvis sms-varsel.
     */
    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    /**
     * Adressen varsel ble sendt til.
     * Kan være en epostadresse (hvis epostvarsel) eller et telefonnummer (hvis smsvarsel).
     */
    public String getAdresse() {
        return adresse;
    }
    /**
     * Adressen varsel ble sendt til.
     * Kan være en epostadresse (hvis epostvarsel) eller et telefonnummer (hvis smsvarsel).
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Innhold i varsel som er sendt til mottaker. Kan inneholde HTML.
     */
    public String getVarslingstekst() {
        return varslingstekst;
    }
    /**
     * Innhold i varsel som er sendt til mottaker. Kan inneholde HTML.
     */
    public void setVarslingstekst(String varslingstekst) {
        this.varslingstekst = varslingstekst;
    }

    /**
     * Tidspunkt når varselet ble sendt. Lokal norsk tid.
     */
    public java.util.Date getVarslingstidspunkt() {
        return varslingstidspunkt;
    }
    /**
     * Tidspunkt når varselet ble sendt. Lokal norsk tid.
     */
    public void setVarslingstidspunkt(java.util.Date varslingstidspunkt) {
        this.varslingstidspunkt = varslingstidspunkt;
    }



}
