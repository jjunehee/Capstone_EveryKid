package com.capstone.everykid.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Board implements Serializable {
    @SerializedName("BKID")
    private Long BKID;

    @SerializedName("KKID")
    private Long KKID;

    @SerializedName("tID")
    private String T_ID;

    @SerializedName("pID")
    private String P_ID;

    @SerializedName("writeDATE")
    private Date WRITE_DATE;

    @SerializedName("writeSUBJECT")
    private String WRITE_SUBJECT;

    @SerializedName("contents")
    private String CONTENTS;

    @SerializedName("HITS")
    private int HITS;

    public Board(Long BKID, Long KKID, String t_ID, String p_ID, Date WRITE_DATE, String WRITE_SUBJECT, String CONTENTS, int HITS) {
        this.BKID = BKID;
        this.KKID = KKID;
        T_ID = t_ID;
        P_ID = p_ID;
        this.WRITE_DATE = WRITE_DATE;
        this.WRITE_SUBJECT = WRITE_SUBJECT;
        this.CONTENTS = CONTENTS;
        this.HITS = HITS;
    }

    @Override
    public String toString() {
        return "Board{" +
                "BKID=" + BKID +
                ", KKID=" + KKID +
                ", T_ID='" + T_ID + '\'' +
                ", P_ID='" + P_ID + '\'' +
                ", WRITE_DATE=" + WRITE_DATE +
                ", WRITE_SUBJECT='" + WRITE_SUBJECT + '\'' +
                ", CONTENTS='" + CONTENTS + '\'' +
                ", HITS=" + HITS +
                '}';
    }

    public Long getBKID() {
        return BKID;
    }

    public void setBKID(Long BKID) {
        this.BKID = BKID;
    }

    public Long getKKID() {
        return KKID;
    }

    public void setKKID(Long KKID) {
        this.KKID = KKID;
    }

    public String getT_ID() {
        return T_ID;
    }

    public void setT_ID(String t_ID) {
        T_ID = t_ID;
    }

    public String getP_ID() {
        return P_ID;
    }

    public void setP_ID(String p_ID) {
        P_ID = p_ID;
    }

    public Date getWRITE_DATE() {
        return WRITE_DATE;
    }

    public void setWRITE_DATE(Date WRITE_DATE) {
        this.WRITE_DATE = WRITE_DATE;
    }

    public String getWRITE_SUBJECT() {
        return WRITE_SUBJECT;
    }

    public void setWRITE_SUBJECT(String WRITE_SUBJECT) {
        this.WRITE_SUBJECT = WRITE_SUBJECT;
    }

    public String getCONTENTS() {
        return CONTENTS;
    }

    public void setCONTENTS(String CONTENTS) {
        this.CONTENTS = CONTENTS;
    }

    public int getHITS() {
        return HITS;
    }

    public void setHITS(int HITS) {
        this.HITS = HITS;
    }
}
