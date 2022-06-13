package com.capstone.everykid.RetrofitAPI;

import com.capstone.everykid.View.Activity.ChildData;

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
            @Field("KKID") String kindergarten,
            @Field("TNAME") String tname
    );

    @FormUrlEncoded
    @POST("/register/teacher")
    Call<String> getTeacherRegist(
            @Field("TID") String id,
            @Field("TPHONE") String phone,
            @Field("TNAME") String username,
            @Field("TPWD") String password,
            @Field("TEMAIL") String email,
            @Field("KKID") String kindergarten
    );

    @FormUrlEncoded
    @POST("child/child")
    Call<String> setChildData(
            @Field("PKID") String pkid,
            @Field("CNAME") String name,
            @Field("CAGE") String age
    );
    @FormUrlEncoded
    @POST("/child/delete")
    Call<String> deleteChild(@Field("PKID") String pkid);
}