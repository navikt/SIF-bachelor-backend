package com.bachelor.vju_vm_apla2.Models.POJO.graphql;


/**
 * Dokumentoversikt er en liste av journalposter som tilfredstiller query kriteriene.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public class Dokumentoversikt implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private java.util.List<Journalpost> journalposter;

    private SideInfo sideInfo;

    public Dokumentoversikt() {
    }

    public Dokumentoversikt(java.util.List<Journalpost> journalposter, SideInfo sideInfo) {
        this.journalposter = journalposter;
        this.sideInfo = sideInfo;
    }

    /**
     * En liste av journalposter.
     */
    public java.util.List<Journalpost> getJournalposter() {
        return journalposter;
    }
    /**
     * En liste av journalposter.
     */
    public void setJournalposter(java.util.List<Journalpost> journalposter) {
        this.journalposter = journalposter;
    }

    /**
     * Informasjon for å hjelpe med paginering.
     */
    public SideInfo getSideInfo() {
        return sideInfo;
    }
    /**
     * Informasjon for å hjelpe med paginering.
     */
    public void setSideInfo(SideInfo sideInfo) {
        this.sideInfo = sideInfo;
    }



}
