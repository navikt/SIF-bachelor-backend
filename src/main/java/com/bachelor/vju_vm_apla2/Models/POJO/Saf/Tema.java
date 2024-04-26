package com.bachelor.vju_vm_apla2.Models.POJO.Saf;

/**
 * Temaet/Fagområdet som en journalpost og tilhørende sak tilhører, f.eks. **FOR** (foreldrepenger).
 * * I NAV brukes Tema for å klassifisere journalposter i arkivet med tanke på gjenfinning, tilgangsstyring og bevaringstid.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public enum Tema {

    /**
     * Arbeidsavklaringspenger
     */
    AAP("AAP"),
    /**
     * Aa-registeret
     */
    AAR("AAR"),
    /**
     * Ajourhold – grunnopplysninger
     */
    AGR("AGR"),
    /**
     * Arbeidsrådgivning – psykologtester
     */
    ARP("ARP"),
    /**
     * Arbeidsrådgivning – skjermet
     */
    ARS("ARS"),
    /**
     * Barnetrygd
     */
    BAR("BAR"),
    /**
     * Bidrag
     */
    BID("BID"),
    /**
     * Bil
     */
    BIL("BIL"),
    /**
     * Dagpenger
     */
    DAG("DAG"),
    /**
     * Enslig mor eller far
     */
    ENF("ENF"),
    /**
     * Erstatning
     */
    ERS("ERS"),
    /**
     * Barnepensjon
     */
    EYB("EYB"),
    /**
     * Omstillingsstønad
     */
    EYO("EYO"),
    /**
     * Foreldreskap
     */
    FAR("FAR"),
    /**
     * Feilutbetaling
     */
    FEI("FEI"),
    /**
     * Fiskerpensjon
     */
    FIP("FIP"),
    /**
     * Foreldre- og svangerskapspenger
     */
    FOR("FOR"),
    /**
     * Forsikring
     */
    FOS("FOS"),
    /**
     * Kompensasjon for selvstendig næringsdrivende/frilansere
     */
    FRI("FRI"),
    /**
     * Fullmakt
     */
    FUL("FUL"),
    /**
     * Generell
     */
    GEN("GEN"),
    /**
     * Gravferdsstønad
     */
    GRA("GRA"),
    /**
     * Grunn- og hjelpestønad
     */
    GRU("GRU"),
    /**
     * Helsetjenester og ortopediske hjelpemidler
     */
    HEL("HEL"),
    /**
     * Hjelpemidler
     */
    HJE("HJE"),
    /**
     * Inkluderende arbeidsliv
     */
    IAR("IAR"),
    /**
     * Tiltakspenger
     */
    IND("IND"),
    /**
     * Kontantstøtte
     */
    KON("KON"),
    /**
     * Klage – lønnsgaranti
     */
    KLL("KLL"),
    /**
     * Kontroll – anmeldelse
     */
    KTA("KTA"),
    /**
     * Kontroll
     */
    KTR("KTR"),
    /**
     * Medlemskap
     */
    MED("MED"),
    /**
     * Mobilitetsfremmende stønad
     */
    MOB("MOB"),
    /**
     * Omsorgspenger, pleiepenger og opplæringspenger
     */
    OMS("OMS"),
    /**
     * Oppfølging – arbeidsgiver
     */
    OPA("OPA"),
    /**
     * Oppfølging
     */
    OPP("OPP"),
    /**
     * Pensjon
     */
    PEN("PEN"),
    /**
     * Permittering og masseoppsigelser
     */
    PER("PER"),
    /**
     * Rehabiliteringspenger
     */
    REH("REH"),
    /**
     * Rekruttering
     */
    REK("REK"),
    /**
     * Retting av personopplysninger
     */
    RPO("RPO"),
    /**
     * Rettferdsvederlag
     */
    RVE("RVE"),
    /**
     * Sanksjon - Arbeidsgiver
     */
    SAA("SAA"),
    /**
     * Sakskostnader
     */
    SAK("SAK"),
    /**
     * Sanksjon – person
     */
    SAP("SAP"),
    /**
     * Serviceklager
     */
    SER("SER"),
    /**
     * Regnskap/utbetaling
     */
    STO("STO"),
    /**
     * Supplerende stønad
     */
    SUP("SUP"),
    /**
     * Sykepenger
     */
    SYK("SYK"),
    /**
     * Sykmeldinger
     */
    SYM("SYM"),
    /**
     * Tiltak
     */
    TIL("TIL"),
    /**
     * Trekkhåndtering
     */
    TRK("TRK"),
    /**
     * Trygdeavgift
     */
    TRY("TRY"),
    /**
     * Tilleggsstønad
     */
    TSO("TSO"),
    /**
     * Tilleggsstønad – arbeidssøkere
     */
    TSR("TSR"),
    /**
     * Unntak fra medlemskap
     */
    UFM("UFM"),
    /**
     * Uføretrygd
     */
    UFO("UFO"),
    /**
     * Ukjent
     */
    UKJ("UKJ"),
    /**
     * Ventelønn
     */
    VEN("VEN"),
    /**
     * Yrkesrettet attføring
     */
    YRA("YRA"),
    /**
     * Yrkesskade og menerstatning
     */
    YRK("YRK");

    private final String graphqlName;

    private Tema(String graphqlName) {
        this.graphqlName = graphqlName;
    }

    @Override
    public String toString() {
        return this.graphqlName;
    }

}
