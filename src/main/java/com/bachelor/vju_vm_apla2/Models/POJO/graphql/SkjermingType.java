package com.bachelor.vju_vm_apla2.Models.POJO.graphql;

/**
 * Beskriver hvorfor journalposten eller dokumentet er skjermet. Det kan komme flere verdier enn disse i fremtiden.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public enum SkjermingType {

    /**
     * Indikerer at det er fattet et vedtak etter personopplysningsloven (GDPR - brukers rett til å bli glemt).
     */
    POL("POL"),
    /**
     * Indikerer at det har blitt gjort en feil under mottak, journalføring eller brevproduksjon, slik at journalposten eller dokumentene er markert for sletting.
     */
    FEIL("FEIL");

    private final String graphqlName;

    private SkjermingType(String graphqlName) {
        this.graphqlName = graphqlName;
    }

    @Override
    public String toString() {
        return this.graphqlName;
    }

}
