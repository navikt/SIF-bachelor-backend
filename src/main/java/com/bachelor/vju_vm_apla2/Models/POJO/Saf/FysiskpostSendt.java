package com.bachelor.vju_vm_apla2.Models.POJO.Saf;


/**
 * Informasjon om distribusjon til fysisk post gjennom Posten Norge
 * Vil kun være satt hvis brevet er distribuert som fysisk post gjennom sentral utskrift
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public class FysiskpostSendt implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String adressetekstKonvolutt;

    public FysiskpostSendt() {
    }

    public FysiskpostSendt(String adressetekstKonvolutt) {
        this.adressetekstKonvolutt = adressetekstKonvolutt;
    }

    /**
     * Adressen som stod på eller i konvolutten som ble sendt til bruker.
     */
    public String getAdressetekstKonvolutt() {
        return adressetekstKonvolutt;
    }
    /**
     * Adressen som stod på eller i konvolutten som ble sendt til bruker.
     */
    public void setAdressetekstKonvolutt(String adressetekstKonvolutt) {
        this.adressetekstKonvolutt = adressetekstKonvolutt;
    }



}
