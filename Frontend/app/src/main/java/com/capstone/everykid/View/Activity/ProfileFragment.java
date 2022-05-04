package com.capstone.everykid.View.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.capstone.everykid.Model.RecyclerItem;
import com.capstone.everykid.R;
import com.capstone.everykid.View.Adapter.RecyclerImageTextAdapter;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    RecyclerView mRecyclerView=null;
    RecyclerImageTextAdapter mAdapter=null;
    ArrayList<RecyclerItem>mList= new ArrayList<RecyclerItem>();

    TextView p_name;
    ImageView p_img,kinder_img;
    Button info_btn;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

//        ActionBar bar =getActivity().getActionBar();
//        bar.setTitle("프로필");

        p_img=(ImageView)view.findViewById(R.id.profile_img); //로그인유저의 프로필사진
        p_name=(TextView)view.findViewById(R.id.profile_name); //로그인 유저의 이름름
        kinder_img=view.findViewById(R.id.kinder_profile);
        info_btn=view.findViewById(R.id.kinder_btn);
        info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                startActivity(intent);
            }
        });
        //프로필 사진 배경에 맞게 자르기
        p_img.setClipToOutline(true);
        kinder_img.setClipToOutline(true);
        // 부모님일 경우 자녀 정보 리싸이클러뷰
        mRecyclerView = view.findViewById(R.id.recycler1);
        mAdapter = new RecyclerImageTextAdapter(mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        //자녀들 리사이클러뷰어댑터에 임시 추가
        addItem(getResources().getDrawable(R.drawable.kidprofile), "김말이(7세)");
        addItem(getResources().getDrawable(R.drawable.kidprofile), "김밥(2세)");
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        return view;
    }
    public void addItem(Drawable icon, String title){
        RecyclerItem item = new RecyclerItem();

        item.setIcon(icon);
        item.setTitle(title);
        mList.add(item);
    }

}