package com.bachelor.vju_vm_apla2.Models.POJO.Saf;

/**
 * Typen variant som returneres. Dette er normalt **ARKIV**, men kan også være **SLADDET**,**PRODUKSJON**, **PRODUKSJON_DLF** eller **FULLVERSJON**.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public enum Variantformat {

    /**
     * Den *"offisielle"* versjonen av et dokument, som er beregnet på visning og langtidsbevaring. I de fleste tilfeller er arkivvarianten lik dokumentet brukeren sendte inn eller mottok (digitalt eller på papir).
     * * Arkivvarianten er alltid i menneskelesbart format, som PDF, PDF/A eller PNG.
     * * Alle dokumenter har en arkivvariant, med mindre bruker har fått innvilget vedtak om sletting eller skjerming av opplysninger i arkivet.
     */
    ARKIV("ARKIV"),
    /**
     * Dette er en sladdet variant av originaldokumentet.
     * * **SLADDET** variant har ikke spesiell tilgangskontroll.
     */
    SLADDET("SLADDET"),
    /**
     * Produksjonsvariant i eget proprietært format.
     * * Varianten finnes for dokumenter som er produsert i Metaforce eller Brevklient.
     */
    PRODUKSJON("PRODUKSJON"),
    /**
     * Produksjonsvariant i eget proprietært format.
     * * Varianten finnes kun for dokumenter som er produsert i Exstream Live Editor.
     */
    PRODUKSJON_DLF("PRODUKSJON_DLF"),
    /**
     * Variant av dokument som inneholder spørsmålstekster, hjelpetekster og ubesvarte spørsmål fra søknadsdialogen.
     * * Fullversjon genereres for enkelte søknadsskjema fra nav.no, og brukes ved klagebehandling.
     */
    FULLVERSJON("FULLVERSJON"),
    /**
     * Variant av dokumentet i strukturert format, f.eks. XML eller JSON.
     * * Originalvarianten er beregnet på maskinell lesning og behandling.
     */
    ORIGINAL("ORIGINAL");

    private final String graphqlName;

    private Variantformat(String graphqlName) {
        this.graphqlName = graphqlName;
    }

    @Override
    public String toString() {
        return this.graphqlName;
    }

}
