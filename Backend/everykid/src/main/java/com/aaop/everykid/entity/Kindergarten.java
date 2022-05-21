package com.aaop.everykid.entity;
import javax.persistence.*;

@Entity
public class Kindergarten {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "K_KID")
    private Long KKID;

    @Column(name = "K_ID")
    private String KID;

    @Column(name = "K_PHONE")
    private String KPHONE;

    @Column(name = "K_ADDRESS")
    private String KADDRESS;

    @Column(name = "K_NAME")
    private String KNAME;

    public Kindergarten(Long KKID, String KID, String KPHONE, String KADDRESS, String KNAME) {
        this.KKID = KKID;
        this.KID = KID;
        this.KPHONE = KPHONE;
        this.KADDRESS = KADDRESS;
        this.KNAME = KNAME;
    }

    public Kindergarten() {

    }

    @Override
    public String toString() {
        return "Kindergarten{" +
                "KKID=" + KKID +
                ", KID='" + KID + '\'' +
                ", KPHONE='" + KPHONE + '\'' +
                ", KADDRESS='" + KADDRESS + '\'' +
                ", KNAME='" + KNAME + '\'' +
                '}';
    }

    public Long getKKID() {
        return KKID;
    }

    public void setKKID(Long KKID) {
        this.KKID = KKID;
    }

    public String getKID() {
        return KID;
    }

    public void setKID(String KID) {
        this.KID = KID;
    }

    public String getKPHONE() {
        return KPHONE;
    }

    public void setKPHONE(String KPHONE) {
        this.KPHONE = KPHONE;
    }

    public String getKADDRESS() {
        return KADDRESS;
    }

    public void setKADDRESS(String KADDRESS) {
        this.KADDRESS = KADDRESS;
    }

    public String getKNAME() {
        return KNAME;
    }

    public void setKNAME(String KNAME) {
        this.KNAME = KNAME;
    }
}