package com.capstone.everykid.View.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.capstone.everykid.Model.CreateAccountItem;
import com.capstone.everykid.R;

public class InfoActivity extends Activity {
    Intent intent;
    TextView K_name_tv, K_address_tv, K_phone_tv;
    String kinder_name, kinder_address, kinder_phone;
    CreateAccountItem createAccountItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_info);
        intent = getIntent();
//        kinder_name= intent.getExtras().getString("K_name");
//        kinder_phone= intent.getExtras().getString("K_phone");
//        kinder_address= intent.getExtras().getString("K_address");

        K_name_tv.setText(createAccountItem.K_name);//널오브젝트 수정
        K_address_tv.setText(createAccountItem.K_address);
        K_phone_tv.setText(createAccountItem.K_phone);


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