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

import android.widget.EditText;
import android.widget.Toast;

import com.capstone.everykid.Model.CreateAccountItem;
import com.capstone.everykid.Model.LoginRequest;
import com.capstone.everykid.Model.LoginRequestTeacher;
import com.capstone.everykid.Model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.capstone.everykid.Model.LoginResponseTeacher;
import com.capstone.everykid.R;
import com.capstone.everykid.RetrofitClient;

public class SigninTeacherActivity extends AppCompatActivity {

    EditText userID, userPW;
    Button signinBtn, createBtn;
    String userId, userPwd;
    private RetrofitClient retrofitClient;
    private com.capstone.everykid.RetrofitAPI.RetrofitAPI RetrofitAPI;

    CreateAccountItem createAccountItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_teacher);


        userID = (EditText) findViewById(R.id.userID);
        userPW = (EditText) findViewById(R.id.userPW);
        signinBtn = (Button) findViewById(R.id.signinteacher_btn);
        createBtn = (Button) findViewById(R.id.signupteacher_btn);

        if (!getPreferenceString("autoLoginId").equals("") && !getPreferenceString("autoLoginPw").equals("")) {
              checkAutoLogin(getPreferenceString("autoLoginId"));
        }

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninTeacherActivity.this, SignupActivity.class);
                intent.putExtra("User", "Teacher");
                startActivity(intent);
            }
        });

        //????????? ??????
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = userID.getText().toString();
                String pw = userPW.getText().toString();


                if (id.trim().length() == 0 || pw.trim().length() == 0 || id == null || pw == null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(SigninTeacherActivity.this);
                    builder.setTitle("??????")
                            .setMessage("????????? ????????? ??????????????????.")
                            .setPositiveButton("??????", null)
                            .create()
                            .show();
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                } else {
                    //????????? ??????
                    login();
                }

            }
        });
    }

    public void login() {
        String userId = userID.getText().toString().trim();
        String userPassword = userPW.getText().toString().trim();

        //loginRequest??? ???????????? ????????? id??? pw??? ??????
        LoginRequestTeacher loginRequestTeacher = new LoginRequestTeacher(userId, userPassword);

        //retrofit ??????
        retrofitClient = RetrofitClient.getInstance();
        RetrofitAPI = RetrofitClient.getRetrofitInterface();

        //loginRequest??? ????????? ???????????? ?????? init?????? ????????? getLoginResponse ????????? ????????? ??? ????????? ??????: ????????? ?????????
        RetrofitAPI.getLogin2Response(loginRequestTeacher).enqueue(new Callback<LoginResponseTeacher>() {
            @Override
            public void onResponse(Call<LoginResponseTeacher> call, Response<LoginResponseTeacher> response) {

                Log.d("retrofit", "Data fetch success");

                //?????? ??????
                if (response.isSuccessful() && response.body() != null) {

                    //response.body()??? result??? ??????
                    LoginResponseTeacher result = response.body();

                    //?????? ?????? ??????
                    String token = result.getTokenT();

                    //??????????????? status??? ???????????? ??????????????? ??????..

                    if (result.getStatus().equals(0)) {
                        String userId = userID.getText().toString();
                    String userPassword = userPW.getText().toString();
                    setPreference("token", token);
                    setPreference("autoLoginId", userId);
                    setPreference("autoLoginPw", userPassword);
                        setPreference("autoUser","t");
                    createAccountItem.User = "t";
                    createAccountItem.Name = result.getTname();
                    createAccountItem.Email = result.getTemail();
                    createAccountItem.Phone = result.getTphone();
                    createAccountItem.Id = result.getTid();
                    createAccountItem.K_name=result.getKnameT();
                    createAccountItem.K_phone=result.getKphoneT();
                    createAccountItem.K_address=result.getKaddressT();

                    //???????????? ??? ????????? NumberFormatException??? ??????. ????????? ????????????.
                    try {
                        createAccountItem.K_kid = Long.parseLong(result.getKkid());
                    } catch (NumberFormatException e) {
                        return;
                    }
                    System.out.println(result.getKkid());

                    Toast.makeText(SigninTeacherActivity.this, createAccountItem.Name + "????????? ???????????????.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SigninTeacherActivity.this, MainParent.class);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                    SigninTeacherActivity.this.finish();

                    }else if(result.getStatus().equals(200)) {
                  String userId = userID.getText().toString();
                    String userPassword = userPW.getText().toString();
                    setPreference("token", token);
                    setPreference("autoLoginId", userId);
                    setPreference("autoLoginPw", userPassword);
                        setPreference("autoUser","t");
                    createAccountItem.User = "t";
                    createAccountItem.Name = result.getTname();
                    createAccountItem.Email = result.getTemail();
                    createAccountItem.Phone = result.getTphone();
                    createAccountItem.Id = result.getTid();
                    createAccountItem.K_name=result.getKnameT();
                    createAccountItem.K_phone=result.getKphoneT();
                    createAccountItem.K_address=result.getKaddressT();

                    //???????????? ??? ????????? NumberFormatException??? ??????. ????????? ????????????.
                    try {
                        createAccountItem.K_kid = Long.parseLong(result.getKkid());
                    } catch (NumberFormatException e) {
                        return;
                    }
                    System.out.println(result.getKkid());

                    Toast.makeText(SigninTeacherActivity.this, createAccountItem.Name + "????????? ???????????????.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SigninTeacherActivity.this, MainParent.class);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                    SigninTeacherActivity.this.finish();

                    } else if (result.getStatus().equals(300)) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(SigninTeacherActivity.this);
                        builder.setTitle("??????")
                                .setMessage("???????????? ???????????? ????????????.")
                                .setPositiveButton("??????", null)
                                .create()
                                .show();
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    } else if (result.getStatus().equals(400)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SigninTeacherActivity.this);
                        builder.setTitle("??????")
                                .setMessage("??????????????? ???????????? ????????????.")
                                .setPositiveButton("??????", null)
                                .create()
                                .show();
                    } else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(SigninTeacherActivity.this);
                        builder.setTitle("??????")
                                .setMessage("????????? ?????? ????????? ?????????????????????.")
                                .setPositiveButton("??????", null)
                                .create()
                                .show();

                    }
                }
            }

            //?????? ??????
            @Override
            public void onFailure(Call<LoginResponseTeacher> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SigninTeacherActivity.this);
                builder.setTitle("??????")
                        .setMessage("????????? ?????? ????????? ?????????????????????.\n ??????????????? ??????????????????.")
                        .setPositiveButton("??????", null)
                        .create()
                        .show();
            }
        });
    }

    //???????????? ?????? ???????????? ????????????
    public void setPreference(String key, String value) {
        SharedPreferences pref = getSharedPreferences("DATA_STORE", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    //?????? ???????????? ????????? ????????? ????????????
    public String getPreferenceString(String key) {
        SharedPreferences pref = getSharedPreferences("DATA_STORE", MODE_PRIVATE);
        return pref.getString(key, "");
    }


    //????????? ?????????
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(userID.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(userPW.getWindowToken(), 0);
    }

    //?????? ?????? ??? ????????? ?????????
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

    //?????? ????????? ??????
    public void checkAutoLogin(String id) {
        String userId = id;
        String userPassword =getPreferenceString("autoLoginPw");

        //loginRequest??? ???????????? ????????? id??? pw??? ??????
        LoginRequestTeacher loginRequestTeacher = new LoginRequestTeacher(userId, userPassword);

        //retrofit ??????
        retrofitClient = RetrofitClient.getInstance();
        RetrofitAPI = RetrofitClient.getRetrofitInterface();

        //loginRequest??? ????????? ???????????? ?????? init?????? ????????? getLoginResponse ????????? ????????? ??? ????????? ??????: ????????? ?????????
        RetrofitAPI.getLogin2Response(loginRequestTeacher).enqueue(new Callback<LoginResponseTeacher>() {
            @Override
            public void onResponse(Call<LoginResponseTeacher> call, Response<LoginResponseTeacher> response) {

                Log.d("retrofit", "Data fetch success");

                //?????? ??????
                if (response.isSuccessful() && response.body() != null) {

                    //response.body()??? result??? ??????
                    LoginResponseTeacher result = response.body();

                    //?????? ?????? ??????
                    String token = result.getTokenT();

                    //??????????????? status??? ???????????? ??????????????? ??????..
                    setPreference("token", token);
                    setPreference("autoLoginId", userId);
                    setPreference("autoLoginPw", userPassword);
                    setPreference("autoUser","t");
                    createAccountItem.User = "t";
                    createAccountItem.Name = result.getTname();
                    createAccountItem.Email = result.getTemail();
                    createAccountItem.Phone = result.getTphone();
                    createAccountItem.Id = result.getTid();
                    createAccountItem.K_name=result.getKnameT();
                    createAccountItem.K_phone=result.getKphoneT();
                    createAccountItem.K_address=result.getKaddressT();

                    //???????????? ??? ????????? NumberFormatException??? ??????. ????????? ????????????.
                    try {
                        createAccountItem.K_kid = Long.parseLong(result.getKkid());
                    } catch (NumberFormatException e) {
                        return;
                    }
                    System.out.println(result.getKkid());

                    Toast.makeText(SigninTeacherActivity.this, createAccountItem.Name + "????????? ???????????????.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SigninTeacherActivity.this, MainParent.class);
                    intent.putExtra("userId", userId);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    SigninTeacherActivity.this.finish();

                }
            }

            //?????? ??????
            @Override
            public void onFailure(Call<LoginResponseTeacher> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SigninTeacherActivity.this);
                builder.setTitle("??????")
                        .setMessage("????????? ?????? ????????? ?????????????????????.\n ??????????????? ??????????????????.")
                        .setPositiveButton("??????", null)
                        .create()
                        .show();
            }
        });


    }

}
