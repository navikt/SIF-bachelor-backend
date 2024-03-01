package com.bachelor.vju_vm_apla2.Models.POJO.graphql;


/**
 * Person eller organisasjon som har et forhold til NAV, f.eks. som mottaker av tjenester eller ytelser.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public class Bruker implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private BrukerIdType type;

    public Bruker() {
    }

    public Bruker(String id, BrukerIdType type) {
        this.id = id;
        this.type = type;
    }

    /**
     * Brukerens identifikator. For personbrukere returneres personens aktørID eller fødselsnummer. For organisasjonsbrukere returneres et organisasjonsnummer.
     */
    public String getId() {
        return id;
    }
    /**
     * Brukerens identifikator. For personbrukere returneres personens aktørID eller fødselsnummer. For organisasjonsbrukere returneres et organisasjonsnummer.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Angir hvilken type brukeren sin id er.
     */
    public BrukerIdType getType() {
        return type;
    }
    /**
     * Angir hvilken type brukeren sin id er.
     */
    public void setType(BrukerIdType type) {
        this.type = type;
    }



}
