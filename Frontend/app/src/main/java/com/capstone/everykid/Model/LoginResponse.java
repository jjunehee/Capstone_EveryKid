package com.capstone.everykid.Model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse { //서버로 부터 받을 데이터들

    @SerializedName("status")
    public String resultCode;

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
        return token;
    }
    public void setrefreshToken(String refresh_token) {
        this.refresh_token = refresh_token;
    }


    public String getResultCode() {
        return resultCode;
    }
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }


}
