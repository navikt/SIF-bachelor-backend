package com.bachelor.vju_vm_apla2.Models.POJO.Saf;


/**
 * Informasjon om distribusjon til Digital postkasse til Innbyggere
 * Vil kun være satt hvis brevet er distribuert til DPI
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public class DigitalpostSendt implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String adresse;

    public DigitalpostSendt() {
    }

    public DigitalpostSendt(String adresse) {
        this.adresse = adresse;
    }

    /**
     * DPI leverandør sin adresse som brevet ble sendt til.
     */
    public String getAdresse() {
        return adresse;
    }
    /**
     * DPI leverandør sin adresse som brevet ble sendt til.
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }



}
