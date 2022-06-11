package com.capstone.everykid.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.capstone.everykid.Model.CreateAccountItem;
import com.capstone.everykid.R;


public class AccountCreate2 extends AppCompatActivity {

    private EditText etphone, etusername,etemail;
    private Button btnregister;
    CreateAccountItem createAccountItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account2);



        etusername = (EditText) findViewById(R.id.join_name);
        etphone = (EditText) findViewById(R.id.join_phone);
        etemail = (EditText) findViewById(R.id.join_email);
        String name =etusername.getText().toString();


        btnregister = (Button) findViewById(R.id.join_next2);

        btnregister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AccountCreate4.class);
                startActivity(intent);
            }
        });
    }
}
