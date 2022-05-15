package com.capstone.everykid.View.Activity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.applandeo.materialcalendarview.CalendarView;
import com.capstone.everykid.Model.RecyclerItem;
import com.capstone.everykid.R;
import com.capstone.everykid.View.Adapter.NoticeItemAdapter;


public class HomeFragment extends Fragment {
    private View view;
    public String fname = null;
    public String str = null;

    public Button cha_Btn, del_Btn, save_Btn;
    public TextView diaryTextView, textView2, textView3;
    public EditText contextEditText;
    RecyclerView mRecyclerView=null;
    NoticeItemAdapter mAdapter=null;
    ArrayList<RecyclerItem> mList= new ArrayList<RecyclerItem>();

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_home, container, false);
        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView); //달력 전체


//공지사항 리사이클러뷰
        mRecyclerView = view.findViewById(R.id.recycler_notice);
        mAdapter = new NoticeItemAdapter(mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        //리사이클러뷰어댑터에 임시 추가
        addItem("공지사항 제목");
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        return view;
    }
    public void addItem(String title){
        RecyclerItem item = new RecyclerItem();
        item.setNotice_title(title);
        mList.add(item);
    }
}

