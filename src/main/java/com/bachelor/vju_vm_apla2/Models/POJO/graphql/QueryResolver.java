package com.bachelor.vju_vm_apla2.Models.POJO.graphql;


/**
 * Query roten til SAF GraphQL API.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public interface QueryResolver {

    /**
     * * dokumentoversiktBruker returnerer en liste over alle dokumentene tilknyttet en bruker.  Listen er sortert omvendt kronologisk.
     * * Det er kun metadata om journalposter med tilhørende dokumenter som returneres. Det fysiske dokumentet kan hentes i saf - REST hentdokument.
     */
    @jakarta.validation.constraints.NotNull
    Dokumentoversikt dokumentoversiktBruker(@jakarta.validation.constraints.NotNull BrukerIdInput brukerId, String fraDato, String tilDato, java.util.List<Tema> tema, java.util.List<Journalposttype> journalposttyper, java.util.List<Journalstatus> journalstatuser, Integer foerste, String etter) throws Exception;

    /**
     * * dokumentoversiktFagsak returnerer en liste over alle dokumentene tilknyttet en fagsak.  Listen er sortert omvendt kronologisk.
     * * Det er kun metadata om journalposter med tilhørende dokumenter som returneres. Det fysiske dokumentet kan hentes i saf - REST hentdokument.
     */
    @jakarta.validation.constraints.NotNull
    Dokumentoversikt dokumentoversiktFagsak(@jakarta.validation.constraints.NotNull FagsakInput fagsak, String fraDato, java.util.List<Tema> tema, java.util.List<Journalposttype> journalposttyper, java.util.List<Journalstatus> journalstatuser, Integer foerste, String etter) throws Exception;

    /**
     * * dokumentoversiktJournalstatus returnerer en liste som matcher søkeparameterene.
     * * Det er kun metadata om journalposter med tilhørende dokumenter som returneres. Det fysiske dokumentet kan hentes i saf - REST hentdokument.
     */
    @jakarta.validation.constraints.NotNull
    Dokumentoversikt dokumentoversiktJournalstatus(String fraDato, java.util.List<Tema> tema, java.util.List<Journalposttype> journalposttyper, @jakarta.validation.constraints.NotNull Journalstatus journalstatus, Integer foerste, String etter) throws Exception;

    /**
     * * Query returnerer metadata for en journalpost.
     * * Fysiske dokumentet tilknyttet journalposten kan hentes i saf - REST hentdokument
     */
    Journalpost journalpost(String journalpostId, String eksternReferanseId) throws Exception;

    /**
     * Henter metadata for en dokumentInfo (dokumentbeskrivelse) med tilknyttede journalposter.
     * Behovet er å hente journalposter som:
     * * Deler samme DokumentInfo (mange-til-mange relasjon via JPDokInfoRel). Dette vil typisk være resultat av et dokument som er journalført på flere saker/brukere.
     * * Er knyttet sammen via originalJournalpostId. Dette vil typisk være resultat av en splitting av et dokument, der det opprettes to nye DokumentInfo med hver sin del av originaldokumentet, og DokumentInfo.originalJournalpostId peker tilbake på journalposten (med status Utgått) som originaldokumentet lå på.
     */
    @jakarta.validation.constraints.NotNull
    java.util.List<Journalpost> tilknyttedeJournalposter(@jakarta.validation.constraints.NotNull String dokumentInfoId, @jakarta.validation.constraints.NotNull Tilknytning tilknytning) throws Exception;

    /**
     * * Query returnerer alle saker for bruker med angitt fnr / aktoerID.
     */
    @jakarta.validation.constraints.NotNull
    java.util.List<Sak> saker(@jakarta.validation.constraints.NotNull BrukerIdInput brukerId) throws Exception;

}
