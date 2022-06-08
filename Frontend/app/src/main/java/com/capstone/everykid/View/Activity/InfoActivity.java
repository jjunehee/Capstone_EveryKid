package com.capstone.everykid.View.Activity;

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

import com.capstone.everykid.Model.CreateAccountItem;
import com.capstone.everykid.R;

public class InfoActivity extends Activity {
    TextView K_name_tv, K_address_tv, K_phone_tv;
    CreateAccountItem createAccountItem;
    Intent intent;
    String info="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_info);

        intent = getIntent();
        info= intent.getExtras().getString("info");

        if(info.equals("kinderinfo")){
            K_name_tv=(TextView)findViewById(R.id.info_txt1);
            K_address_tv=(TextView)findViewById(R.id.info_txt2);
            K_phone_tv=(TextView)findViewById(R.id.info_txt3);

            K_name_tv.setText(createAccountItem.K_name);
            K_address_tv.setText("주소: " + createAccountItem.K_address);
            K_phone_tv.setText("연락처: " + createAccountItem.K_phone);
        } else if(info.equals("teacherinfo")){
            K_name_tv=(TextView)findViewById(R.id.info_txt1);
            K_address_tv=(TextView)findViewById(R.id.info_txt2);
            K_phone_tv=(TextView)findViewById(R.id.info_txt3);

            K_name_tv.setText(createAccountItem.Tname);
//            K_address_tv.setText("선생님 이메일:" + );
//            K_phone_tv.setText("선생님 연락처" + );
        }




    }
    //확인 버튼 클릭
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