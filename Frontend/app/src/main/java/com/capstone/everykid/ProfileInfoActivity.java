package com.capstone.everykid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class ProfileInfoActivity extends AppCompatActivity {
    private Intent intent;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        byte[] byteArray = getIntent().getByteArrayExtra("attendimage");
        Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        ImageView ivImage = findViewById(R.id.attend_img);
        ivImage.setImageBitmap(image);
    }
}