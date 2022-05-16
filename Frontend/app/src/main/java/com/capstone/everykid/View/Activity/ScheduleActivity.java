package com.capstone.everykid.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.capstone.everykid.R;

public class ScheduleActivity extends AppCompatActivity {
    TextView txt;
    private Intent intent;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        txt =(TextView)findViewById(R.id.txt_schedule); // 학사일정 보여주는 textview

        intent = getIntent();
        text= intent.getExtras().getString("context");
        if(text==null){ //일정 없을 때
            txt.setText("일정 없음");
        }else{
            txt.setText(text);
        }

    }
    public void mOnClose(View v){
        //데이터 전달하기
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        //액티비티(팝업) 닫기
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