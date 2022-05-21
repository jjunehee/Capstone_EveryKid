package com.capstone.everykid.View.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.capstone.everykid.PicActivity;
import com.capstone.everykid.R;

import com.capstone.everykid.Model.ListItem;
import com.capstone.everykid.View.Adapter.ListItemAdapter;

import java.io.ByteArrayOutputStream;

public class ListFragment extends Fragment {
    private View view;
    ListView listView;
    ListItemAdapter adapter;
    Button notiBtn;



    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
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
        view = inflater.inflate(R.layout.fragment_list, container, false);
        listView=view.findViewById(R.id.list);
        adapter = new ListItemAdapter();
        adapter.addItem(new ListItem("2022-05-18")); //임시
        adapter.addItem(new ListItem("2022-05-19"));
        adapter.addItem(new ListItem("2022-05-20"));
        listView.setAdapter(adapter);

        notiBtn = (Button) view.findViewById(R.id.notificationSetting);

        notiBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NotificationSetting.class);
                startActivity(intent);
            }
        });

        return view;
    }

}