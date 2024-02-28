package com.bachelor.vju_vm_apla2.Models.POJO.graphql;

/**
 * Indikator på hvilken type id som brukes i spørringen.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public enum AvsenderMottakerIdType {

    /**
     * Folkeregisterets fødselsnummer eller d-nummer for en person.
     */
    FNR("FNR"),
    /**
     * Foretaksregisterets organisasjonsnummer for en juridisk person.
     */
    ORGNR("ORGNR"),
    /**
     * Helsepersonellregisterets identifikator for leger og annet helsepersonell.
     */
    HPRNR("HPRNR"),
    /**
     * Unik identifikator for utenlandske institusjoner / organisasjoner. Identifikatorene vedlikeholdes i EUs institusjonskatalog.
     */
    UTL_ORG("UTL_ORG"),
    /**
     * AvsenderMottakerId er tom
     */
    NULL("NULL"),
    /**
     * Ukjent IdType
     */
    UKJENT("UKJENT");

    private final String graphqlName;

    private AvsenderMottakerIdType(String graphqlName) {
        this.graphqlName = graphqlName;
    }

    @Override
    public String toString() {
        return this.graphqlName;
    }

}
