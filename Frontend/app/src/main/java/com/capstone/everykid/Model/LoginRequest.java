package com.capstone.everykid.Model;

import com.google.gson.annotations.SerializedName;

public class LoginRequest { //서버에 보낼 데이터
    @SerializedName("pID")
    public String inputId;

    @SerializedName("pPWD")
    public String inputPw;

    public String getInputId() {
        return inputId;
    }

    public String getInputPw() {
        return inputPw;
    }

    public void setInputId(String inputId) {
        this.inputId = inputId;
    }

    public void setInputPw(String inputPw) {
        this.inputPw = inputPw;
    }

    public LoginRequest(String inputId, String inputPw) {
        this.inputId = inputId;
        this.inputPw = inputPw;
    }
}
