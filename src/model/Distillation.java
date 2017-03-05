package model;

import java.sql.Date;

public class Distillation implements Comparable<Distillation>{

    private int VATNumber;
    private int DistillationNumber;
    private int MashPaperNumber;
    private double HLF = 0.0;
    private double GYSZL = 0.0;
    private Date Bring;
    private Date Took = null;

    public Distillation(int VATNumber, int distillationNumber, double HLF, double GYSZL, Date bring) {
        this.VATNumber = VATNumber;
        DistillationNumber = distillationNumber;
        this.HLF = HLF;
        this.GYSZL = GYSZL;
        Bring = bring;
    }

    public int getVATNumber() {
        return VATNumber;
    }

    public void setVATNumber(int VATNumber) {
        this.VATNumber = VATNumber;
    }

    public int getDistillationNumber() {
        return DistillationNumber;
    }

    public void setDistillationNumber(int distillationNumber) {
        DistillationNumber = distillationNumber;
    }

    public int getMashPaperNumber() {
        return MashPaperNumber;
    }

    public void setMashPaperNumber(int mashPaperNumber) {
        MashPaperNumber = mashPaperNumber;
    }

    public double getHLF() {
        return HLF;
    }

    public void setHLF(double HLF) {
        this.HLF = HLF;
    }

    public double getGYSZL() {
        return GYSZL;
    }

    public void setGYSZL(double GYSZL) {
        this.GYSZL = GYSZL;
    }

    public Date getBring() {
        return Bring;
    }

    public void setBring(Date bring) {
        Bring = bring;
    }

    public Date getTook() {
        return Took;
    }

    public void setTook(Date took) {
        Took = took;
    }

    @Override
    public int compareTo(Distillation o) {
        return (int)Bring.compareTo(o.getBring());
    }
}