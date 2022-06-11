package com.capstone.everykid.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.everykid.Model.CreateAccountItem;
import com.capstone.everykid.R;
import com.capstone.everykid.RetrofitAPI.RetrofitAPI;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoticeWriteActivity extends Activity {
    TextView titleView;
    TextView contentsView;
    Button writeButton;
    Button cancelButton;
    RetrofitAPI retrofitAPI;
    Call call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.activity_notice_write);
        Intent intent = getIntent();
        titleView = findViewById(R.id.noticeTitle_et);
        contentsView = findViewById(R.id.noticeContent_et);
        writeButton = findViewById(R.id.noticeWrite);
        cancelButton = findViewById(R.id.noticeCancel);
        try {
            titleView.setText(intent.getExtras().getString("subject"));
            contentsView.setText(intent.getExtras().getString("contents"));
        } catch(NullPointerException e) {

        }

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:8080/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                retrofitAPI = retrofit.create(RetrofitAPI.class);

                SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formatDate = dtFormat.format(new Date());

                call = retrofitAPI.registNotice(CreateAccountItem.K_kid, formatDate, titleView.getText().toString(), contentsView.getText().toString());
                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.isSuccessful()) {
                            //통신이 성공된 경우
                            Boolean result = response.body();
                            System.out.println("통신 완료");
                            //parent, teacher 구별
                            finish();
                            Intent intent = new Intent(NoticeWriteActivity.this, MainParent.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            //data 에서 필요한 내용 꺼내 쓰기
                        } else {
                            Toast.makeText(NoticeWriteActivity.this, "통신에 실패했습니다", Toast.LENGTH_LONG).show();
                            //통신이 실패한 경우
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        //통신 실패 (인터넷 끊김, 예외 발생 등 시스템적인 이유)
                        t.printStackTrace();
                    }
                });
            }
        });
    }
    public void mOnClose(View v){ //창 닫기
        //데이터 전달하기
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        //액티비티(팝업) 닫기
        finish();
    }
    public void mOnRegist(View v){ //공지사항 등록
        //데이터 전달하기
//        Intent intent = new Intent();
//        setResult(RESULT_OK, intent);
//        //액티비티(팝업) 닫기
        finish();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}