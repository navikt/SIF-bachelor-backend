package com.bachelor.vju_vm_apla2.Models.POJO.graphql;

/**
 * Dokumentstatus gir en indikasjon på hvorvidt dokumentet er ferdigstilt eller under arbeid, eventuelt avbrutt. Dersom dokumentet ikke har noen dokumentstatus, er dokumentet komplett / ferdigstilt.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public enum Dokumentstatus {

    /**
     * Dokumentet er under arbeid. Benyttes for redigerbare brev.
     */
    UNDER_REDIGERING("UNDER_REDIGERING"),
    /**
     * Dokumentet er ferdigstilt. Benyttes for redigerbare brev.
     */
    FERDIGSTILT("FERDIGSTILT"),
    /**
     * Dokumentet ble opprettet, men ble avbrutt under redigering. Benyttes for redigerbare brev.
     */
    AVBRUTT("AVBRUTT"),
    /**
     * Dokumentet er kassert.
     */
    KASSERT("KASSERT");

    private final String graphqlName;

    private Dokumentstatus(String graphqlName) {
        this.graphqlName = graphqlName;
    }

    @Override
    public String toString() {
        return this.graphqlName;
    }

}
