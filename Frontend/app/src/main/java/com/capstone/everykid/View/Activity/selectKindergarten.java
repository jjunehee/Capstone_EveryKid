package com.capstone.everykid.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.capstone.everykid.Model.BoardList;
import com.capstone.everykid.Model.ItemClass;
import com.capstone.everykid.R;
import com.capstone.everykid.RetrofitAPI.RetrofitAPI;
import com.capstone.everykid.View.Adapter.CommunityFragmentAdapter;
import com.capstone.everykid.View.Adapter.selectKindergartenAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lakue.pagingbutton.LakuePagingButton;
import com.lakue.pagingbutton.OnPageSelectListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class selectKindergarten extends AppCompatActivity {

    private ListView listView;
    private selectKindergartenAdapter adapter;
    private ArrayList<ItemClass> kgList;
    public static Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        setContentView(R.layout.activity_selectkindergarten);
        listView = findViewById(R.id.kg_list);
        adapter = new selectKindergartenAdapter();
        listView.setAdapter(adapter);

        intent = getIntent();
        kgList = (ArrayList<ItemClass>) intent.getSerializableExtra("list");

        getKindergartenList();
    }

    public void getKindergartenList() {
        for(int i = 0; i < kgList.size(); i++) {
            adapter.addItem(kgList.get(i));
            System.out.println(kgList.get(i));
        }
        listView.invalidate();
    }
}