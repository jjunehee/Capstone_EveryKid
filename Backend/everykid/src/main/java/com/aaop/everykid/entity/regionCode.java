package com.aaop.everykid.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="arcode")
public class regionCode {

    @Column(name = "stname")
    String siDoName;

    @Column(name = "stcode")
    String siDoCode;

    @Column(name = "arname")
    String siGunGuName;

    @Id
    @Column(name = "arcode")
    String siGunGuCode;

    public regionCode(String siDoName, String siDoCode, String siGunGuName, String siGunGuCode) {
        this.siDoName = siDoName;
        this.siDoCode = siDoCode;
        this.siGunGuName = siGunGuName;
        this.siGunGuCode = siGunGuCode;
    }

    public regionCode() {

    }

    @Override
    public String toString() {
        return "regionCode{" +
                "siDoName='" + siDoName + '\'' +
                ", siDoCode='" + siDoCode + '\'' +
                ", siGunGuName='" + siGunGuName + '\'' +
                ", siGunGuCode='" + siGunGuCode + '\'' +
                '}';
    }
}
