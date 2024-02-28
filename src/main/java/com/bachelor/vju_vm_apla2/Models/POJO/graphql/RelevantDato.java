package com.bachelor.vju_vm_apla2.Models.POJO.graphql;


/**
 * Dato som kan være relevant for en journalpost. De ulike journalposttypene (inngående, utgående og notat) får returnert ulike relevante datoer.
 * * For eksempel er **DATO_EKSPEDERT** kun relevant for utgående dokumenter, og **DATO_REGISTRERT** kun for inngående.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public class RelevantDato implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private java.util.Date dato;
    @jakarta.validation.constraints.NotNull
    private Datotype datotype;

    public RelevantDato() {
    }

    public RelevantDato(java.util.Date dato, Datotype datotype) {
        this.dato = dato;
        this.datotype = datotype;
    }

    /**
     * ISO-8601 representasjon for en kalenderdato med tid, trunkert til nærmeste sekund. *YYYY-MM-DD'T'hh:mm:ss*.
     * Eksempel: *2018-01-01T12:00:00*.
     */
    public java.util.Date getDato() {
        return dato;
    }
    /**
     * ISO-8601 representasjon for en kalenderdato med tid, trunkert til nærmeste sekund. *YYYY-MM-DD'T'hh:mm:ss*.
     * Eksempel: *2018-01-01T12:00:00*.
     */
    public void setDato(java.util.Date dato) {
        this.dato = dato;
    }

    /**
     * Markør for hvilken type dato som dato-feltet inneholder.
     */
    public Datotype getDatotype() {
        return datotype;
    }
    /**
     * Markør for hvilken type dato som dato-feltet inneholder.
     */
    public void setDatotype(Datotype datotype) {
        this.datotype = datotype;
    }



}
