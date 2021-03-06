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
                            //????????? ????????? ??????
                            Boolean result = response.body();
                            System.out.println("?????? ??????");
                            //parent, teacher ??????
                            finish();
                            Intent intent = new Intent(NoticeWriteActivity.this, MainParent.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            //data ?????? ????????? ?????? ?????? ??????
                        } else {
                            Toast.makeText(NoticeWriteActivity.this, "????????? ??????????????????", Toast.LENGTH_LONG).show();
                            //????????? ????????? ??????
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        //?????? ?????? (????????? ??????, ?????? ?????? ??? ??????????????? ??????)
                        t.printStackTrace();
                    }
                });
            }
        });
    }
    public void mOnClose(View v){ //??? ??????
        //????????? ????????????
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        //????????????(??????) ??????
        finish();
    }
    public void mOnRegist(View v){ //???????????? ??????
        //????????? ????????????
//        Intent intent = new Intent();
//        setResult(RESULT_OK, intent);
//        //????????????(??????) ??????
        finish();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //??????????????? ????????? ????????????
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        //??????????????? ????????? ??????
        return;
    }
}