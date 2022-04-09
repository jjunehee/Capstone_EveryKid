package com.aaop.everykid.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Children {

    @Id
    @Column
    private String C_ID;

    @Column
    private int C_AGE;

    @Column
    private String P_ID;

    @Column
    private String K_ID;

    @Column
    private String C_STATUS;


    public Children(String c_ID, int c_AGE, String p_ID, String k_ID, String c_STATUS) {
        C_ID = c_ID;
        C_AGE = c_AGE;
        P_ID = p_ID;
        K_ID = k_ID;
        C_STATUS = c_STATUS;
    }

    public Children() {

    }

    @Override
    public String toString() {
        return "Children{" +
                "C_ID='" + C_ID + '\'' +
                ", C_AGE=" + C_AGE +
                ", P_ID='" + P_ID + '\'' +
                ", K_ID='" + K_ID + '\'' +
                ", C_STATUS='" + C_STATUS + '\'' +
                '}';
    }
}
