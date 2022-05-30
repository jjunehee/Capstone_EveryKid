package com.capstone.everykid.View.Activity;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.capstone.everykid.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

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
            try {
                ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), selectedImageUri);
                Bitmap bitmap = ImageDecoder.decodeBitmap(source);
                bitmap = resize(bitmap);
                String image = bitmapToByteArray(bitmap); //image 스트링에 비트맵으로 저장됨
                changeProfileImageToDB(image);


            } catch(Exception e){

            }
        }
    }

    private Bitmap resize(Bitmap bm){
        Configuration config = getResources().getConfiguration();
        if(config.smallestScreenWidthDp >= 800)
            bm = Bitmap.createScaledBitmap(bm, 400, 240, true);
        else if(config.smallestScreenWidthDp>=600)
            bm = Bitmap.createScaledBitmap(bm, 300, 180, true);
        else if(config.smallestScreenWidthDp>=400)
            bm = Bitmap.createScaledBitmap(bm, 200, 120, true);
        else if(config.smallestScreenWidthDp>=360)
            bm = Bitmap.createScaledBitmap(bm, 180, 108, true);
        else
            bm = Bitmap.createScaledBitmap(bm,160, 96 , true);
        return bm;
    }

    public String bitmapToByteArray(Bitmap bitmap){
        String image ="";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, stream);
        byte[] byteArray = stream.toByteArray();
        image="&image=" + byteArrayToBinaryString(byteArray);
        return image;
    }

    public static String byteArrayToBinaryString(byte[] b){
        StringBuilder sb = new StringBuilder();
        for (int i =0; i<b.length; ++i){
            sb.append(byteToBinaryString(b[i]));
        }
        return sb.toString();
    }

    public static String byteToBinaryString(byte n){
        StringBuilder sb = new StringBuilder("00000000");
        for (int bit = 0; bit <8; bit++){
            if(((n>> bit) & 1)>0){
                sb.setCharAt(7- bit, '1');
            }
        }
        return sb.toString();
    }

    private void changeProfileImageToDB(String image){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                } catch (JSONException e){
                    e.printStackTrace();
                    return;
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        //서버에 저장하는 코드

    }




}

