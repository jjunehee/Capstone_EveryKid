package com.capstone.everykid;

import com.capstone.everykid.RetrofitAPI.initMyApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private static com.capstone.everykid.RetrofitAPI.initMyApi initMyApi;
    //사용하고 있는 서버 BASE 주소
    private static String baseUrl = "http://10.0.2.2:8080/";



    private RetrofitClient() {
        //로그를 보기 위한 Interceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        //retrofit 설정
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client) //로그 기능 추가
                .build();

        initMyApi = retrofit.create(initMyApi.class);
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public static initMyApi getRetrofitInterface() {
        return initMyApi;
    }
}
