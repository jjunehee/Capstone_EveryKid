package com.capstone.everykid.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.capstone.everykid.Model.Globals;
import com.capstone.everykid.R;

public class AccountCreate3 extends AppCompatActivity {

    private EditText k_id;
    private Button search_btn, next_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account3);
        search_btn = (Button) findViewById(R.id.btn_search);
        next_btn = (Button) findViewById(R.id.join_next3);
        next_btn.setVisibility(View.GONE);

        k_id=findViewById(R.id.join_kId);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next_btn.setVisibility(View.VISIBLE);
            }
        });


        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Globals)getApplication() ).setK_id(k_id.getText().toString());
                Intent intent = new Intent(getApplicationContext(), AccountCreate4.class);
                startActivity(intent);
            }
        });
    }
}