package com.capstone.everykid.RetrofitAPI;

import com.capstone.everykid.Model.LoginRequest;
import com.capstone.everykid.Model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface initMyApi {
    @POST("/register/login")
    Call<LoginResponse> getLoginResponse(
            @Body LoginRequest loginRequest
            );
}
