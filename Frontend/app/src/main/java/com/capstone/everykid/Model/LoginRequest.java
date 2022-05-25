package com.capstone.everykid.Model;

import com.google.gson.annotations.SerializedName;

public class LoginRequest { //서버에 보낼 데이터
    @SerializedName("pid")
    public String ParentId;

    @SerializedName("ppwd")
    public String ParentPw;

    public String getParentId() {
        return ParentId;
    }

    public String getParentPw() {
        return ParentPw;
    }

    public void setParentId(String inputId) {
        this.ParentId = inputId;
    }

    public void setParentPw(String inputPw) {
        this.ParentPw = inputPw;
    }

    public LoginRequest(String inputId, String inputPw) {
        this.ParentId = inputId;
        this.ParentPw = inputPw;
    }
}
