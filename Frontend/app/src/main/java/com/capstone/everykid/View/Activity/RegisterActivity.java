package com.capstone.everykid.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.capstone.everykid.R;
import com.capstone.everykid.RetrofitAPI.RetrofitAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private EditText title, content;
    private Button reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        title=(EditText) findViewById(R.id.title_et);
        content=(EditText) findViewById(R.id.content_et);
        reg=(Button)findViewById(R.id.reg_button);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String TITLE = title.getText().toString();
                String CONTENT = content.getText().toString();
                String USER_ID="bbbb";
                String K_ID = "1";

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:8080/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

                Call<Boolean> call = retrofitAPI.registBoard(K_ID, "null", USER_ID, TITLE, CONTENT); //tID를 null이나, 빈 문자열로 비워두면 매핑이 안된다. 서버에서 처리해주도록 하자.

                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.isSuccessful()) {
                            //통신이 성공된 경우
                            System.out.println("111");
                            Boolean result = response.body();
                            System.out.println(result);
                            System.out.println("통신 완료");
                            Toast.makeText(getApplicationContext(),"게시글 업로드 완료",Toast.LENGTH_SHORT).show();

                            //MainParent로 가는 intent, MainTheacher로 가는 intent 하나씩 생성
                            Intent intent = new Intent(RegisterActivity.this, MainParent.class);
                            intent.putExtra("cmFlag", 1);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            //data 에서 필요한 내용 꺼내 쓰기
                        } else {
                            //통신이 실패한 경우
                            System.out.println("222");
                            Toast.makeText( getApplicationContext(), "업로드 실패", Toast.LENGTH_SHORT ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        //통신 실패 (인터넷 끊김, 예외 발생 등 시스템적인 이유)
                        System.out.println("333");
                        t.printStackTrace();
                        Toast.makeText( getApplicationContext(), "업로드 실패", Toast.LENGTH_SHORT ).show();
                    }
                });
            }
        });
    }
}