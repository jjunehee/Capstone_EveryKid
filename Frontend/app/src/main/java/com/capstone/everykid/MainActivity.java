package com.capstone.everykid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.*;
import java.net.*;

import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userID,userPW;
    Button signinBtn, createBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userID = (EditText) findViewById(R.id.userID);
        userPW = (EditText) findViewById(R.id.userPW);
        signinBtn = (Button) findViewById(R.id.button);
        createBtn = (Button) findViewById(R.id.button2);
        signinBtn.setOnClickListener(btnListener);
        createBtn.setOnClickListener(btnListener);
    }
    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings){
            try{
                String str;
                URL url = new URL ("http://"); //보낼 jsp주소
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST"); //데이터를 post방식으로 전송
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&pwd="+strings[1]+"&type="+strings[2];
                osw.write(sendMsg);
                osw.flush();
                //jsp와 통신이 정상적으로 되었을 때
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    //jsp에서 보낸 값 받음
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //jsp로부터 받은 리턴값값
            return receiveMsg;
        }
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button : // 로그인 버튼 눌렀을 경우
                    String signinid = userID.getText().toString();
                    String signinpw = userPW.getText().toString();
                    try {
                        String result  = new CustomTask().execute(signinid,signinpw,"login").get();
                        if(result.equals("true")) {
                            Toast.makeText(MainActivity.this,"로그인",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, MainParent.class);//일단 바로 부모님 메인화면으로..
                            startActivity(intent);
                            finish();
                        } else if(result.equals("false")) {
                            Toast.makeText(MainActivity.this,"아이디 또는 비밀번호가 틀렸습니다.",Toast.LENGTH_SHORT).show();
                            userID.setText("");
                            userPW.setText("");
                        } else if(result.equals("noId")) {
                            Toast.makeText(MainActivity.this,"존재하지 않는 아이디입니다.",Toast.LENGTH_SHORT).show();
                            userID.setText("");
                            userPW.setText("");
                        }
                    }catch (Exception e) {}
                    break;
                case R.id.button2 : // 회원가입
                    Intent intent = new Intent(getApplicationContext(), AccountCreate.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}