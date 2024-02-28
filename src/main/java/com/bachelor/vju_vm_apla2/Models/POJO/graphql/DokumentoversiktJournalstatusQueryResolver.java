package com.bachelor.vju_vm_apla2.Models.POJO.graphql;


/**
 * * dokumentoversiktJournalstatus returnerer en liste som matcher søkeparameterene.
 * * Det er kun metadata om journalposter med tilhørende dokumenter som returneres. Det fysiske dokumentet kan hentes i saf - REST hentdokument.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public interface DokumentoversiktJournalstatusQueryResolver {

    /**
     * * dokumentoversiktJournalstatus returnerer en liste som matcher søkeparameterene.
     * * Det er kun metadata om journalposter med tilhørende dokumenter som returneres. Det fysiske dokumentet kan hentes i saf - REST hentdokument.
     */
    @jakarta.validation.constraints.NotNull
    Dokumentoversikt dokumentoversiktJournalstatus(String fraDato, java.util.List<Tema> tema, java.util.List<Journalposttype> journalposttyper, @jakarta.validation.constraints.NotNull Journalstatus journalstatus, Integer foerste, String etter) throws Exception;

}
