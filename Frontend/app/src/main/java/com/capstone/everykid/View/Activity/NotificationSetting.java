package com.capstone.everykid.View.Activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.everykid.Model.CreateAccountItem;
import com.capstone.everykid.Model.FcmUser;
import com.capstone.everykid.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

public class NotificationSetting extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private  String token;

    CheckBox chSubscribe;
    Button btSubscribe;
    public TextView tvResult;
    EditText etName;
    EditText etEmail;

    FirebaseDatabase mdatabase;
    DatabaseReference myRef;
    private String userId;
    private CreateAccountItem createAccountItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        chSubscribe = (CheckBox) findViewById(R.id.ch_checkbox);
        btSubscribe = (Button) findViewById(R.id.bt_subscribe);
        tvResult = (TextView) findViewById(R.id.tv_result);
        etName = (EditText) findViewById(R.id.et_name);
        etEmail = (EditText) findViewById(R.id.et_email);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.d(TAG,"token failed");
                        return;
                    }

                    token = task.getResult();
                    Log.d(TAG,"token : " + token);
                });

        mdatabase = FirebaseDatabase.getInstance();
        myRef = mdatabase.getReference("users");

        if(chSubscribe.isChecked()) {
            btSubscribe.setText("???????????????");
        } else {
            btSubscribe.setText("???????????????.");
        }

        String userName = createAccountItem.C_name;

        Query myQuery = myRef.orderByChild("name").equalTo(userName);
        myQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                    FcmUser user = userSnapshot.getValue(FcmUser.class);
                    String name = user.getName();
                    String email = user.getEmail();
                    tvResult.setText("Name: "+name+"                                                               Email: " +email+" ?????? ??????????????????.");
                    etName.setText(name);
                    etEmail.setText(email);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //
                Log.d(TAG, "Failed to read value", databaseError.toException());
            }
        });

        chSubscribe.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if(isChecked) {
                //
                btSubscribe.setText("???????????????.");
            } else {
                btSubscribe.setText("???????????????.");
            }
        });

        btSubscribe.setOnClickListener(view -> {

            if (etName.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "????????? ????????? ?????????.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (etEmail.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "????????? ????????? ?????????.", Toast.LENGTH_SHORT).show();
                return;
            }


            if (chSubscribe.isChecked()){
                //?????????????????????.
                //FirebaseMessaging.getInstance().subscribeToTopic("news");
                Log.d(TAG, "??????: Yes");
                Toast.makeText(getApplicationContext(), "??????:Yes", Toast.LENGTH_SHORT).show();

                //?????? ????????? ???????????? ????????? ????????? ???????????? ?????? ???????????? ???????????? ??????.
                //????????? userIda?????? ???????????? ?????? ?????? ??????... ?????? ???????????? ????????? ??????.
                if(TextUtils.isEmpty(userId)){
                    createUser(etName.getText().toString(), etEmail.getText().toString(), token);
                    Toast.makeText(getApplicationContext(), "??????:Yes create", Toast.LENGTH_SHORT).show();
                } else {
                    updateUser(etName.getText().toString(), etEmail.getText().toString(), token);
                    Toast.makeText(getApplicationContext(), "??????:Yes update", Toast.LENGTH_SHORT).show();
                }

            } else {
                //???????????????????????? ?????? ???????????? ????????? ??????
                deleteUser(token);
                Log.d(TAG, "??????: Cancel");
            }
        });
    }
    private void createUser(String name, String email, String token) {
        //TODO ??????????????? ?????? ??????
        ///????????? ???????????? ?????? ????????? ????????? ????????? User??? ????????????.
        //????????? ?????????????????? ????????? ????????????.
        Query myQuery = myRef.orderByChild("token").equalTo(token);
        //????????? ???????????????
        if(TextUtils.isEmpty(userId)){
            userId = myRef.push().getKey();
        }
        FcmUser user = new FcmUser(name, email, token);
        myRef.child(userId).setValue(user);
    }

    //?????? userId??? ?????? ?????? ?????? ??????????????? ??????.
    private void updateUser(String name, String email, String token){
        if (!TextUtils.isEmpty(name)){
            myRef.child(userId).child("name").setValue(name);
        }
        if (!TextUtils.isEmpty(email)){
            myRef.child(userId).child("email").setValue(email);
        }
        if (!TextUtils.isEmpty(token)){
            myRef.child(userId).child("token").setValue(token);
        }
    }

    //??????
    private void deleteUser(final String token){
        //???????????????????????? ???????????? ????????? ????????????.
        Query myQuery = myRef.orderByChild("token").equalTo(token);
        myQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    snapshot.getRef().removeValue();
                    Toast.makeText(getApplicationContext(), "????????????!", Toast.LENGTH_SHORT).show();
                    if(token!=null) {
                        tvResult.setText("????????? ???????????? ???????????????.");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "Failed to read value", databaseError.toException());
            }
        });
    }


}