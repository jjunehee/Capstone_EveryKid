package com.capstone.everykid.Model;

import com.google.gson.annotations.SerializedName;

public class regionCode {

    @SerializedName("siDoName")
    String siDoName;

    @SerializedName("siDoCode")
    String siDoCode;

    @SerializedName("siGunGuName")
    String siGunGuName;

    @SerializedName("siGunGuCode")
    String siGunGuCode;

    public regionCode(String siDoName, String siDoCode, String siGunGuName, String siGunGuCode) {
        this.siDoName = siDoName;
        this.siDoCode = siDoCode;
        this.siGunGuName = siGunGuName;
        this.siGunGuCode = siGunGuCode;
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

    public String getSiDoName() {
        return siDoName;
    }

    public void setSiDoName(String siDoName) {
        this.siDoName = siDoName;
    }

    public String getSiDoCode() {
        return siDoCode;
    }

    public void setSiDoCode(String siDoCode) {
        this.siDoCode = siDoCode;
    }

    public String getSiGunGuName() {
        return siGunGuName;
    }

    public void setSiGunGuName(String siGunGuName) {
        this.siGunGuName = siGunGuName;
    }

    public String getSiGunGuCode() {
        return siGunGuCode;
    }

    public void setSiGunGuCode(String siGunGuCode) {
        this.siGunGuCode = siGunGuCode;
    }
}
