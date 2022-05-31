package com.capstone.everykid.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {
    @SerializedName("KKID")
    private Long KKID;

    @SerializedName("writeDate")
    private Date writeDate;

    @SerializedName("writeSubject")
    private String writeSubject;

    @SerializedName("contents")
    private String contents;

    public Notice(Long KKID, Date writeDate, String writeSubject, String contents) {
        this.KKID = KKID;
        this.writeDate = writeDate;
        this.writeSubject = writeSubject;
        this.contents = contents;
    }

    public Long getKKID() {
        return KKID;
    }

    public void setKKID(Long KKID) {
        this.KKID = KKID;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public String getWriteSubject() {
        return writeSubject;
    }

    public void setWriteSubject(String writeSubject) {
        this.writeSubject = writeSubject;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
