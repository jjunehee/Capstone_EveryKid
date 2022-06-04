package com.capstone.everykid.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import com.capstone.everykid.Model.Board;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.capstone.everykid.R;
import com.capstone.everykid.RetrofitAPI.RetrofitAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModifyActivity extends AppCompatActivity {
    Board board;
    TextView titleView;
    TextView contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        Intent intent = getIntent();
        board = (Board) intent.getSerializableExtra("board");

        titleView = (TextView) findViewById(R.id.title_et);
        contentView = (TextView) findViewById(R.id.content_et);

        titleView.setText(board.getWRITE_SUBJECT());
        contentView.setText(board.getCONTENTS());
    }

    public void ok(View v){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call call = retrofitAPI.modifyBoard(board.getBKID(), titleView.getText().toString(), contentView.getText().toString());

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()) {
                    //통신이 성공된 경우
                    String result = response.body();
                    System.out.println("통신 완료");
                    //data 에서 필요한 내용 꺼내 쓰기
                } else {
                    //통신이 실패한 경우
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //통신 실패 (인터넷 끊김, 예외 발생 등 시스템적인 이유)
                t.printStackTrace();
            }
        });

        Intent intent = new Intent(this, MainParent.class);
        intent.putExtra("cmFlag", 1);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    //수정버튼
    public void cancel(View v){
        //글쓴이라면 버튼 보이게하고
        finish();
    }
}