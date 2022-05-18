package com.capstone.everykid.View.Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.capstone.everykid.R;
public class ChildAddActivity extends AppCompatActivity {
    private final int GET_GALLERY_IMAGE =200;
    private ImageView imageview;
    EditText name,age;
    Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_add);

        imageview=(ImageView)findViewById(R.id.user_image);
        name = (EditText) findViewById(R.id.addchildName_et);

        age = (EditText) findViewById(R.id.addchildAge_et);


    }

    //사진 등록 버튼
    public void opengallery(View v){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        startActivityForResult(intent, GET_GALLERY_IMAGE);
    }

    //아이 등록 버튼
    public void register(View v){

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            imageview.setImageURI(selectedImageUri);
        }
    }
}

