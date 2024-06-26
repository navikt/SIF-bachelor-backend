package com.bachelor.vju_vm_apla2.Models.POJO.Saf;

/**
 * * Status på journalposten i arkivet, f.eks. **MOTTATT** eller **JOURNALFOERT**. Journalstatusen gir et indikasjon på hvor i journalførings- eller dokumentproduksjonsprosessen journalposten befinner seg.
 * * Journalposter som er resultat av en feilsituasjon og ikke skal hensyntas for saksbehandlinghar egne koder, som **UTGAAR** eller **AVBRUTT**.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public enum Journalstatus {

    /**
     * Journalposten er mottatt, men ikke journalført. *"Mottatt"* er et annet ord for *"arkivert"* eller *"midlertidig journalført"*
     * * Statusen vil kun forekomme for inngående dokumenter.
     */
    MOTTATT("MOTTATT"),
    /**
     * Journalposten er ferdigstilt og ansvaret for videre behandling av forsendelsen er overført til fagsystemet.
     * * Journalposter med status **JOURNALFOERT** oppfyller minimumskrav til metadata i arkivet, som for eksempel tema, sak, bruker og avsender.
     */
    JOURNALFOERT("JOURNALFOERT"),
    /**
     * Journalposten med tilhørende dokumenter er ferdigstilt, og journalen er i prinsippet låst for videre endringer.
     * * Tilsvarer statusen **JOURNALFOERT** for inngående dokumenter.
     */
    FERDIGSTILT("FERDIGSTILT"),
    /**
     * Dokumentet er sendt til bruker. Statusen benyttes også når dokumentet er tilgjengeliggjort for bruker på Nav.no, og bruker er varslet.
     * * Statusen kan forekomme for utgående dokumenter og notater.
     */
    EKSPEDERT("EKSPEDERT"),
    /**
     * Journalposten er opprettet i arkivet, men fremdeles under arbeid.
     * * Statusen kan forekomme for utgående dokumenter og notater.
     */
    UNDER_ARBEID("UNDER_ARBEID"),
    /**
     * Journalposten har blitt unntatt fra saksbehandling etter at den feilaktig har blitt knyttet til en sak. Det er ikke mulig å slette en saksrelasjon, istedet settes saksrelasjonen til feilregistrert.
     * * Statusen kan forekomme for alle journalposttyper.
     */
    FEILREGISTRERT("FEILREGISTRERT"),
    /**
     * Journalposten er unntatt fra saksbehandling. Status **UTGAAR** brukes stort sett ved feilsituasjoner knyttet til skanning eller journalføring.
     * * Statusen vil kun forekomme for inngående dokumenter
     */
    UTGAAR("UTGAAR"),
    /**
     * Utgående dokumenter og notater kan avbrytes mens de er under arbeid, og ikke enda er ferdigstilt. Statusen **AVBRUTT** brukes stort sett ved feilsituasjoner knyttet til vedtaksproduksjon.
     * * Statusen kan forekomme for utgående dokumenter og notater.
     */
    AVBRUTT("AVBRUTT"),
    /**
     * Journalposten har ikke noen kjent bruker.
     * ** NB: ** **UKJENT_BRUKER** er ikke en midlertidig status, men benyttes der det ikke er mulig å journalføre fordi man ikke klarer å identifisere brukeren forsendelsen gjelder.
     * * Statusen kan kun forekomme for inngående dokumenter.
     */
    UKJENT_BRUKER("UKJENT_BRUKER"),
    /**
     * Statusen benyttes bl.a. i forbindelse med brevproduksjon for å reservere 'plass' i journalen for dokumenter som skal populeres på et senere tidspunkt.
     * Tilsvarer statusen **OPPLASTING_DOKUMENT** for inngående dokumenter.
     * * Statusen kan forekomme for utgående dokumenter og notater
     */
    RESERVERT("RESERVERT"),
    /**
     * Midlertidig status på vei mot **MOTTATT**.
     * Dersom en journalpost blir stående i status **OPPLASTING_DOKUMENT** over tid, tyder dette på at noe har gått feil under opplasting av vedlegg ved arkivering.
     * * Statusen kan kun forekomme for inngående dokumenter.
     */
    OPPLASTING_DOKUMENT("OPPLASTING_DOKUMENT"),
    /**
     * Dersom statusfeltet i Joark er tomt, mappes dette til **UKJENT**
     */
    UKJENT("UKJENT");

    private final String graphqlName;

    private Journalstatus(String graphqlName) {
        this.graphqlName = graphqlName;
    }

    @Override
    public String toString() {
        return this.graphqlName;
    }

}
