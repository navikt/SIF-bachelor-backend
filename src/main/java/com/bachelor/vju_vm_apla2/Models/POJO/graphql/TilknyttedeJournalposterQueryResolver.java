package com.bachelor.vju_vm_apla2.Models.POJO.graphql;


/**
 * Henter metadata for en dokumentInfo (dokumentbeskrivelse) med tilknyttede journalposter.
 * Behovet er å hente journalposter som:
 * * Deler samme DokumentInfo (mange-til-mange relasjon via JPDokInfoRel). Dette vil typisk være resultat av et dokument som er journalført på flere saker/brukere.
 * * Er knyttet sammen via originalJournalpostId. Dette vil typisk være resultat av en splitting av et dokument, der det opprettes to nye DokumentInfo med hver sin del av originaldokumentet, og DokumentInfo.originalJournalpostId peker tilbake på journalposten (med status Utgått) som originaldokumentet lå på.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public interface TilknyttedeJournalposterQueryResolver {

    /**
     * Henter metadata for en dokumentInfo (dokumentbeskrivelse) med tilknyttede journalposter.
     * Behovet er å hente journalposter som:
     * * Deler samme DokumentInfo (mange-til-mange relasjon via JPDokInfoRel). Dette vil typisk være resultat av et dokument som er journalført på flere saker/brukere.
     * * Er knyttet sammen via originalJournalpostId. Dette vil typisk være resultat av en splitting av et dokument, der det opprettes to nye DokumentInfo med hver sin del av originaldokumentet, og DokumentInfo.originalJournalpostId peker tilbake på journalposten (med status Utgått) som originaldokumentet lå på.
     */
    @jakarta.validation.constraints.NotNull
    java.util.List<Journalpost> tilknyttedeJournalposter(@jakarta.validation.constraints.NotNull String dokumentInfoId, @jakarta.validation.constraints.NotNull Tilknytning tilknytning) throws Exception;

}
