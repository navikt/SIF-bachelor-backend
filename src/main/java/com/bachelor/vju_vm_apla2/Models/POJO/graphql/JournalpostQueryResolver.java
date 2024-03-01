package com.bachelor.vju_vm_apla2.Models.POJO.graphql;


/**
 * * Query returnerer metadata for en journalpost.
 * * Fysiske dokumentet tilknyttet journalposten kan hentes i saf - REST hentdokument
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public interface JournalpostQueryResolver {

    /**
     * * Query returnerer metadata for en journalpost.
     * * Fysiske dokumentet tilknyttet journalposten kan hentes i saf - REST hentdokument
     */
    Journalpost journalpost(String journalpostId, String eksternReferanseId) throws Exception;

}
