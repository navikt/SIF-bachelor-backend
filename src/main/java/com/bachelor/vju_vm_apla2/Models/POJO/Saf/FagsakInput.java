package com.bachelor.vju_vm_apla2.Models.POJO.Saf;


/**
 * FagsakInput er et argument som identifiserer en fagsak.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public class FagsakInput implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String fagsakId;
    @jakarta.validation.constraints.NotNull
    private String fagsaksystem;

    public FagsakInput() {
    }

    public FagsakInput(String fagsakId, String fagsaksystem) {
        this.fagsakId = fagsakId;
        this.fagsaksystem = fagsaksystem;
    }

    public String getFagsakId() {
        return fagsakId;
    }
    public void setFagsakId(String fagsakId) {
        this.fagsakId = fagsakId;
    }

    public String getFagsaksystem() {
        return fagsaksystem;
    }
    public void setFagsaksystem(String fagsaksystem) {
        this.fagsaksystem = fagsaksystem;
    }



}
