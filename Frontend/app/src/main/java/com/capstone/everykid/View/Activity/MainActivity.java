package com.capstone.everykid.View.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.capstone.everykid.Model.LoginRequest;
import com.capstone.everykid.Model.LoginResponse;
import com.capstone.everykid.R;
import com.capstone.everykid.RetrofitAPI.initMyApi;
import com.capstone.everykid.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText userID, userPW;
    Button signinBtn, createBtn;
    String userId, userPwd;
    private RetrofitClient retrofitClient;
    private com.capstone.everykid.RetrofitAPI.initMyApi initMyApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userID = (EditText) findViewById(R.id.userID);
        userPW = (EditText) findViewById(R.id.userPW);
        signinBtn = (Button) findViewById(R.id.button);
        createBtn = (Button) findViewById(R.id.button2);

        if (!getPreferenceString("autoLoginId").equals("") && !getPreferenceString("autoLoginPw").equals("")) {
            checkAutoLogin(getPreferenceString("autoLoginId"));
        }

//        auto = getSharedPreferences("autoLogin", MainActivity.MODE_PRIVATE);
//        userId = auto.getString("userID", null);
//        userPwd = auto.getString("userPWD", null);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AccountCreate.class);
                startActivity(intent);
            }
        });

        //로그인 버튼
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = userID.getText().toString();
                String pw = userPW.getText().toString();

                if (id.trim().length() == 0 || pw.trim().length() == 0 || id == null || pw == null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("알림")
                            .setMessage("로그인 정보를 입력바랍니다.")
                            .setPositiveButton("확인", null)
                            .create()
                            .show();
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                } else {
                    //로그인 통신
                    login();
                }

//                // 자동 로그인 데이터 저장
//                SharedPreferences auto = getSharedPreferences("autoLogin", MainActivity.MODE_PRIVATE);
//                SharedPreferences.Editor autoLoginEdit = auto.edit();
//                autoLoginEdit.putString("userID", userID.getText().toString());
//                autoLoginEdit.putString("userPWD", userPW.getText().toString());
//                autoLoginEdit.commit();


//                임시
//                Intent intent = new Intent(MainActivity.this, MainParent.class);
//                startActivity(intent);

            }
        });
    }

    public void login() {
        String userId = userID.getText().toString().trim();
        String userPassword = userPW.getText().toString().trim();

        //loginRequest에 사용자가 입력한 id와 pw를 저장
        LoginRequest loginRequest = new LoginRequest(userId, userPassword);

        //retrofit 생성
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        //loginRequest에 저장된 데이터와 함께 init에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음
        initMyApi.getLoginResponse(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                Log.d("retrofit", "Data fetch success");

                //통신 성공
                if (response.isSuccessful() && response.body() != null) {

                    //response.body()를 result에 저장
                    LoginResponse result = response.body();

                    //받은 코드 저장
                    String resultCode = result.getResultCode();

                    //받은 토큰 저장
                    String token = result.getToken();

                    String success = "200"; //로그인 성공
                    String errorId = "300"; //아이디 일치x
                    String errorPw = "400"; //비밀번호 일치x


                    if (resultCode.equals(success)) {
                        String userId = userID.getText().toString();
                        String userPassword = userPW.getText().toString();

                        //다른 통신을 하기 위해 token 저장
                        setPreference(token, token);

                        setPreference("autoLoginId", userId);
                        setPreference("autoLoginPw", userPassword);


                        Toast.makeText(MainActivity.this, userID + "님 환영합니다.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, MainParent.class);
                        intent.putExtra("userId", userId);
                        startActivity(intent);
                        MainActivity.this.finish();

                    } else if (resultCode.equals(errorId)) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("알림")
                                .setMessage("아이디가 존재하지 않습니다.\n 고객센터에 문의바랍니다.")
                                .setPositiveButton("확인", null)
                                .create()
                                .show();
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    } else if (resultCode.equals(errorPw)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("알림")
                                .setMessage("비밀번호가 일치하지 않습니다.\n 고객" +
                                        "센터에 문의바랍니다.")
                                .setPositiveButton("확인", null)
                                .create()
                                .show();
                    } else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("알림")
                                .setMessage("예기치 못한 오류가 발생하였습니다.\n 고객센터에 문의바랍니다.")
                                .setPositiveButton("확인", null)
                                .create()
                                .show();

                    }
                }
            }

            //통신 실패
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("알림")
                        .setMessage("예기치 못한 오류가 발생하였습니다.\n 고객센터에 문의바랍니다.")
                        .setPositiveButton("확인", null)
                        .create()
                        .show();
            }
        });
    }

    //데이터를 내부 저장소에 저장하기
    public void setPreference(String key, String value) {
        SharedPreferences pref = getSharedPreferences("DATA_STORE", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    //내부 저장소에 저장된 데이터 가져오기
    public String getPreferenceString(String key) {
        SharedPreferences pref = getSharedPreferences("DATA_STORE", MODE_PRIVATE);
        return pref.getString(key, "");
    }


    //키보드 숨기기
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(userID.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(userPW.getWindowToken(), 0);
    }

    //화면 터치 시 키보드 내려감
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

    //자동 로그인 유저
    public void checkAutoLogin(String id) {

        //Toast.makeText(this, id + "님 환영합니다.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainParent.class);
        startActivity(intent);
        finish();

    }

}

