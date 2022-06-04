package com.capstone.everykid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.capstone.everykid.Model.Board;
import com.capstone.everykid.ModifyActivity;
import com.capstone.everykid.R;
import com.capstone.everykid.View.Activity.CommunityFragment;
import com.capstone.everykid.View.Activity.MainParent;
import com.capstone.everykid.View.Activity.NoticeWriteActivity;
import com.capstone.everykid.View.Activity.PostActivity;

public class ModifyActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        Intent intent = getIntent();
        Board board = (Board) intent.getSerializableExtra("board");

        TextView titleView = findViewById(R.id.title_et);
        TextView contentView = findViewById(R.id.content_et);

        titleView.setText(board.getWRITE_SUBJECT());
        contentView.setText(board.getCONTENTS());
    }
    //확인버튼
    public void ok(View v){
        finish();

    }
    //취소
    public void cancel(View v){
        finish();
    }
}
