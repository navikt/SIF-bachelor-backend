package com.bachelor.vju_vm_apla2.Models.POJO.Saf;

/**
 * Sier hvorvidt saken inngår i et fagsystem (FAGSAK) eller ikke (GENERELL_SAK).
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public enum Sakstype {

    /**
     * Benyttes normalt for dokumenter som ikke saksbehandles i et fagsystem. Generell sak har ikke saksnummer, men kan ses på som brukerens "mappe" av dokumenter på et gitt tema.
     */
    GENERELL_SAK("GENERELL_SAK"),
    /**
     * Vil si at saken tilhører et fagsystem. Hvilket fagsystem saken tilhører, finnes i feltet fagsaksystem.
     */
    FAGSAK("FAGSAK");

    private final String graphqlName;

    private Sakstype(String graphqlName) {
        this.graphqlName = graphqlName;
    }

    @Override
    public String toString() {
        return this.graphqlName;
    }

}
