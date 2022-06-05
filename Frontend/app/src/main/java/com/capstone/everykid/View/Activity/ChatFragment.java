package com.capstone.everykid.View.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.capstone.everykid.Model.CreateAccountItem;
import com.capstone.everykid.R;

public class ChatFragment extends Fragment {
    private View view;
    private Button gochat_btn;
    private ImageView img;
    CreateAccountItem createAccountItem;

    public ChatFragment() {
        // Required empty public constructor
    }
    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat, container, false);


        gochat_btn = (Button) view.findViewById(R.id.btn_gochat);
        if(createAccountItem.User.equals("t")){
            Intent intent = new Intent(getActivity(), ChatActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
        //프로필 사진 배경에 맞게 자르기
        img = (ImageView) view.findViewById(R.id.chat_profile); //프로필 사진
        img.setClipToOutline(true);

        gochat_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        return view;
    }
}