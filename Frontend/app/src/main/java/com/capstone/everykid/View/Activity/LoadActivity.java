package com.capstone.everykid.View.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.capstone.everykid.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.List;
import java.util.stream.Collectors;

public class LoadActivity extends AppCompatActivity {

    ImageView load;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imgload);
        String getName = getIntent().getStringExtra("name");
        String getDate = getIntent().getStringExtra("date");
        String getTime = getIntent().getStringExtra("time");

        load=(ImageView) findViewById(R.id.loadimg);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        StorageReference pathReference = storageReference.child("image_store");
        if(pathReference == null) {
            Toast.makeText(LoadActivity.this, "저장소에 사진이 없습니다.",Toast.LENGTH_SHORT).show();
        } else {
            StorageReference submitProfile = storageReference.child("image_store/" + getName + " " + getDate + " " + getTime + ".jpg");
            submitProfile.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(LoadActivity.this).load(uri).into(load);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    load.setImageResource(R.drawable.exit);

                }
            });
        }
    }
}
