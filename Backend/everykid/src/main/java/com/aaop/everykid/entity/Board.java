package com.aaop.everykid.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
public class Board {

    @Id
    @Column(name = "B_ID")
    private int bID;

    @Column(name = "K_ID")
    private String kID;

    @Column(name = "T_ID")
    private String tID;

    @Column(name = "P_ID")
    private String pID;

    @Column(name = "WRITE_DATE")
    private Date writeDATE;

    @Column(name = "WRITE_SUBJECT")
    private String writeSUBJECT;

    @Column(columnDefinition = "TEXT")
    private String contents;

    @Column
    private int HITS;

    public Board(int bID, String kID, String tID, String pID, Date writeDATE, String writeSUBJECT, String contents, int HITS) {
        this.bID = bID;
        this.kID = kID;
        this.tID = tID;
        this.pID = pID;
        this.writeDATE = writeDATE;
        this.writeSUBJECT = writeSUBJECT;
        this.contents = contents;
        this.HITS = HITS;
    }

    public Board() {

    }

    @Override
    public String toString() {
        return "Board{" +
                "bID=" + bID +
                ", kID='" + kID + '\'' +
                ", tID='" + tID + '\'' +
                ", pID='" + pID + '\'' +
                ", writeDATE=" + writeDATE +
                ", writeSUBJECT='" + writeSUBJECT + '\'' +
                ", contents='" + contents + '\'' +
                ", HITS=" + HITS +
                '}';
    }

    public void modifyContents(String contents) {
        this.contents = contents;
    }
}

