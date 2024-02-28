package com.bachelor.vju_vm_apla2.Models.POJO.graphql;


/**
 * Informasjon om paginering.
 */
@jakarta.annotation.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2024-02-28T09:45:41+0100"
)
public class SideInfo implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String sluttpeker;
    private boolean finnesNesteSide;
    private Integer antall;
    private Integer totaltAntall;

    public SideInfo() {
    }

    public SideInfo(String sluttpeker, boolean finnesNesteSide, Integer antall, Integer totaltAntall) {
        this.sluttpeker = sluttpeker;
        this.finnesNesteSide = finnesNesteSide;
        this.antall = antall;
        this.totaltAntall = totaltAntall;
    }

    /**
     * Når man paginerer forover, pekeren for å fortsette.
     */
    public String getSluttpeker() {
        return sluttpeker;
    }
    /**
     * Når man paginerer forover, pekeren for å fortsette.
     */
    public void setSluttpeker(String sluttpeker) {
        this.sluttpeker = sluttpeker;
    }

    /**
     * True/False verdi for om neste side eksisterer, når man paginerer forover.
     */
    public boolean getFinnesNesteSide() {
        return finnesNesteSide;
    }
    /**
     * True/False verdi for om neste side eksisterer, når man paginerer forover.
     */
    public void setFinnesNesteSide(boolean finnesNesteSide) {
        this.finnesNesteSide = finnesNesteSide;
    }

    /**
     * Antall journalposter på denne siden.
     */
    public Integer getAntall() {
        return antall;
    }
    /**
     * Antall journalposter på denne siden.
     */
    public void setAntall(Integer antall) {
        this.antall = antall;
    }

    /**
     * Totalt antall journalposter på alle sider.
     */
    public Integer getTotaltAntall() {
        return totaltAntall;
    }
    /**
     * Totalt antall journalposter på alle sider.
     */
    public void setTotaltAntall(Integer totaltAntall) {
        this.totaltAntall = totaltAntall;
    }



}
