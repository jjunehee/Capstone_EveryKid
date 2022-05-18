package com.capstone.everykid.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.capstone.everykid.Model.CreateAccountItem;
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

public class AccountCreate4 extends AppCompatActivity
{
    public final String TAG = "AccountCreate4";

    private EditText etid, etpwd;
    private Button btnregister;
    private PreferenceHelper preferenceHelper;
    CreateAccountItem createAccountItem;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account4);

        preferenceHelper = new PreferenceHelper(this);
        createAccountItem = (CreateAccountItem)getApplicationContext();


        etid = (EditText) findViewById(R.id.join_id);
        etpwd = (EditText) findViewById(R.id.join_pwd);

        createAccountItem.setId(etid.getText().toString());
        createAccountItem.setPwd(etpwd.getText().toString());

        btnregister=(Button)findViewById(R.id.join_btn);

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
        String id =createAccountItem.getId();
        String phone =createAccountItem.getPhone();
        String username =createAccountItem.getName();
        String password = createAccountItem.getPwd();
        String email = createAccountItem.getEmail();
       // final String k_id =((CreateAccountItem)getApplication()).getK_id();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RegisterInterface.REGIST_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RegisterInterface api = retrofit.create(RegisterInterface.class);
        Call<String> call = api.getParentRegist(id, phone, username, password, email);
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
            Toast.makeText(AccountCreate4.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(AccountCreate4.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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
