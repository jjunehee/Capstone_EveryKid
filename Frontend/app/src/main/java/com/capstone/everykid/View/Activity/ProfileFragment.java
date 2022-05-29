package com.capstone.everykid.View.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.capstone.everykid.Model.CreateAccountItem;
import com.capstone.everykid.Model.G;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.everykid.Model.LoginResponse;
import com.capstone.everykid.Model.LoginResponseTeacher;
import com.capstone.everykid.Model.RecyclerItem;
import com.capstone.everykid.R;
import com.capstone.everykid.View.Adapter.RecyclerImageTextAdapter;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
//    RecyclerView mRecyclerView=null;
//    RecyclerImageTextAdapter mAdapter=null;
//    ArrayList<RecyclerItem>mList= new ArrayList<RecyclerItem>();
      TextView p_name, kinder_name, user_status,user_kinder;
      View view;
      ImageView p_img, child_img, kinder_img;
      Button info_btn, add_btn, profile_btn;
      CreateAccountItem createAccountItem;

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
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        if(createAccountItem.Name==null){
            Toast.makeText(getActivity(),  "다시 로그인 해주세요.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }

        p_img = (ImageView)view.findViewById(R.id.profile_img); //로그인유저의 프로필사진
        child_img=(ImageView)view.findViewById(R.id.kidprofile_img);
        p_name = (TextView)view.findViewById(R.id.profile_name); //로그인 유저의 이름
        user_status=(TextView)view.findViewById(R.id.user_txt);
        user_kinder=(TextView)view.findViewById(R.id.user_txt2);
        if(createAccountItem.User.equals("t")){
            user_status.setText("선생님");
            user_kinder.setText("근무 유치원");
        }
        kinder_name=(TextView)view.findViewById(R.id.kinder_name);
        p_name.setText(createAccountItem.Name);

        info_btn = view.findViewById(R.id.kinder_btn);
        profile_btn = view.findViewById(R.id.profileInfo_btn);
        add_btn = view.findViewById(R.id.profile_kidadd_btn);

        kinder_name.setText(createAccountItem.K_name);

        //프로필 사진 배경에 맞게 자르기
        p_img.setClipToOutline(true);
        child_img.setClipToOutline(true);

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
        return view;
    }

}


        // 부모님일 경우 자녀 정보 리싸이클러뷰
//        mRecyclerView = view.findViewById(R.id.recycler1);
//        mAdapter = new RecyclerImageTextAdapter(mList);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
//        //자녀들 리사이클러뷰어댑터에 임시 추가
//        addItem(getResources().getDrawable(R.drawable.kidprofile), "김말이(7세)");
//        addItem(getResources().getDrawable(R.drawable.kidprofile), "김밥(2세)");
//        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();
//
//    }
//    public void addItem(Drawable icon, String title){
//        RecyclerItem item = new RecyclerItem();
//        item.setIcon(icon);
//        item.setTitle(title);
//        mList.add(item);
//    }
