package com.aaop.everykid.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Children {

    @Id
    @Column(name = "C_ID")
    private String cID;

    @Column(name = "C_AGE")
    private int cAGE;

    @Column(name = "P_ID")
    private String pID;

    @Column(name = "K_ID")
    private String kID;

    @Column(name = "C_STATUS")
    private String cSTATUS;


    public Children(String cID, int cAGE, String pID, String kID, String cSTATUS) {
        this.cID = cID;
        this.cAGE = cAGE;
        this.pID = pID;
        this.kID = kID;
        this.cSTATUS = cSTATUS;
    }

    public Children() {

    }

    @Override
    public String toString() {
        return "Children{" +
                "cID='" + cID + '\'' +
                ", cAGE=" + cAGE +
                ", pID='" + pID + '\'' +
                ", kID='" + kID + '\'' +
                ", cSTATUS='" + cSTATUS + '\'' +
                '}';
    }
}
