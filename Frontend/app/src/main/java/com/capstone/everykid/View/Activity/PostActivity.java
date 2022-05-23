package com.capstone.everykid.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.capstone.everykid.Model.Board;
import com.capstone.everykid.R;

public class PostActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Intent intent = getIntent();
        Board board = (Board) intent.getSerializableExtra("board");

        TextView titleView = findViewById(R.id.title_tv);
        TextView dateView = findViewById(R.id.date_tv);
        TextView writerView = findViewById(R.id.writer_tv);
        TextView contentView = findViewById(R.id.content_tv);

        titleView.setText(board.getWRITE_SUBJECT());
        //dateView.setText(board.getWRITE_DATE().toString());
        writerView.setText((board.getT_ID() == null) ? board.getP_ID() : board.getT_ID());
        contentView.setText(board.getCONTENTS());
    }
}
