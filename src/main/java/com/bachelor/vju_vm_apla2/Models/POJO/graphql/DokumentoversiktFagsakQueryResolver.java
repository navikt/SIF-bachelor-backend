package com.bachelor.vju_vm_apla2.Models.POJO.graphql;


/**
 * * dokumentoversiktFagsak returnerer en liste over alle dokumentene tilknyttet en fagsak.  Listen er sortert omvendt kronologisk.
 * * Det er kun metadata om journalposter med tilhørende dokumenter som returneres. Det fysiske dokumentet kan hentes i saf - REST hentdokument.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public interface DokumentoversiktFagsakQueryResolver {

    /**
     * * dokumentoversiktFagsak returnerer en liste over alle dokumentene tilknyttet en fagsak.  Listen er sortert omvendt kronologisk.
     * * Det er kun metadata om journalposter med tilhørende dokumenter som returneres. Det fysiske dokumentet kan hentes i saf - REST hentdokument.
     */
    @jakarta.validation.constraints.NotNull
    Dokumentoversikt dokumentoversiktFagsak(@jakarta.validation.constraints.NotNull FagsakInput fagsak, String fraDato, java.util.List<Tema> tema, java.util.List<Journalposttype> journalposttyper, java.util.List<Journalstatus> journalstatuser, Integer foerste, String etter) throws Exception;

}
