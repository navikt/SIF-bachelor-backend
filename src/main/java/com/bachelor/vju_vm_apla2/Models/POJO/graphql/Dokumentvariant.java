package com.bachelor.vju_vm_apla2.Models.POJO.graphql;


/**
 * En variant av et dokumentet, som er beregnet på et spesielt formål, for eksempel langtidsbevaring eller automatisk saksbehandling.
 * * De fleste dokumenter vil kun returneres i variantformat ARKIV. Dersom det eksisterer andre varianter av dokumentet, vil disse også returneres, gitt at saksbehandler har rettigheter som tilsier at han/hun skal vite at det finnes andre varianter.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public class Dokumentvariant implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private Variantformat variantformat;
    private String filnavn;
    @Deprecated
    private String filuuid;
    @Deprecated
    private String filtype;
    @Deprecated
    private Integer filstoerrelse;
    private boolean saksbehandlerHarTilgang;
    private SkjermingType skjerming;

    public Dokumentvariant() {
    }

    public Dokumentvariant(Variantformat variantformat, String filnavn, String filuuid, String filtype, Integer filstoerrelse, boolean saksbehandlerHarTilgang, SkjermingType skjerming) {
        this.variantformat = variantformat;
        this.filnavn = filnavn;
        this.filuuid = filuuid;
        this.filtype = filtype;
        this.filstoerrelse = filstoerrelse;
        this.saksbehandlerHarTilgang = saksbehandlerHarTilgang;
        this.skjerming = skjerming;
    }

    /**
     * Typen variant som returneres. Normalt vil dette være ARKIV.
     * * Andre visningsvarianter er SLADDET
     * * Et dokument kan ha både en SLADDET og en ARKIV variant, men aldri flere varianter av samme type.
     */
    public Variantformat getVariantformat() {
        return variantformat;
    }
    /**
     * Typen variant som returneres. Normalt vil dette være ARKIV.
     * * Andre visningsvarianter er SLADDET
     * * Et dokument kan ha både en SLADDET og en ARKIV variant, men aldri flere varianter av samme type.
     */
    public void setVariantformat(Variantformat variantformat) {
        this.variantformat = variantformat;
    }

    /**
     * Navnet på filen i arkivet. Navnet vil i de fleste tilfeller være autogenerert ved arkivering.
     */
    public String getFilnavn() {
        return filnavn;
    }
    /**
     * Navnet på filen i arkivet. Navnet vil i de fleste tilfeller være autogenerert ved arkivering.
     */
    public void setFilnavn(String filnavn) {
        this.filnavn = filnavn;
    }

    /**
     * Unik identifikator per fil.
     * * NB: Feltet skal kun brukes etter avtale med Team Dokument.
     */
    @Deprecated
    public String getFiluuid() {
        return filuuid;
    }
    /**
     * Unik identifikator per fil.
     * * NB: Feltet skal kun brukes etter avtale med Team Dokument.
     */
    @Deprecated
    public void setFiluuid(String filuuid) {
        this.filuuid = filuuid;
    }

    /**
     * Dokumentets filtype, f.eks. PDFA, XML eller JPG. Gyldige verdier finnes på siden Fagarkiv - Filtype.
     * * NB: Informasjonen er ikke garantert å samsvare med dokumentets faktiske filtype, da dette ikke valideres under arkivering.
     * * NB: Verdien 'PDFA' mappes til 'PDF'
     * * NB: Feltet skal kun brukes etter avtale med Team Dokument.
     */
    @Deprecated
    public String getFiltype() {
        return filtype;
    }
    /**
     * Dokumentets filtype, f.eks. PDFA, XML eller JPG. Gyldige verdier finnes på siden Fagarkiv - Filtype.
     * * NB: Informasjonen er ikke garantert å samsvare med dokumentets faktiske filtype, da dette ikke valideres under arkivering.
     * * NB: Verdien 'PDFA' mappes til 'PDF'
     * * NB: Feltet skal kun brukes etter avtale med Team Dokument.
     */
    @Deprecated
    public void setFiltype(String filtype) {
        this.filtype = filtype;
    }

    /**
     * Dokumentets filstørrelse i bytes.
     */
    @Deprecated
    public Integer getFilstoerrelse() {
        return filstoerrelse;
    }
    /**
     * Dokumentets filstørrelse i bytes.
     */
    @Deprecated
    public void setFilstoerrelse(Integer filstoerrelse) {
        this.filstoerrelse = filstoerrelse;
    }

    /**
     * Sier hvorvidt saksbehandler som gjør oppslaget vil få tilgang til å åpne denne dokumentvarianten.
     * * Dersom verdien er false, vil tilgang bli avslått dersom saksbehandler forsøker å åpne dokumentet.
     */
    public boolean getSaksbehandlerHarTilgang() {
        return saksbehandlerHarTilgang;
    }
    /**
     * Sier hvorvidt saksbehandler som gjør oppslaget vil få tilgang til å åpne denne dokumentvarianten.
     * * Dersom verdien er false, vil tilgang bli avslått dersom saksbehandler forsøker å åpne dokumentet.
     */
    public void setSaksbehandlerHarTilgang(boolean saksbehandlerHarTilgang) {
        this.saksbehandlerHarTilgang = saksbehandlerHarTilgang;
    }

    /**
     * Uttrykker at tilgangen til metadata for dette dokumentet er begrenset, og at dataene ikke skal brukes i ordinær saksbehandling.
     */
    public SkjermingType getSkjerming() {
        return skjerming;
    }
    /**
     * Uttrykker at tilgangen til metadata for dette dokumentet er begrenset, og at dataene ikke skal brukes i ordinær saksbehandling.
     */
    public void setSkjerming(SkjermingType skjerming) {
        this.skjerming = skjerming;
    }



}
