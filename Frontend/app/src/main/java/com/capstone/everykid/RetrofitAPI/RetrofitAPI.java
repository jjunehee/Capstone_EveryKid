package com.capstone.everykid.RetrofitAPI;

import com.capstone.everykid.Model.BoardList;
import com.capstone.everykid.Model.ItemClass;
import com.capstone.everykid.Model.Kindergarten;
import com.capstone.everykid.Model.regionCode;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @GET("/board/board/{kID}")
    Call<BoardList> listBoard(@Path("kID") String kID,
                              @Query("page") int page); //0페이지가 첫 페이지

    @GET("/board/write/{kID}/{tID}/{pID}/{writeSUBJECT}/{contents}")
    Call<Boolean> registBoard(@Path("kID") String kID, @Path("tID") String tID, @Path("pID") String pID, @Path("writeSUBJECT") String writeSUBJECT, @Path("contents") String contents);

    @GET("board/search/{kID}/{key}")
    Call<BoardList> searchBoard(@Path("kID") String kID, @Path("key") String key, @Query("page") int page);

    @GET("region/findAll")
    Call<List<regionCode>> getAllRegionCode();

    @GET("region/getKey")
    Call<String> getKey();

    @GET("/mediate/rest/cpmsapi021/cpmsapi021/request")
    Call<Kindergarten> getKindergartenInfo(@Query("key") String key, @Query("arcode") String arcode);
}
