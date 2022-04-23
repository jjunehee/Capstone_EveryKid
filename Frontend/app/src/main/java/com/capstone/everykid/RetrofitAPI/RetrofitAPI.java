package com.capstone.everykid.RetrofitAPI;

import com.capstone.everykid.Model.Board;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitAPI {
    @GET("/board/board/{kID}")
    Call<List<Board>> listBoard(@Path("kID") String kID);


}
