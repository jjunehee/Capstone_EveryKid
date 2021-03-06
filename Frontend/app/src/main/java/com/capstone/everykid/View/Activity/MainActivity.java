package com.capstone.everykid.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.capstone.everykid.R;

public class MainActivity extends AppCompatActivity {
    Button teacher_btn;
    Button parent_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teacher_btn = (Button)findViewById(R.id.teacher_btn);
        parent_btn = (Button)findViewById(R.id.parent_btn);

        teacher_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), SigninTeacherActivity.class);
                startActivity(intent);
            }
        });

        parent_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), SigninParentActivity.class);
                startActivity(intent);
            }
        });
    }
}
