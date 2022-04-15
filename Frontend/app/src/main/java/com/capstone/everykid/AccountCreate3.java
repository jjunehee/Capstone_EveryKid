package com.capstone.everykid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccountCreate3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account3);

        Button Search_button = (Button) findViewById(R.id.btn_search);
        Search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Search_button.setText("확인");
            }
        });
    }
}