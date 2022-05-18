package com.capstone.everykid.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.capstone.everykid.Model.LoginRequest;
import com.capstone.everykid.R;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences auto;
    EditText userID,userPW;
    Button signinBtn, createBtn;
    CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
    String userId, userPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userID = (EditText) findViewById(R.id.userID);
        userPW = (EditText) findViewById(R.id.userPW);
        signinBtn = (Button) findViewById(R.id.button);
        createBtn = (Button) findViewById(R.id.button2);

        auto = getSharedPreferences("autoLogin",MainActivity.MODE_PRIVATE);
        userId = auto.getString("userID",null);
        userPwd = auto.getString("userPWD",null);

        if(userId != null && userPwd!= null){
            login();
        }else{
            //회원가입버튼
            createBtn.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( MainActivity.this, AccountCreate.class );
                    startActivity( intent );
                }
            });

            //로그인 버튼
            signinBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if(checkBox.isChecked()){
                        // 자동 로그인 데이터 저장
                        SharedPreferences auto = getSharedPreferences("autoLogin", MainActivity.MODE_PRIVATE);
                        SharedPreferences.Editor autoLoginEdit = auto.edit();
                        autoLoginEdit.putString("userID", userID.getText().toString());
                        autoLoginEdit.putString("userPWD", userPW.getText().toString());
                        autoLoginEdit.commit();
                    }

                    //임시
                    Intent intent = new Intent(MainActivity.this, MainParent.class);
                    startActivity(intent);
                }
            });
        }

    }

    public void login(){

    }
}