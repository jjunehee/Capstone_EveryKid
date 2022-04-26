package com.capstone.everykid.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.capstone.everykid.Model.PreferenceHelper;
import com.capstone.everykid.R;
import com.capstone.everykid.RetrofitAPI.RegisterInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;



public class AccountCreate2 extends AppCompatActivity
{
    public final String TAG = "SignUpActivity";

    private EditText etid, etphone, etusername, etpassword, etemail;
    private Button btnregister;
    private PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account2);

        preferenceHelper = new PreferenceHelper(this);

        etid = (EditText) findViewById(R.id.join_id);
        etphone = (EditText) findViewById(R.id.join_phone);
        etusername = (EditText) findViewById(R.id.join_name);
        etpassword = (EditText) findViewById(R.id.join_password);
        etemail = (EditText) findViewById(R.id.join_email);

        btnregister = (Button) findViewById(R.id.join_button);

        btnregister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                registerMe();
            }
        });
    }

    private void registerMe()
    {
        final String id = etid.getText().toString();
        final String phone = etphone.getText().toString();
        final String username = etusername.getText().toString();
        final String password = etpassword.getText().toString();
        final String email = etemail.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RegisterInterface.REGIST_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RegisterInterface api = retrofit.create(RegisterInterface.class);
        Call<String> call = api.getUserRegist(id, phone, username, password, email);
        call.enqueue(new Callback<String>()
        {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    Log.e("onSuccess", response.body());

                    String jsonResponse = response.body();
                    try
                    {
                        parseRegData(jsonResponse);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t)
            {
                Log.e(TAG, "에러 = " + t.getMessage());
            }
        });
    }

    private void parseRegData(String response) throws JSONException
    {
        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.optString("status").equals("true"))
        {
            saveInfo(response);
            Toast.makeText(AccountCreate2.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(AccountCreate2.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveInfo(String response)
    {
        preferenceHelper.putIsLogin(true);
        try
        {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true"))
            {
                JSONArray dataArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++)
                {
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    preferenceHelper.putName(dataobj.getString("name"));
                    preferenceHelper.putHobby(dataobj.getString("hobby"));
                }
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

}

/*
package com.capstone.everykid.View.Activity;

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

}

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
*/
