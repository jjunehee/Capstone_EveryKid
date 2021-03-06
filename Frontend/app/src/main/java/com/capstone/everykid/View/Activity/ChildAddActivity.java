package com.capstone.everykid.View.Activity;

import static android.content.ContentValues.TAG;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.capstone.everykid.Model.CreateAccountItem;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.capstone.everykid.Model.CreateAccountItem;
import com.capstone.everykid.Model.PreferenceHelper;
import com.capstone.everykid.R;
import com.capstone.everykid.RetrofitAPI.RegisterInterface;
import com.capstone.everykid.RetrofitAPI.RetrofitAPI;
import com.capstone.everykid.RetrofitClient;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ChildAddActivity extends AppCompatActivity {
    CreateAccountItem createAccountItem;
    private final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview;
    private RetrofitClient retrofitClient;
    private com.capstone.everykid.RetrofitAPI.RetrofitAPI RetrofitAPI;
    private EditText name, age;
    private Button btn;
    private PreferenceHelper preferenceHelper;
    private Intent intent, intent2;
    private String pkid = Long.toString(createAccountItem.P_kid), img;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_add);

        intent = getIntent();
        imageview = (ImageView) findViewById(R.id.user_image);
        name = (EditText) findViewById(R.id.addchildName_et);
        age = (EditText) findViewById(R.id.addchildAge_et);

        retrofitClient = RetrofitClient.getInstance();
        RetrofitAPI = RetrofitClient.getRetrofitInterface();
        preferenceHelper = new PreferenceHelper(this);
        Button btn = findViewById(R.id.child);


        //?????? ?????? ??????
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "?????? ?????? ??????");
                registerChild();
                intent2 = new Intent(getApplicationContext(), MainParent.class);
                intent2.putExtra("childadd", 1);
                startActivity(intent2);
            }
        });
    }

    private void registerChild() {

        String name1 = name.getText().toString();
        String age1 = age.getText().toString();

        retrofitClient = RetrofitClient.getInstance();
        RetrofitAPI = RetrofitClient.getRetrofitInterface();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RegisterInterface.REGIST_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RegisterInterface api = retrofit.create(RegisterInterface.class);
        Call<String> call = api.setChildData(pkid, name1, age1, img);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull retrofit2.Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String jsonResponse = response.body();
                    createAccountItem.C_name = name1;
                    createAccountItem.C_age = age1;

                    Log.e(TAG, "?????? ??????");
                    Toast.makeText(ChildAddActivity.this, "?????? ?????? ??????", Toast.LENGTH_SHORT).show();
                    try {
                        parseRegData(jsonResponse);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.e(TAG, "?????? = " + t.getMessage());
            }
        });
    }

    private void parseRegData(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.optString("status").equals("true")) {
            saveInfo(response);
        } else {
        }
    }

    private void saveInfo(String response) {
        preferenceHelper.putIsLogin(true);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {
                JSONArray dataArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    preferenceHelper.putName(dataobj.getString("name"));
                    preferenceHelper.putHobby(dataobj.getString("hobby"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //????????? ?????? ??????
    public void opengallery(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.addFlags(intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, GET_GALLERY_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();

            String uri = selectedImageUri.toString();

            img = uri; //img????????? uri ??????????????? ????????? ??????
            createAccountItem.C_img=uri;
            System.out.println("==========================="+createAccountItem.C_img);
            Picasso.get().load(createAccountItem.C_img).into(imageview);
            // imageview.setImageURI(selectedImageUri);

            try {
            } catch (Exception e) {

            }
        }
    }
    public void setPreference(String key, String value) {
        SharedPreferences pref = getSharedPreferences("CHILD", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
        editor.commit();
    }
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


}
/*
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
        //????????? ???????????? ??????
    }
}
*/