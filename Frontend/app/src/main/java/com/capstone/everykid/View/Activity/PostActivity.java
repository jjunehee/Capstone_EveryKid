package com.capstone.everykid.View.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.capstone.everykid.Model.Board;
import com.capstone.everykid.Model.CreateAccountItem;
import com.capstone.everykid.R;
import com.capstone.everykid.RetrofitAPI.RetrofitAPI;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {
    Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Intent intent = getIntent();
        board = (Board) intent.getSerializableExtra("board");

        Button modifyButton = findViewById(R.id.cm_modify);
        Button deleteButton = findViewById(R.id.cm_delete);
        TextView titleView = findViewById(R.id.title_tv);
        TextView dateView = findViewById(R.id.date_tv);
        TextView writerView = findViewById(R.id.writer_tv);
        TextView contentView = findViewById(R.id.content_tv);

        try {
            if((CreateAccountItem.User.equals("t") && CreateAccountItem.Id.equals(board.getT_ID())) ||
                CreateAccountItem.User.equals("p") && CreateAccountItem.Id.equals(board.getP_ID())){
                modifyButton.setVisibility(View.VISIBLE);
                deleteButton.setVisibility(View.VISIBLE);
            }
        } catch(NullPointerException e) {

        }

        titleView.setText(board.getWRITE_SUBJECT());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateView.setText(format.format(board.getWRITE_DATE()));
        writerView.setText((board.getT_ID() == null) ? board.getP_ID() : board.getT_ID());
        contentView.setText(board.getCONTENTS());
    }
    //확인버튼
    public void ok(View v){
       finish();

    }
    //수정버튼
    public void update(View v){
        //글쓴이라면 버튼 보이게하고
        Intent intent = new Intent(this, ModifyActivity.class);
        intent.putExtra("board", board);
        startActivity(intent);
    }
    //삭제버튼
    public void delete(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("삭제").setMessage("정말 삭제하시겠습니까?");

        builder.setPositiveButton("삭제", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:8080/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
                Call call = retrofitAPI.deleteBoard(board.getBKID());

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

                Intent intent = new Intent(PostActivity.this, MainParent.class);
                intent.putExtra("cmFlag", 1);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
