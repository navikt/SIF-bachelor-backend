package com.bachelor.vju_vm_apla2.Models.POJO.Saf;

/**
 * Indikator på hvilken type id som brukes i spørringen.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public enum BrukerIdType {

    /**
     * NAV aktørid for en person.
     */
    AKTOERID("AKTOERID"),
    /**
     * Folkeregisterets fødselsnummer eller d-nummer for en person.
     */
    FNR("FNR"),
    /**
     * Foretaksregisterets organisasjonsnummer for en juridisk person.
     */
    ORGNR("ORGNR");

    private final String graphqlName;

    private BrukerIdType(String graphqlName) {
        this.graphqlName = graphqlName;
    }

    @Override
    public String toString() {
        return this.graphqlName;
    }

}
