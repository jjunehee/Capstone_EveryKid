package com.capstone.everykid.Model;

import com.google.gson.annotations.SerializedName;

public class LoginResponseTeacher {

    //선생님 로그인 리스폰스
    @SerializedName("access_TOKEN")
    public String tokenT;
    public String getTokenT() {
        return tokenT;
    }
    public void setTokenT(String tokenT) {
        this.tokenT = tokenT;
    }

    @SerializedName("refresh_TOKEN")
    public String refresh_tokenT;
    public String getrefreshTokenT() {
        return refresh_tokenT;
    }
    public void setrefreshTokenT(String refresh_tokenT) {
        this.refresh_tokenT = refresh_tokenT;
    }

    @SerializedName("temail")
    public String temail;
    public String getTemail() {
        return temail;
    }
    public void setTemail(String temail) {
        this.temail = temail;
    }

    @SerializedName("tid")
    public String tid;
    public String getTid() {
        return tid;
    }
    public void setTid(String tid) {
        this.tid = tid;
    }

    @SerializedName("tkid")
    public int tkid;
    public int getTkid() {
        return tkid;
    }
    public void setTkid(int tkid) {
        this.tkid = tkid;
    }

    @SerializedName("kkid")
    public String kkid;
    public String getKkid() {
        return kkid;
    }
    public void setKkid(String kkid) {
        this.kkid = kkid;
    }

    @SerializedName("tname")
    public String tname;
    public String getTname() {
        return tname;
    }
    public void setTname(String tname) {
        this.tname = tname;
    }

    @SerializedName("tphone")
    public String tphone;
    public String getTphone() {
        return tphone;
    }
    public void setTphone(String tphone) {
        this.tphone = tphone;
    }

}
