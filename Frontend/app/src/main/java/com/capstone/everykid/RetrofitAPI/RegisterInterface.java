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
    Call<String> getParentRegist(
            @Field("PID") String id,
            @Field("PPHONE") String phone,
            @Field("PNAME") String username,
            @Field("PPWD") String password,
            @Field("PEMAIL") String email,
            @Field("KKID") String kindergarten
    );

    @FormUrlEncoded
    @POST("/register/teacher")
    Call<String> getTeacherRegist(
            @Field("tid") String id,
            @Field("tphone") String phone,
            @Field("tname") String username,
            @Field("tpwd") String password,
            @Field("temail") String email
    );
}