package com.capstone.everykid.View.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.widget.ImageView;
import com.capstone.everykid.Model.CreateAccountItem;
import com.capstone.everykid.R;


public class SettingActivity extends AppCompatActivity {
    EditText name, email, phone, id;
    CreateAccountItem createAccountItem;
    private final int GET_GALLERY_IMAGE =200;
    ImageView profileimg;

    Uri imgUri;

    boolean isFirst= true; //앱을 처음 실행한 것인가?
    boolean isChanged= false; //프로필을 변경한 적이 있는가?

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
    //확인 뒤로가기 버튼
    public void ok(View v){
        finish();
    }


    //수정버튼
    public void update(View v){
        saveData();
        finish();
    }


    public void OpenPhoto(View v){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 10:
                if(resultCode==RESULT_OK){
                    imgUri= data.getData();
                    Picasso.get().load(imgUri).into(profileimg);

                    isChanged=true;
                }
                break;
        }
    }

    void saveData(){

        if(imgUri==null) return;

        SimpleDateFormat sdf= new SimpleDateFormat("yyyMMddhhmmss"); //20191024111224
        String fileName= sdf.format(new Date())+".png";

        FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
        final StorageReference imgRef= firebaseStorage.getReference("profileImages/"+createAccountItem.Id+"_"+fileName);

        //파일 업로드
        UploadTask uploadTask=imgRef.putFile(imgUri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                    @Override
                    public void onSuccess(Uri uri) {
                        createAccountItem.porfileUri = uri.toString();

                        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                        DatabaseReference profileRef= firebaseDatabase.getReference("profile_images");

                        profileRef.child(createAccountItem.Id).setValue(createAccountItem.porfileUri);

                        Toast.makeText(SettingActivity.this, "프로필 수정 완료", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}