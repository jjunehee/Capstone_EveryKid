package com.aaop.everykid.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Board {

    @Id
    @Column
    private int B_ID;

    @Column
    private String K_ID;

    @Column
    private String T_ID;

    @Column
    private String P_ID;

    @Column
    private Date WRITE_DATE;

    @Column
    private String WRITE_SUBJECT;

    @Column
    private String TEXT;

    @Column
    private int HITS;

    public Board(int b_ID, String k_ID, String t_ID, String p_ID, Date WRITE_DATE, String WRITE_SUBJECT, String TEXT, int HITS) {
        B_ID = b_ID;
        K_ID = k_ID;
        T_ID = t_ID;
        P_ID = p_ID;
        this.WRITE_DATE = WRITE_DATE;
        this.WRITE_SUBJECT = WRITE_SUBJECT;
        this.TEXT = TEXT;
        this.HITS = HITS;
    }

    public Board() {

    }

    @Override
    public String toString() {
        return "Board{" +
                "B_ID=" + B_ID +
                ", K_ID='" + K_ID + '\'' +
                ", T_ID='" + T_ID + '\'' +
                ", P_ID='" + P_ID + '\'' +
                ", WRITE_DATE=" + WRITE_DATE +
                ", WRITE_SUBJECT='" + WRITE_SUBJECT + '\'' +
                ", TEXT='" + TEXT + '\'' +
                ", HITS=" + HITS +
                '}';
    }
}

