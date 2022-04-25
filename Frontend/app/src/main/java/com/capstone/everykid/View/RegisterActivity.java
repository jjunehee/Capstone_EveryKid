package com.capstone.everykid.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.capstone.everykid.R;
import com.capstone.everykid.Model.WriteRequest;
import com.capstone.everykid.Model.CurrentTime;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText title, content;
    private Button reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        title=(EditText) findViewById(R.id.title_et);
        content=(EditText) findViewById(R.id.content_et);
        reg=(Button)findViewById(R.id.reg_button);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TITLE = title.getText().toString();
                String CONTENT = content.getText().toString();
                int HITS=0;
                int B_ID=0;
                String USER_ID="";
                String WRITE_DATE= CurrentTime.getCurrentTime();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("successs");

                            if(success){
                                Toast.makeText(getApplicationContext(),"게시글 업로드 완료",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, CommunityFragment.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText( getApplicationContext(), "업로드 실패", Toast.LENGTH_SHORT ).show();
                                return;
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };
                WriteRequest writeRequest = new WriteRequest(B_ID, WRITE_DATE, USER_ID, TITLE, CONTENT, HITS, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(writeRequest);

            }
        });
    }
}