package com.capstone.everykid.View.Activity;

import static android.content.ContentValues.TAG;

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
import com.capstone.everykid.Model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.capstone.everykid.R;
import com.capstone.everykid.RetrofitClient;
import com.capstone.everykid.Model.G;

public class SigninParentActivity extends AppCompatActivity {

    EditText userID, userPW;
    Button signinBtn, createBtn;
    private RetrofitClient retrofitClient;
    private com.capstone.everykid.RetrofitAPI.RetrofitAPI RetrofitAPI;
    CreateAccountItem createAccountItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_parent);

        userID = (EditText) findViewById(R.id.userID);
        userPW = (EditText) findViewById(R.id.userPW);
        signinBtn = (Button) findViewById(R.id.signinparent_btn);
        createBtn = (Button) findViewById(R.id.signupparent_btn);

        if (!getPreferenceString("autoLoginId").equals("") && !getPreferenceString("autoLoginPw").equals("")) {
            checkAutoLogin(getPreferenceString("autoLoginId"));
        }

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninParentActivity.this, SignupActivity.class);
                intent.putExtra("User", "Parent");
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

                    AlertDialog.Builder builder = new AlertDialog.Builder(SigninParentActivity.this);
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
        LoginRequest loginRequest = new LoginRequest(userId, userPassword);

        //retrofit ??????
        retrofitClient = RetrofitClient.getInstance();
        RetrofitAPI = RetrofitClient.getRetrofitInterface();

        //loginRequest??? ????????? ???????????? ?????? init?????? ????????? getLoginResponse ????????? ????????? ??? ????????? ??????
        RetrofitAPI.getLoginResponse(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                Log.d("retrofit", "Data fetch success");

                //?????? ??????
                if (response.isSuccessful() && response.body() != null) {

                    //response.body()??? result??? ??????
                    LoginResponse result = response.body();

                    //?????? ?????? ??????
                    String token = result.getToken();

                    if (result.getStatus().equals(200)) {
                        String userId = userID.getText().toString();
                        String userPassword = userPW.getText().toString();

                        //?????? ????????? ?????? ?????? token ??????
                        setPreference("token", token);

                        setPreference("autoLoginId", userId);
                        setPreference("autoLoginPw", userPassword);
                        setPreference("autoUser","p");

                        createAccountItem.User = "p";
                        createAccountItem.Name=result.getPname();
                        createAccountItem.Email=result.getPemail();
                        createAccountItem.Phone=result.getPphone();
                        createAccountItem.Id=userId;
                        createAccountItem.K_name=result.getKname();
                        createAccountItem.K_phone=result.getKphone();
                        createAccountItem.K_address=result.getKaddress();
                        createAccountItem.Tname=result.getPtname();
                        createAccountItem.C_img=result.getC_img();

                        createAccountItem.C_name=result.getC_name();
                        createAccountItem.C_age=result.getC_age();
                        //???????????? ??? ????????? NumberFormatException??? ??????. ????????? ????????????.
                        try {
                            createAccountItem.K_kid = Long.parseLong(result.getKkid());
                            createAccountItem.P_kid=Long.parseLong(result.getPkid());
                        } catch (NumberFormatException e) {
                            return;
                        }

                        //??????
                        SharedPreferences preferences= getSharedPreferences("chataccount",MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("chat_name",createAccountItem.Name);
                        editor.commit();

                        Toast.makeText(SigninParentActivity.this, createAccountItem.Name + "??? ???????????????.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SigninParentActivity.this, MainParent.class);
                        intent.putExtra("userId", userId);
                        startActivity(intent);
                        SigninParentActivity.this.finish();

                    } else if(result.getStatus().equals(0)) {

                        String userId = userID.getText().toString();
                        String userPassword = userPW.getText().toString();

                        //?????? ????????? ?????? ?????? token ??????
                        setPreference(token, token);

                        setPreference("autoLoginId", userId);
                        setPreference("autoLoginPw", userPassword);
                        setPreference("autoUser","p");
                        createAccountItem.User = "p";
                        createAccountItem.Name=result.getPname();
                        createAccountItem.Email=result.getPemail();
                        createAccountItem.Phone=result.getPphone();
                        createAccountItem.Id=userId;
                        createAccountItem.K_name=result.getKname();
                        createAccountItem.K_phone=result.getKphone();
                        createAccountItem.K_address=result.getKaddress();
                        createAccountItem.Tname=result.getPtname();
                        createAccountItem.C_name=result.getC_name();
                        createAccountItem.C_age=result.getC_age();
                        createAccountItem.C_img=result.getC_img();
                        //???????????? ??? ????????? NumberFormatException??? ??????. ????????? ????????????.
                        try {
                            createAccountItem.K_kid = Long.parseLong(result.getKkid());
                            createAccountItem.P_kid=Long.parseLong(result.getPkid());
                        } catch (NumberFormatException e) {
                            return;
                        }

                        Toast.makeText(SigninParentActivity.this, createAccountItem.Name + "??? ???????????????.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SigninParentActivity.this, MainParent.class);
                        intent.putExtra("userId", userId);
                        startActivity(intent);
                        SigninParentActivity.this.finish();
                    }else if (result.getStatus().equals(300)) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(SigninParentActivity.this);
                        builder.setTitle("??????")
                                .setMessage("???????????? ???????????? ????????????.")
                                .setPositiveButton("??????", null)
                                .create()
                                .show();
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    } else if (result.getStatus().equals(400)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SigninParentActivity.this);
                        builder.setTitle("??????")
                                .setMessage("??????????????? ???????????? ????????????.")
                                .setPositiveButton("??????", null)
                                .create()
                                .show();
                    } else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(SigninParentActivity.this);
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
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SigninParentActivity.this);
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

        String userPassword = getPreferenceString("autoLoginPw");

        //loginRequest??? ???????????? ????????? id??? pw??? ??????
        LoginRequest loginRequest = new LoginRequest(userId, userPassword);

        //retrofit ??????
        retrofitClient = RetrofitClient.getInstance();
        RetrofitAPI = RetrofitClient.getRetrofitInterface();

        //loginRequest??? ????????? ???????????? ?????? init?????? ????????? getLoginResponse ????????? ????????? ??? ????????? ??????
        RetrofitAPI.getLoginResponse(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                Log.d("retrofit", "Data fetch success");

                //?????? ??????
                if (response.isSuccessful() && response.body() != null) {

                    //response.body()??? result??? ??????
                    LoginResponse result = response.body();

                    //?????? ?????? ??????
                    String token = result.getToken();


                    String userId =id;
                    String userPassword = getPreferenceString("autoLoginPw");

                    //?????? ????????? ?????? ?????? token ??????
                    setPreference(token, token);

                    setPreference("autoLoginId", userId);
                    setPreference("autoLoginPw", userPassword);
                    setPreference("autoUser","p");
                    createAccountItem.User = "p";
                    createAccountItem.Name=result.getPname();
                    createAccountItem.Email=result.getPemail();
                    createAccountItem.Phone=result.getPphone();
                    createAccountItem.Id=userId;
                    createAccountItem.K_name=result.getKname();
                    createAccountItem.K_phone=result.getKphone();
                    createAccountItem.K_address=result.getKaddress();
                    createAccountItem.Tname=result.getPtname();
                    createAccountItem.C_name=result.getC_name();
                    createAccountItem.C_age=result.getC_age();
                    createAccountItem.C_img=result.getC_img();
                    //???????????? ??? ????????? NumberFormatException??? ??????. ????????? ????????????.
                    try {
                        createAccountItem.K_kid = Long.parseLong(result.getKkid());
                        createAccountItem.P_kid=Long.parseLong(result.getPkid());
                    } catch (NumberFormatException e) {
                        return;
                    }
                    Toast.makeText(getApplication(), createAccountItem.Name + "??? ???????????????.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplication(), MainParent.class);
                    intent.putExtra("userId", userId);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    SigninParentActivity.this.finish();


                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SigninParentActivity.this);
//                builder.setTitle("??????")
//                        .setMessage("????????? ?????? ????????? ?????????????????????.\n ??????????????? ??????????????????.")
//                        .setPositiveButton("??????", null)
//                        .create()
//                        .show();
            }

        });

    }


}
