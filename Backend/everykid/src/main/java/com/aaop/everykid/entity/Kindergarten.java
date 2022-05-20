package com.aaop.everykid.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Kindergarten {

    @Id
    @GeneratedValue
    @Column(name = "K_KID")
    private Long KKID;

    @Column(name = "K_ID")
    private String kID;

    @Column(name = "K_PHONE")
    private String kPHONE;

    @Column(name = "K_ADDRESS")
    private String kADDRESS;

    @Column(name = "K_NAME")
    private String kNAME;

    public Kindergarten(Long KKID, String kID, String kPHONE, String kADDRESS, String kNAME) {
        this.KKID = KKID;
        this.kID = kID;
        this.kPHONE = kPHONE;
        this.kADDRESS = kADDRESS;
        this.kNAME = kNAME;
    }

    public Kindergarten() {

    }

    @Override
    public String toString() {
        return "Kindergarten{" +
                "KKID='" + KKID + '\'' +
                ", kID='" + kID + '\'' +
                ", kPHONE='" + kPHONE + '\'' +
                ", kADDRESS='" + kADDRESS + '\'' +
                ", kNAME='" + kNAME + '\'' +
                '}';
    }
}