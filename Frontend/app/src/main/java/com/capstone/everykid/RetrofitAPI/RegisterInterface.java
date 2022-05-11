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
            @Field("p_ID") String id,
            @Field("p_PHONE") String phone,
            @Field("p_NAME") String username,
            @Field("p_PWD") String password,
            @Field("p_EMAIL") String email
    );

//선생님 회원가입 아직 추가 안함
//    @FormUrlEncoded
//    @POST("/register/teacher")
//    Call<String> getUserRegist(
//            @Field("t_ID") String id,
//
//    )


}