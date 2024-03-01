package com.bachelor.vju_vm_apla2.Models.POJO.graphql;

/**
 * Indikerer hvor man kan finne saksparten som dokumentene er journalført mot, samt en peker til selve fagsaken, dersom det finnes en.
 * * For pensjons- og uføresaker vil arkivsaksystemet være PSAK. For alle andre sakstyper er arkivsaksystem GSAK.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public enum Arkivsaksystem {

    /**
     * Arkivsaksystem for alle NAV saker unntatt pensjon og uføre.
     */
    GSAK("GSAK"),
    /**
     * Arkivsaksystem for pensjon og uføre.
     */
    PSAK("PSAK");

    private final String graphqlName;

    private Arkivsaksystem(String graphqlName) {
        this.graphqlName = graphqlName;
    }

    @Override
    public String toString() {
        return this.graphqlName;
    }

}
