package com.aaop.everykid.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="notice")
@IdClass(NoticeKey.class)
public class Notice {

    @Id
    @Column(name = "K_KID")
    Long KKID;

    @Id
    @Column(name = "WRITE_DATE")
    Date writeDate;

    @Column(name = "WRITE_SUBJECT")
    String writeSubject;

    @Column(name = "CONTENTS")
    String contents;

    public Notice(Long KKID, Date writeDate, String writeSubject, String contents) {
        this.KKID = KKID;
        this.writeDate = writeDate;
        this.writeSubject = writeSubject;
        this.contents = contents;
    }

    public Notice() {

    }

    @Override
    public String toString() {
        return "Notice{" +
                "KKID=" + KKID +
                ", writeDate=" + writeDate +
                ", writeSubject='" + writeSubject + '\'' +
                ", contents='" + contents + '\'' +
                '}';
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
