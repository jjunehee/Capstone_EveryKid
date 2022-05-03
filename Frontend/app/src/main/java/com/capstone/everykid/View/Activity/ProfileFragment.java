package com.capstone.everykid.View.Activity;

import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.capstone.everykid.Model.RecyclerItem;
import com.capstone.everykid.R;
import com.capstone.everykid.View.Adapter.RecyclerImageTextAdapter;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    ImageView img;

    RecyclerView mRecyclerView=null;
    RecyclerImageTextAdapter mAdapter=null;
    ArrayList<RecyclerItem>mList= new ArrayList<RecyclerItem>();


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

        //프로필 사진 배경에 맞게 자르기
        img = (ImageView) view.findViewById(R.id.iv_profile); //프로필 사진
        img.setClipToOutline(true);

        mRecyclerView = view.findViewById(R.id.recycler1);
        mAdapter = new RecyclerImageTextAdapter(mList);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        addItem(getResources().getDrawable(R.drawable.kidprofile), "김말이(7세)");
        addItem(getResources().getDrawable(R.drawable.kidprofile), "김밥(2세)");


        mRecyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
        //        Button btn =view.findViewById(R.id.btn_setting);
//        btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//
//            }
//        });
        return view;
    }
    public void addItem(Drawable icon, String title){
        RecyclerItem item = new RecyclerItem();

        item.setIcon(icon);
        item.setTitle(title);
        mList.add(item);
    }

}