package com.capstone.everykid.View.Activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.capstone.everykid.Model.BoardList;
import com.capstone.everykid.Model.G;
import com.capstone.everykid.R;
import com.capstone.everykid.Model.PreferenceHelper;
import com.capstone.everykid.RetrofitAPI.RegisterInterface;
import com.capstone.everykid.RetrofitAPI.RetrofitAPI;
import com.capstone.everykid.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SignupActivity extends AppCompatActivity
{
    public final String TAG = "SignupActivity";

    private EditText etid, etphone, etusername, etpassword, etemail, ealias, ekindergarten;
    private TextView text;
    private Button btnregister, btnduplicateCheck, btnkindergarten;
    private PreferenceHelper preferenceHelper;
    private String accountUser,kindergartenID;
    private Intent intent;
    private Long kkid;
    private RetrofitClient retrofitClient;
    private com.capstone.everykid.RetrofitAPI.RetrofitAPI RetrofitAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        intent = getIntent();
        accountUser= intent.getExtras().getString("User"); //회원가입하는 사용자가 선생님인지 학부모인지

        retrofitClient = RetrofitClient.getInstance();
        RetrofitAPI = RetrofitClient.getRetrofitInterface();


        //학부모 회원가입인지 선생님 회원가입인지 임시로 띄워둠
        text=findViewById(R.id.textView6);
        text.setText(accountUser);

        preferenceHelper = new PreferenceHelper(this);

        etid = (EditText) findViewById(R.id.id);
        etphone = (EditText) findViewById(R.id.phone);
        etusername = findViewById(R.id.name);
        etpassword = (EditText) findViewById(R.id.pw);
        etemail = (EditText) findViewById(R.id.email);
        ealias = (EditText) findViewById(R.id.alias);
        ekindergarten = (EditText) findViewById(R.id.kindergarten) ;

        etid.setText(intent.getExtras().getString("etid"));
        etphone.setText(intent.getExtras().getString("etphone"));
        etusername.setText(intent.getExtras().getString("etusername"));
        etpassword.setText(intent.getExtras().getString("etpassword"));
        etemail.setText(intent.getExtras().getString("etemail"));
        ealias.setText(intent.getExtras().getString("ealias"));
        ekindergarten.setText(intent.getExtras().getString("ekindergarten"));
        kkid = intent.getExtras().getLong("kkid");
        kindergartenID=Long.toString(kkid);
        System.out.println(kindergartenID);

        btnregister = (Button) findViewById(R.id.button4);
        btnduplicateCheck = (Button) findViewById(R.id.duplicateCheck_btn);
        btnkindergarten = (Button) findViewById(R.id.kindergarten_btn);


        btnregister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(accountUser.equals("Parent")){
                    registerParent();
                    Intent intent = new Intent(getApplicationContext(), SigninParentActivity.class);
                    startActivity(intent);
                }else if(accountUser.equals("Teacher")){
                    registerTeacher();
                    Intent intent = new Intent(getApplicationContext(), SigninTeacherActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnkindergarten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), findKindergarten.class);
                intent.putExtra("User", accountUser);
                intent.putExtra("etid", etid.getText().toString());
                intent.putExtra("etphone", etphone.getText().toString());
                intent.putExtra("etusername", etusername.getText().toString());
                intent.putExtra("etpassword", etpassword.getText().toString());
                intent.putExtra("etemail", etemail.getText().toString());
                intent.putExtra("ealias", ealias.getText().toString());
                intent.putExtra("ekindergarten", ekindergarten.getText().toString());
                startActivity(intent);
            }
        });
    }

    //    학부모의 회원가입
    private void registerParent()
    {
        final String id = etid.getText().toString();
        final String phone = etphone.getText().toString();
        final String username = etusername.getText().toString();
        final String password = etpassword.getText().toString();
        final String email = etemail.getText().toString();
        final String kindergarten = kindergartenID;





        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RegisterInterface.REGIST_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RegisterInterface api = retrofit.create(RegisterInterface.class);
        Call<String> call = api.getParentRegist(id, phone, username, password, email, kindergarten);
        call.enqueue(new Callback<String>()
        {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    Log.e("onSuccess", response.body());
                    Toast.makeText(SignupActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(SignupActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void registerTeacher()
    {
        final String id = etid.getText().toString();
        final String phone = etphone.getText().toString();
        final String username = etusername.getText().toString();
        final String password = etpassword.getText().toString();
        final String email = etemail.getText().toString();
        final String kindergarten = kindergartenID;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RegisterInterface.REGIST_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RegisterInterface api = retrofit.create(RegisterInterface.class);
        Call<String> call = api.getTeacherRegist(id, phone, username, password, email, kindergarten);
        call.enqueue(new Callback<String>()
        {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    Log.e("onSuccess", response.body());
                    Toast.makeText(SignupActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();

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
        }
        else
        {
            Toast.makeText(SignupActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }


}