package com.capstone.everykid.View.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.capstone.everykid.Model.CreateAccountItem;
import com.capstone.everykid.Model.G;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.everykid.Model.LoginResponse;
import com.capstone.everykid.Model.LoginResponseTeacher;
import com.capstone.everykid.Model.MessageItem;
import com.capstone.everykid.Model.RecyclerItem;
import com.capstone.everykid.R;
import com.capstone.everykid.RetrofitAPI.RegisterInterface;
import com.capstone.everykid.RetrofitAPI.RetrofitAPI;
import com.capstone.everykid.View.Adapter.RecyclerImageTextAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {

    private static final int RESULT_OK = -1;

    boolean isChanged= false; //프로필을 변경한 적이 있는가?


    //    RecyclerView mRecyclerView=null;
//    RecyclerImageTextAdapter mAdapter=null;
//    ArrayList<RecyclerItem>mList= new ArrayList<RecyclerItem>();
    TextView p_name, kinder_name, user_status, user_kinder, teacher_name, child_name, child_age;
    View view;
    ImageView p_img, child_img;
    Button info_btn, add_btn, profile_btn, logout_btn, teacher_btn, delete_child_btn;
    CreateAccountItem createAccountItem;
    Context context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    FrameLayout frame, frame2;
    LinearLayout linear, kidprofile;


    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mList= new ArrayList<RecyclerItem>();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String pofileUrl = createAccountItem.porfileUri;
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        if (createAccountItem.Name == null) {
            Toast.makeText(getActivity(), "다시 로그인 해주세요.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }

        p_img = (ImageView) view.findViewById(R.id.profile_img); //로그인유저의 프로필사진
        child_img = (ImageView) view.findViewById(R.id.kidprofile_img);
        p_name = (TextView) view.findViewById(R.id.profile_name); //로그인 유저의 이름
        user_status = (TextView) view.findViewById(R.id.user_txt);
        user_kinder = (TextView) view.findViewById(R.id.user_txt2);
        teacher_name = (TextView) view.findViewById(R.id.teacher_name);
        frame = view.findViewById(R.id.teacherinfo);
        linear = view.findViewById(R.id.teacherinfo2);
        frame2 = view.findViewById(R.id.childinfo);
        kidprofile = view.findViewById(R.id.kidprofile);
        child_name = view.findViewById(R.id.child_name);
        child_age = view.findViewById(R.id.child_age);
        delete_child_btn = view.findViewById(R.id.child_delete);


        kinder_name = (TextView) view.findViewById(R.id.kinder_name);
        p_name.setText(createAccountItem.Name);
        teacher_name.setText(createAccountItem.Tname+" 선생님");

        info_btn = view.findViewById(R.id.kinder_btn);
        profile_btn = view.findViewById(R.id.profileInfo_btn);
        add_btn = view.findViewById(R.id.profile_kidadd_btn);
        logout_btn = view.findViewById(R.id.logout_btn);
        teacher_btn = view.findViewById(R.id.teacher_btn);

        System.out.println("==========================="+createAccountItem.C_name);

        if (createAccountItem.C_name == null) {
            kidprofile.setVisibility(View.GONE);
            add_btn.setVisibility(View.VISIBLE);
        }

        if (createAccountItem.C_name != null) {
            kidprofile.setVisibility(View.VISIBLE);
            add_btn.setVisibility(View.GONE);
            child_name.setText(createAccountItem.C_name);
            child_age.setText(createAccountItem.C_age + "세");
           // Picasso.get().load(createAccountItem.C_img).into(child_img);

//            if(createAccountItem.C_uri!=null){
//                child_img.setImageURI(createAccountItem.C_uri);
//            }else{
//                child_img.setImageResource(R.drawable.default_profile);
//            }

            if(createAccountItem.C_img!=null){
                Uri imageUri = Uri.parse(createAccountItem.C_img);
                //child_img.setImageURI(imageUri);


                //Uri imgUri=Uri.parse("android.resource://my.package.name/"+R.drawable.default_profile);
                //child_img.setImageResource(R.drawable.default_profile);
                //child_img.setImageURI(imgUri);

                Picasso.get().load(createAccountItem.C_img).into(child_img);
//                Glide.with(this)
//                        .load(createAccountItem.C_img)
//                        .into(child_img);

                System.out.println("uri 받아짐");
            }else{
                child_img.setImageResource(R.drawable.default_profile);
            }

//            if (!getPreferenceString("childImage").equals("")){
//                String uri = getPreferenceString("childImage");
//                Uri imageUri = Uri.parse(uri);
//                child_img.setImageURI(imageUri);
//            }else{
//                child_img.setImageResource(R.drawable.default_profile);
//            }


            if(pofileUrl!=null){
                Glide.with(this).load(pofileUrl).into(p_img);
            }else{
                Glide.with(this).load(R.drawable.default_profile).into(p_img);
            }
        //프로필 사진


        }
        if (createAccountItem.User.equals("t")) {
            user_status.setText("선생님");
            user_kinder.setText("근무 유치원");
            frame.setVisibility(View.GONE);
            linear.setVisibility(View.GONE);
            frame2.setVisibility(View.GONE);
            add_btn.setVisibility(View.GONE);
        }
        kinder_name.setText(createAccountItem.K_name);

        //프로필 정보 확인 버튼
        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });



//        유치원 정보 버튼
        info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("info", "kinderinfo");
                startActivity(intent);
            }
        });


//        자녀 추가 버튼
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChildAddActivity.class);
                startActivity(intent);
            }
        });

//        로그아웃 버튼
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                pref = getActivity().getSharedPreferences("DATA_STORE", context.MODE_PRIVATE);
                editor = pref.edit();
                editor.clear();
                editor.commit();

            }
        });
        delete_child_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("삭제").setMessage("정말 삭제하시겠습니까?");

                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://10.0.2.2:8080/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        System.out.println("------------------------" + createAccountItem.P_kid);

                        RegisterInterface retrofitAPI = retrofit.create(RegisterInterface.class);
                        Call<String> call = retrofitAPI.deleteChild(Long.toString(createAccountItem.P_kid));

                        call.enqueue(new Callback<String>() {

                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.isSuccessful() && response.body()!= null) {
                                    //통신이 성공된 경우
                                    String result = response.body();
                                    System.out.println("아이 삭제 통신 완료");
                                    createAccountItem.C_name = null;
                                    createAccountItem.C_age = null;
                                    Intent intent = new Intent(getActivity(), MainParent.class);
                                    intent.putExtra("child", 1);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    Toast.makeText(getActivity(), "아이 삭제 완료", Toast.LENGTH_SHORT).show();
                                } else {
                                    //통신이 실패한 경우
                                    System.out.println("아이 삭제 실패");
                                }
                            }
                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                //통신 실패 (인터넷 끊김, 예외 발생 등 시스템적인 이유)
                                t.printStackTrace();
                                System.out.println("예외발생");
                                System.out.println("아이 삭제 통신 완료");
                                createAccountItem.C_name = null;
                                createAccountItem.C_age = null;
                                Intent intent2 = new Intent(getActivity(), MainParent.class);
                                intent2.putExtra("child", 1);
                                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent2);
                                Toast.makeText(getActivity(), "아이 삭제 완료", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });


        return view;


    }
    public String getPreferenceString(String key) {
        SharedPreferences pref = getActivity().getSharedPreferences("CHILD", context.MODE_PRIVATE);
        return pref.getString(key, "");
    }
}