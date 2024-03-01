package com.bachelor.vju_vm_apla2.Models.POJO.graphql;


/**
 * * Query returnerer alle saker for bruker med angitt fnr / aktoerID.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public interface SakerQueryResolver {

    /**
     * * Query returnerer alle saker for bruker med angitt fnr / aktoerID.
     */
    @jakarta.validation.constraints.NotNull
    java.util.List<Sak> saker(@jakarta.validation.constraints.NotNull BrukerIdInput brukerId) throws Exception;

}
