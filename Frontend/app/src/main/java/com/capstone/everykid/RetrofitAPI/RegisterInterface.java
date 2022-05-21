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
            @Field("PALIAS") String alias
<<<<<<< HEAD

=======
>>>>>>> 9a44d88c72d4e6ef1bc6a8cac541b3520550e90e
    );

    @FormUrlEncoded
    @POST("/register/teacher")
    Call<String> getTeacherRegist(
            @Field("T_ID") String id,
            @Field("T_PHONE") String phone,
            @Field("T_NAME") String username,
            @Field("T_PWD") String password,
            @Field("T_EMAIL") String email
    );
}