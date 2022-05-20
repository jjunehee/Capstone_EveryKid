package com.aaop.everykid.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="regioncode")
public class regionCode {

    @Column(name = "시도명")
    String siDoName;

    @Column(name = "시도코드")
    String siDoCode;

    @Column(name = "시군구명")
    String siGunGuName;

    @Id
    @Column(name = "시군구코드")
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
