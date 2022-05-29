package com.capstone.everykid.RetrofitAPI;

import com.capstone.everykid.Model.BoardList;
import com.capstone.everykid.Model.ItemClass;
import com.capstone.everykid.Model.Kindergarten;
import com.capstone.everykid.Model.LoginRequest;
import com.capstone.everykid.Model.LoginRequestTeacher;
import com.capstone.everykid.Model.LoginResponse;
<<<<<<< HEAD
import com.capstone.everykid.Model.LoginResponseTeacher;
=======
import com.capstone.everykid.Model.Notice;
>>>>>>> 6ba6c8cdf855951c5a11e3a4695d41c14babed61
import com.capstone.everykid.Model.regionCode;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @GET("/board/board/{kID}")
    Call<BoardList> listBoard(@Path("kID") Long kID,
                              @Query("page") int page); //0페이지가 첫 페이지

    @GET("/board/write/{kID}/{tID}/{pID}/{writeSUBJECT}/{contents}")
    Call<Boolean> registBoard(@Path("kID") String kID, @Path("tID") String tID, @Path("pID") String pID, @Path("writeSUBJECT") String writeSUBJECT, @Path("contents") String contents);

    @GET("/board/search/{kID}")
    Call<BoardList> searchBoard(@Path("kID") Long kID, @Query("key") String key, @Query("page") int page);

    @GET("region/findAll")
    Call<List<regionCode>> getAllRegionCode();

    @GET("region/getKey")
    Call<String> getKey();

    @GET("/mediate/rest/cpmsapi021/cpmsapi021/request")
    Call<Kindergarten> getKindergartenInfo(@Query("key") String key, @Query("arcode") String arcode);

    @GET("kindergarten/find/{KID}/{KPHONE}/{KADDRESS}/{KNAME}")
    Call<Long> selectKindergarten(@Path("KID") String KID, @Path("KPHONE") String KPHONE, @Path("KADDRESS") String KADDRESS, @Path("KNAME") String KNAME);

    @GET("/board/select/{bKID}")
    Call<Void> selectBoard(@Path("bKID") Long kID);

    @POST("/register/plogin")
    Call<LoginResponse> getLoginResponse(
            @Body LoginRequest loginRequest
    );
    @POST("/register/tlogin")
    Call<LoginResponseTeacher> getLogin2Response(
            @Body LoginRequestTeacher loginRequestTeacher
    );

    @GET("/notice/write/{KKID}/{DATE}/{WRITESUBJECT}/{CONTENTS}")
    Call<Boolean> registNotice(@Path("KKID") Long KKID, @Path("DATE") Date DATE, @Path("WRITESUBJECT") String WRITESUBJECT, @Path("CONTENTS") String CONTENTS);

    @GET("/notice/notice/{KKID}")
    Call<List<Notice>> getNoticeList(@Path("KKID") Long KKID);
}
