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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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


        createBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, AccountCreate.class );
                startActivity( intent );
            }
        });

        signinBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String ID = userID.getText().toString();
                String PW = userPW.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                 @Override
                 public void onResponse(String response){
                     try{
                         JSONObject jsonObject = new JSONObject(response);
                         boolean success = jsonObject.getBoolean("success");

                         if(success){
                             Toast.makeText(getApplicationContext(),"로그인 성공",Toast.LENGTH_SHORT).show();
                             Intent intent = new Intent(MainActivity.this, MainParent.class);
                             startActivity(intent);
                         }
                         else{
                             Toast.makeText( getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT ).show();
                             return;
                         }
                     }catch (JSONException e){
                         e.printStackTrace();
                     }
                 }
                };
                LoginRequest loginRequest = new LoginRequest(ID, PW,responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });

    }
}