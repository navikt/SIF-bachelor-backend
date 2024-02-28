package com.bachelor.vju_vm_apla2.Models.POJO.graphql;

/**
 * Forteller hvordan to eller flere journalposter er relatert til hverandre.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public enum Tilknytning {

    /**
     * Ved tilknytningstype 'Gjenbruk', deler to journalposter samme dokumentInfo og underliggende dokumenter. Dette vil typisk være tilfelle når et inngående dokument journalføres på flere saker/brukere.
     */
    GJENBRUK("GJENBRUK");

    private final String graphqlName;

    private Tilknytning(String graphqlName) {
        this.graphqlName = graphqlName;
    }

    @Override
    public String toString() {
        return this.graphqlName;
    }

}
