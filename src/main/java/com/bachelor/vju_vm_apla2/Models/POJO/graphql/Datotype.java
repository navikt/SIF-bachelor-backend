package com.bachelor.vju_vm_apla2.Models.POJO.graphql;

/**
 * Beskriver en type dato som kan være relevant for en journalpost, for eksempel **DATO_OPPRETTET**. Ulike datotyper returneres for ulike journalstatuser.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public enum Datotype {

    /**
     * * Tidspunktet journalposten er opprettet i joark. Tidspunktet settes automatisk og kan ikke overskrives. Selv om hver journalpost har mange datoer (se relevanteDatoer) er datoOpprettet å anse som "fasit" på journalpostens alder.
     * * Returneres for alle journalposter
     */
    DATO_OPPRETTET("DATO_OPPRETTET"),
    /**
     * * Tidspunktet dokumentene på journalposten ble sendt til print.
     * * Returneres for utgående journalposter
     */
    DATO_SENDT_PRINT("DATO_SENDT_PRINT"),
    /**
     * * Tidspunktet dokumentene på journalposten ble sendt til bruker.
     * * Returneres for utgående journalposter
     */
    DATO_EKSPEDERT("DATO_EKSPEDERT"),
    /**
     * * Tidspunktet journalposten ble journalført (inngående) eller ferdigstilt (utgående).
     * * Returneres for alle journalposttyper
     */
    DATO_JOURNALFOERT("DATO_JOURNALFOERT"),
    /**
     * * Tidspunkt dokumentene i journalposten ble registrert i NAV sine systemer.
     * * Returneres for inngående journalposter
     */
    DATO_REGISTRERT("DATO_REGISTRERT"),
    /**
     * * Tidspunkt som dokumentene i journalposten ble sendt på nytt, grunnet retur av opprinnelig forsendelse.
     * * Returneres for utgående journalposter
     */
    DATO_AVS_RETUR("DATO_AVS_RETUR"),
    /**
     * * Dato på hoveddokumentet i forsendelsen. Registreres i noen tilfeller manuelt av saksbehandler.
     * * Returneres for alle journalposter
     */
    DATO_DOKUMENT("DATO_DOKUMENT"),
    /**
     * * Tidspunkt som hoveddokumentet på journalposten ble lest på nav.no
     * * returneres for utgående journalposter
     */
    DATO_LEST("DATO_LEST");

    private final String graphqlName;

    private Datotype(String graphqlName) {
        this.graphqlName = graphqlName;
    }

    @Override
    public String toString() {
        return this.graphqlName;
    }

}
