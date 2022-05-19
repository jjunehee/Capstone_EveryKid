package com.capstone.everykid.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.capstone.everykid.R;

public class SettingActivity extends AppCompatActivity {
    EditText name, email, phone, id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        name = (EditText) findViewById(R.id.userName_et);
        email = (EditText) findViewById(R.id.userEmail_et);
        phone = (EditText) findViewById(R.id.userPhone_et);
        id = (EditText) findViewById(R.id.userId_et);


    }
    public void ok(View v){

    }
    public void update(View v){

    }
}