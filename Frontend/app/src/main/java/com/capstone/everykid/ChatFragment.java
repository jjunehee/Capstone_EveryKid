package com.capstone.everykid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    EditText etName;
    CircleImageView ivProfile;

    Uri imgUri;//선택한 프로필 이미지 경로 Uri

    boolean isFirst= true; //앱을 처음 실행한 것인가?
    boolean isChanged= false; //프로필을 변경한 적이 있는가?


    public ChatFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chatting_main); //오류 수정 필요

        etName=findViewById(R.id.et_name);
        ivProfile=findViewById(R.id.iv_profile);

        //폰에 저장되어 있는 프로필 읽어오기
        loadData();
        if(G.nickName!=null){
            etName.setText(G.nickName);
            Picasso.get().load(G.porfileUri).into(ivProfile);

            //처음이 아니다, 즉, 이미 접속한 적이 있다.
            isFirst=false;

        }

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    public void clickImage(View view) {
        //프로필 이미지 선택하도록 Gallery 앱 실행
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 10:
                if(resultCode==RESULT_OK){
                    imgUri= data.getData();
                    //Glide.with(this).load(imgUri).into(ivProfile);
                    //Glide는 이미지를 읽어와서 보여줄때 내 device의 외장메모리에 접근하는 퍼미션이 요구됨.
                    //(퍼미션이 없으면 이미지가 보이지 않음.)
                    //Glide를 사용할 때는 동적 퍼미션 필요함.

                    //Picasso 라이브러리는 퍼미션 없어도 됨.
                    Picasso.get().load(imgUri).into(ivProfile);

                    //변경된 이미지가 있다.
                    isChanged=true;
                }
                break;
        }
    }

    public void clickBtn(View view) {

        //바꾼것도 없고, 처음 접속도 아니고..
        if(!isChanged && !isFirst){
            //ChatActivity로 전환
            Intent intent= new Intent(this, ChatActivity.class);
            startActivity(intent);
            finish();
        }else{
            //1. save작업
            saveData();
        }
    }

    void saveData(){
        //EditText의 닉네임 가져오기 [전역변수에]
        G.nickName= etName.getText().toString();

        //이미지를 선택하지 않았을 수도 있으므로
        if(imgUri==null) return;

        //Firebase storage에 이미지 저장하기 위해 파일명 만들기(날짜를 기반으로)
        SimpleDateFormat sdf= new SimpleDateFormat("yyyMMddhhmmss"); //20191024111224
        String fileName= sdf.format(new Date())+".png";

        //Firebase storage에 저장하기
        FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
        final StorageReference imgRef= firebaseStorage.getReference("profileImages/"+fileName);

        //파일 업로드
        UploadTask uploadTask=imgRef.putFile(imgUri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //이미지 업로드가 성공되었으므로...
                //곧바로 firebase storage의 이미지 파일 다운로드 URL을 얻어오기
                imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //파라미터로 firebase의 저장소에 저장되어 있는
                        //이미지에 대한 다운로드 주소(URL)을 문자열로 얻어오기
                        G.porfileUri= uri.toString();
                        Toast.makeText(ChatFragment.this, "프로필 저장 완료", Toast.LENGTH_SHORT).show();

                        //1. Firebase Database에 nickName, profileUrl을 저장
                        //firebase DB관리자 객체 소환
                        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                        //'profiles'라는 이름의 자식 노드 참조 객체 얻어오기
                        DatabaseReference profileRef= firebaseDatabase.getReference("profiles");

                        //닉네임을 key 식별자로 하고 프로필 이미지의 주소를 값으로 저장
                        profileRef.child(G.nickName).setValue(G.porfileUri);

                        //2. 내 phone에 nickName, profileUrl을 저장
                        SharedPreferences preferences= getSharedPreferences("account",MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();

                        editor.putString("nickName",G.nickName);
                        editor.putString("profileUrl", G.porfileUri);

                        editor.commit();
                        //저장이 완료되었으니 ChatActivity로 전환
                        Intent intent=new Intent(MainActivity.this, ChatActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
            }
        });
    }//saveData() ..

    //내 phone에 저장되어 있는 프로필정보 읽어오기
    void loadData(){
        SharedPreferences preferences=getSharedPreferences("account",MODE_PRIVATE);
        G.nickName=preferences.getString("nickName", null);
        G.porfileUri=preferences.getString("profileUrl", null);


    }

}