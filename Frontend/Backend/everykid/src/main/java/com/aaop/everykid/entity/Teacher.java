package com.aaop.everykid.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Teacher {

    @Column(name = "T_NAME")
    private String tNAME;

    @Column(name = "T_PHONE")
    private String tPHONE;

    @Column(name = "T_EMAIL")
    private String tEMAIL;

    @Id
    @Column(name = "T_ID")
    private String tID;

    @Column(name = "T_PWD")
    private String tPWD;

    @Column(name = "K_ID")
    private String kID;

    public Teacher(String tNAME, String tPHONE, String tEMAIL, String tID, String tPWD, String kID) {
        this.tNAME = tNAME;
        this.tPHONE = tPHONE;
        this.tEMAIL = tEMAIL;
        this.tID = tID;
        this.tPWD = tPWD;
        this.kID = kID;
    }

    public Teacher() {

    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tNAME='" + tNAME + '\'' +
                ", tPHONE='" + tPHONE + '\'' +
                ", tEMAIL='" + tEMAIL + '\'' +
                ", tID='" + tID + '\'' +
                ", tPWD='" + tPWD + '\'' +
                ", kID='" + kID + '\'' +
                '}';
    }
}
