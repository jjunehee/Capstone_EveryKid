package com.aaop.everykid.entity;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "B_KID")
    private Long BKID;

    @Column(name = "K_KID")
    private Long KKID;

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

    public Board(Long BKID, Long KKID, String tID, String pID, Date writeDATE, String writeSUBJECT, String contents, int HITS) {
        this.BKID = BKID;
        this.KKID = KKID;
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
                "BKID=" + BKID +
                ", KKID='" + KKID + '\'' +
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

    public void plusHITS() {
        System.out.println(this.HITS);
        this.HITS = ++HITS;
        System.out.println(this.HITS);}
}

