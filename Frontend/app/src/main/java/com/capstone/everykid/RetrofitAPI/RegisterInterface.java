package com.capstone.everykid.RetrofitAPI;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RegisterInterface
{
    String REGIST_URL = "http://10.0.2.2:8080/";

    @FormUrlEncoded
    @POST("/register/parent")
    Call<String> getUserRegist(
            @Field("P_ID") String id,
            @Field("P_PHONE") String phone,
            @Field("P_NAME") String username,
            @Field("P_PWD") String password,
            @Field("P_EMAIL") String email
    );
}