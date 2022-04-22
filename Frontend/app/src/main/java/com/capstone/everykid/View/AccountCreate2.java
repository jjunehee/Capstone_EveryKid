package com.capstone.everykid.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.capstone.everykid.R;

public class AccountCreate2 extends AppCompatActivity {
    EditText ca_name;
    EditText ca_phone;
    EditText ca_email;
    private Button ca_nextbtn1;
    private AlertDialog dialog;
    private boolean validate = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account2);

        ca_name = findViewById(R.id.editText_Name);
        ca_phone = findViewById(R.id.editText_Phone);
        ca_email = findViewById(R.id.editText_Email);


        //첫번째 next 버튼을 눌렀을 때
        ca_nextbtn1 = findViewById(R.id.ca_nextbtn1);
        ca_nextbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String parent_name = ca_name.getText().toString();
                final String parent_phone = ca_phone.getText().toString();
                final String parent_email = ca_email.getText().toString();


                //비어있는 항목이 있을 경우
                if (parent_email.equals("") || parent_name.equals("") || parent_phone.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AccountCreate2.this);
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            //다음 페이지 이동 성공
                            if (success) {
                                Intent intent = new Intent(AccountCreate2.this, AccountCreate3.class);
                                startActivity(intent);

                            //다음 페이지 이동 실패
                            } else {
                                Toast.makeText(getApplicationContext(), "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
            }
        });
    }
}