package com.capstone.everykid.Model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse { //서버로 부터 받을 데이터들

    @SerializedName("status")
    public int resultCode;
    public Integer getStatus() {
        return resultCode;
    }
    public void setStatus(int resultCode) { this.resultCode = resultCode; }

    @SerializedName("access_TOKEN")
    public String token;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    @SerializedName("refresh_TOKEN")
    public String refresh_token;
    public String getrefreshToken() {
        return refresh_token;
    }
    public void setrefreshToken(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    @SerializedName("cage")
    public String c_age;
    public String getC_age() {
        return c_age;
    }
    public void setC_age(String c_age) {
        this.c_age = c_age;
    }

    @SerializedName("c_IMG")
    public String c_img;
    public String getC_img() {
        return c_img;
    }
    public void setC_img(String c_img) {
        this.c_img = c_img;
    }

    @SerializedName("cname")
    public String c_name;
    public String getC_name() {
        return c_name;
    }
    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    @SerializedName("kkid")
    public String kkid;
    public String getKkid() {
        return kkid;
    }
    public void setKkid(String kkid) {
        this.kkid = kkid;
    }

    @SerializedName("pkid")
    public String pkid;
    public String getPkid() {
        return pkid;
    }
    public void setPkid(String pkid) { this.pkid = pkid; }

    @SerializedName("kname")
    public String kname;
    public String getKname() {
        return kname;
    }
    public void setKname(String kname) {
        this.kname = kname;
    }

    @SerializedName("kphone")
    public String kphone;
    public String getKphone() {
        return kphone;
    }
    public void setKphone(String kphone) {
        this.kphone = kphone;
    }

    @SerializedName("kaddress")
    public String kaddress;
    public String getKaddress() {
        return kaddress;
    }
    public void setKaddress(String kaddress) {
        this.kaddress = kaddress;
    }

    @SerializedName("palias")
    public String palias;
    public String getPalias() {
        return palias;
    }
    public void setPalias(String palias) {
        this.palias = palias;
    }

    @SerializedName("pemail")
    public String pemail;
    public String getPemail() {
        return pemail;
    }
    public void setPemail(String pemail) {
        this.pemail = pemail;
    }

    @SerializedName("pid")
    public String pid;
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }


    @SerializedName("pname")
    public String pname;
    public String getPname() {
        return pname;
    }
    public void setPname(String pname) {
        this.pname = pname;
    }

    @SerializedName("pphone")
    public String pphone;
    public String getPphone() {
        return pphone;
    }
    public void setPphone(String pphone) {
        this.pphone = pphone;
    }

    @SerializedName("tname")
    public String tname;
    public String getPtname() {
        return tname;
    }
    public void setPtname(String tname) {
        this.tname = tname;
    }





}
