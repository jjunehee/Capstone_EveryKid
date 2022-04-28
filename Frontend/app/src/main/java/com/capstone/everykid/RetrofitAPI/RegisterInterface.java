package com.capstone.everykid.RetrofitAPI;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterInterface
{
    String REGIST_URL = "http://10.0.2.2:8080/";

    @FormUrlEncoded
    @POST("/parents/new")
    Call<String> getUserRegist(
            @Field("p_ID") String id,
            @Field("p_PHONE") String phone,
            @Field("p_NAME") String username,
            @Field("p_PWD") String password,
            @Field("p_EMAIL") String email

    );
}