package com.capstone.everykid.RetrofitAPI;

import com.capstone.everykid.Model.BoardList;
import com.capstone.everykid.Model.ItemClass;
import com.capstone.everykid.Model.Kindergarten;
import com.capstone.everykid.Model.LoginRequest;
import com.capstone.everykid.Model.LoginRequestTeacher;
import com.capstone.everykid.Model.LoginResponse;
import com.capstone.everykid.Model.LoginResponseTeacher;
import com.capstone.everykid.Model.Notice;
import com.capstone.everykid.Model.regionCode;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @GET("/board/board/{kID}")
    Call<BoardList> listBoard(@Path("kID") Long kID,
                              @Query("page") int page); //0페이지가 첫 페이지

    @GET("/board/write/{kID}/{tID}/{pID}/{writeSUBJECT}/{contents}")
    Call<Boolean> registBoard(@Path("kID") String kID, @Path("tID") String tID, @Path("pID") String pID, @Path("writeSUBJECT") String writeSUBJECT, @Path("contents") String contents);

    @GET("/board/search/{kID}")
    Call<BoardList> searchBoard(@Path("kID") Long kID, @Query("key") String key, @Query("SPINNER") String spinnerSelect, @Query("page") int page);

    @GET("/board/delete")
    Call<String> deleteBoard(@Query("bKID") Long bKID);

    @GET("/board/modify")
    Call<String> modifyBoard(@Query("bKID") Long bKID, @Query("subject") String subject, @Query("contents") String contents);

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
