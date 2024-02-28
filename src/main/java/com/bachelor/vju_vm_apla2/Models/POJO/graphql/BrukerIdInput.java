package com.bachelor.vju_vm_apla2.Models.POJO.graphql;


/**
 * BrukerIdInput er et argument som identifiserer en aktør eller organisasjon.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public class BrukerIdInput implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String id;
    @jakarta.validation.constraints.NotNull
    private BrukerIdType type;

    public BrukerIdInput() {
    }

    public BrukerIdInput(String id, BrukerIdType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public BrukerIdType getType() {
        return type;
    }
    public void setType(BrukerIdType type) {
        this.type = type;
    }



}
