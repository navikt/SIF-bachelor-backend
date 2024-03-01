package com.bachelor.vju_vm_apla2.Models.POJO.graphql;


/**
 * En person, organisasjon eller annen samhandler som er mottaker eller avsender av dokumentene på en journalpost.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public class AvsenderMottaker implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private AvsenderMottakerIdType type;
    private String navn;
    private String land;
    private boolean erLikBruker;

    public AvsenderMottaker() {
    }

    public AvsenderMottaker(String id, AvsenderMottakerIdType type, String navn, String land, boolean erLikBruker) {
        this.id = id;
        this.type = type;
        this.navn = navn;
        this.land = land;
        this.erLikBruker = erLikBruker;
    }

    /**
     * Identifikatoren til parten som er avsender eller mottaker av dokumentene på journalposten.
     * * Normalt et fødselsnummer eller organisasjonsnummer.
     */
    public String getId() {
        return id;
    }
    /**
     * Identifikatoren til parten som er avsender eller mottaker av dokumentene på journalposten.
     * * Normalt et fødselsnummer eller organisasjonsnummer.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Identifikatortypen til parten som er avsender eller mottaker av dokumentene på journalposten.
     */
    public AvsenderMottakerIdType getType() {
        return type;
    }
    /**
     * Identifikatortypen til parten som er avsender eller mottaker av dokumentene på journalposten.
     */
    public void setType(AvsenderMottakerIdType type) {
        this.type = type;
    }

    /**
     * Navnet på personen eller organisasjonen som er avsender eller mottaker av dokumentene på journalposten.
     */
    public String getNavn() {
        return navn;
    }
    /**
     * Navnet på personen eller organisasjonen som er avsender eller mottaker av dokumentene på journalposten.
     */
    public void setNavn(String navn) {
        this.navn = navn;
    }

    /**
     * Landet forsendelsen er mottatt fra eller sendt til. Feltet brukes kun dersom avsender / mottaker er en institusjon med adresse i utlandet.
     */
    public String getLand() {
        return land;
    }
    /**
     * Landet forsendelsen er mottatt fra eller sendt til. Feltet brukes kun dersom avsender / mottaker er en institusjon med adresse i utlandet.
     */
    public void setLand(String land) {
        this.land = land;
    }

    /**
     * Angir hvorvidt bruker er lik avsender/mottaker av journalposten.
     * * Informasjonen er ikke garantert korrekt, da tjenesten sammenlikner avsenderMottaker med bruker tilknyttet journalposten, ikke bruker tilknyttet saken.
     */
    public boolean getErLikBruker() {
        return erLikBruker;
    }
    /**
     * Angir hvorvidt bruker er lik avsender/mottaker av journalposten.
     * * Informasjonen er ikke garantert korrekt, da tjenesten sammenlikner avsenderMottaker med bruker tilknyttet journalposten, ikke bruker tilknyttet saken.
     */
    public void setErLikBruker(boolean erLikBruker) {
        this.erLikBruker = erLikBruker;
    }



}
