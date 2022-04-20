package com.capstone.everykid.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.capstone.everykid.R;

public class AccountCreate2 extends AppCompatActivity {
    EditText name;
    EditText phone;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account2);

        name = findViewById(R.id.editText_Name);
        phone = findViewById(R.id.editText_Phone);
        email = findViewById(R.id.editText_Email);

        Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AccountCreate3.class);
                startActivity(intent);
            }
        });
    }
}