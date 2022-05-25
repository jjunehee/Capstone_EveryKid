package com.capstone.everykid.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.net.Uri;
import android.widget.EditText;
import android.widget.ImageView;

import com.capstone.everykid.Model.CreateAccountItem;
import com.capstone.everykid.R;

public class SettingActivity extends AppCompatActivity {
    EditText name, email, phone, id;
    CreateAccountItem createAccountItem;
    private final int GET_GALLERY_IMAGE =200;
    ImageView profileimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        profileimg=(ImageView)findViewById(R.id.user_updateimage);
        name = (EditText) findViewById(R.id.userName_et);
        email = (EditText) findViewById(R.id.userEmail_et);
        phone = (EditText) findViewById(R.id.userPhone_et);
        id = (EditText) findViewById(R.id.userId_et);

        name.setText(createAccountItem.Name);
        email.setText(createAccountItem.Email);
        phone.setText(createAccountItem.Phone);
        id.setText(createAccountItem.Id);


    }
    //확인버튼
    public void ok(View v){
        finish();
    }
    //수정버튼
    public void update(View v){

    }
    public void OpenPhoto(View v){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        startActivityForResult(intent, GET_GALLERY_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            profileimg.setImageURI(selectedImageUri);
        }
    }
}