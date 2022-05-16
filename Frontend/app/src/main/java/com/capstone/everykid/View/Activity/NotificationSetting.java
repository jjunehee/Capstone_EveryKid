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
            btSubscribe.setText("구독합니다");
        } else {
            btSubscribe.setText("해지합니다.");
        }

        Query myQuery = myRef.orderByChild("userId").equalTo(userId);
        myQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                    FcmUser user = userSnapshot.getValue(FcmUser.class);
                    String name = user.getName();
                    String email = user.getEmail();
                    tvResult.setText("Name: "+name+" Email: " +email+" 으로 구독중입니다." + token);
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
                btSubscribe.setText("구독합니다.");
            } else {
                btSubscribe.setText("해지합니다.");
            }
        });

        btSubscribe.setOnClickListener(view -> {

            if (etName.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "이름을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (etEmail.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "메일을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                return;
            }


            if (chSubscribe.isChecked()){
                //구독신청합니다.
                //FirebaseMessaging.getInstance().subscribeToTopic("news");
                Log.d(TAG, "구독: Yes");
                Toast.makeText(getApplicationContext(), "구독:Yes", Toast.LENGTH_SHORT).show();

                //기존 저장된 데이터가 있는지 없는지 체크해서 새로 만들든지 업데이트 한다.
                //그런데 userIda말고 토큰으로 해야 할듯 한데... 일단 작동하니 이대로 하자.
                if(TextUtils.isEmpty(userId)){
                    createUser(etName.getText().toString(), etEmail.getText().toString(), token);
                    Toast.makeText(getApplicationContext(), "구독:Yes create", Toast.LENGTH_SHORT).show();
                } else {
                    updateUser(etName.getText().toString(), etEmail.getText().toString(), token);
                    Toast.makeText(getApplicationContext(), "구독:Yes update", Toast.LENGTH_SHORT).show();
                }

            } else {
                //데이터베이스에서 완전 삭제하는 것으로 처리
                deleteUser(token);
                Log.d(TAG, "구독: Cancel");
            }
        });
    }
    private void createUser(String name, String email, String token) {
        //TODO 등록여부에 따라 분기
        ///토큰을 검색해서 같은 토큰이 없으면 새로운 User를 생성한다.
        //기존에 유저아이디가 없으면 생성한다.
        Query myQuery = myRef.orderByChild("token").equalTo(token);
        //어떻게 체크하지???
        if(TextUtils.isEmpty(userId)){
            userId = myRef.push().getKey();
        }
        FcmUser user = new FcmUser(name, email, token);
        myRef.child(userId).setValue(user);
    }

    //이미 userId가 있는 경우 값을 업데이트만 한다.
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

    //삭제
    private void deleteUser(final String token){
        //데이터베이스에서 삭제하는 것으로 처리하자.
        Query myQuery = myRef.orderByChild("name").equalTo("junhee");
        myQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    snapshot.getRef().removeValue();
                    Toast.makeText(getApplicationContext(), "삭제완료!", Toast.LENGTH_SHORT).show();
                    if(token!=null) {
                        tvResult.setText(token.substring(0, 100));
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