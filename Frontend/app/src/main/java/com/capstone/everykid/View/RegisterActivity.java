package com.capstone.everykid.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.capstone.everykid.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText id, pw;
    private Button reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        id=findViewById(R.id.editText_ID);
        pw=findViewById(R.id.editText_PW);
    }
}