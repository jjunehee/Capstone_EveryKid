package com.capstone.everykid.View.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.capstone.everykid.R;

public class ScheduleActivity extends Activity {
    TextView subjectView;
    TextView txt;
    private Intent intent;
    String subject;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_schedule);
        subjectView =(TextView)findViewById(R.id.txt_subject);
        txt =(TextView)findViewById(R.id.txt_content); // 학사일정 보여주는 textview
        txt.setMovementMethod(new ScrollingMovementMethod());

        intent = getIntent();
        subject = intent.getExtras().getString("subject");
        text= intent.getExtras().getString("context");
        if(subject!=null) {
            subjectView.setText(subject);
        }

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