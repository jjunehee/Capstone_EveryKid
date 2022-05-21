package com.capstone.everykid.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.everykid.Model.BoardList;
import com.capstone.everykid.Model.ItemClass;
import com.capstone.everykid.Model.Kindergarten;
import com.capstone.everykid.Model.regionCode;
import com.capstone.everykid.R;
import com.capstone.everykid.RetrofitAPI.RetrofitAPI;
import com.capstone.everykid.View.Adapter.ExpandableListAdapter;
import com.lakue.pagingbutton.OnPageSelectListener;
import com.tickaroo.tikxml.TikXml;
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class findKindergarten extends AppCompatActivity {
    private RecyclerView recyclerview;
    private Call<List<regionCode>> call;
    private Call<String> callKey;
    RetrofitAPI retrofitAPI;
    List<regionCode> codeList;
    Button searchButton;
    private int dataCount = 0;
    private ExpandableListAdapter adapter;
    private String key;
    List<ItemClass> kindergartenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitAPI = retrofit.create(RetrofitAPI.class);

        callKey = retrofitAPI.getKey();
        callKey.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()) {
                    //통신이 성공된 경우
                    String result = response.body();
                    System.out.println(response.headers());
                    System.out.println(response.errorBody());
                    key = result;

                    System.out.println("통신 완료");
                    //data 에서 필요한 내용 꺼내 쓰기
                } else {
                    //통신이 실패한 경우
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //통신 실패 (인터넷 끊김, 예외 발생 등 시스템적인 이유)
                t.printStackTrace();
            }
        });

        setContentView(R.layout.activity_findkindergarten);
        recyclerview = findViewById(R.id.fk_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        getAllRegionCode();

        searchButton = findViewById(R.id.searchRegionCode);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String siGunGuCode = "";
                String siDo = adapter.getRegionName().get(0);
                String siGunGu = adapter.getRegionName().get(1);
                System.out.println(siDo + " " + siGunGu);
                for(int i = 0; i < codeList.size(); i++) {
                    if(codeList.get(i).getSiDoName().equals(siDo) && codeList.get(i).getSiGunGuName().equals(siGunGu)) {
                        siGunGuCode = codeList.get(i).getSiGunGuCode();
                    }
                }
                System.out.println(siGunGuCode);

                TikXml tikXml = new TikXml.Builder()
                        .exceptionOnUnreadXml(false)
                        .build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://api.childcare.go.kr/")
                        .addConverterFactory(TikXmlConverterFactory.create(tikXml))
                        .build();
                retrofitAPI = retrofit.create(RetrofitAPI.class);

                System.out.println(key + " " + siGunGuCode);
                retrofitAPI.getKindergartenInfo(key,siGunGuCode).enqueue(new Callback<Kindergarten>() {
                    @Override
                    public void onResponse(Call<Kindergarten> call, Response<Kindergarten> response) {
                        System.out.println(call.request());
                        System.out.println("받아오기 완료");
                        kindergartenList = response.body().getItems();
                        Intent intent = getIntent();
                        Intent intent1 = new Intent(getApplicationContext(), selectKindergarten.class);
                        ArrayList<ItemClass> list = new ArrayList<ItemClass>();
                        list.addAll(kindergartenList);
                        intent1.putExtra("list", list);
                        intent1.putExtra("User", intent.getExtras().getString("User"));
//                        System.out.println(intent.getExtras().getString("User"));
//                        System.out.println(intent.getExtras().getString("etid"));
//                        System.out.println(intent.getExtras().getString("etpassword"));
                        intent1.putExtra("etid", intent.getExtras().getString("etid"));
                        intent1.putExtra("etphone", intent.getExtras().getString("etphone"));
                        intent1.putExtra("etusername", intent.getExtras().getString("etusername"));
                        intent1.putExtra("etpassword", intent.getExtras().getString("etpassword"));
                        intent1.putExtra("etemail", intent.getExtras().getString("etemail"));
                        intent1.putExtra("ealias", intent.getExtras().getString("ealias"));
                        intent1.putExtra("ekindergarten", intent.getExtras().getString("ekindergarten"));
                        startActivity(intent1);
                    }

                    @Override
                    public void onFailure(Call<Kindergarten> call, Throwable t) {
                        System.out.println("받아오기 실패");
                        Log.e("Response fail", t.getMessage());
                    }
                });
            }
        });
    }

    public void getAllRegionCode() {
        call = retrofitAPI.getAllRegionCode();

        call.enqueue(new Callback<List<regionCode>>() {
            @Override
            public void onResponse(Call<List<regionCode>> call, Response<List<regionCode>> response) {
                if(response.isSuccessful()) {
                    //통신이 성공된 경우
                    List<regionCode> result = response.body();
                    setDate(result);

                    System.out.println("통신 완료");
                    //data 에서 필요한 내용 꺼내 쓰기
                } else {
                    //통신이 실패한 경우
                }
            }

            @Override
            public void onFailure(Call<List<regionCode>> call, Throwable t) {
                //통신 실패 (인터넷 끊김, 예외 발생 등 시스템적인 이유)
                t.printStackTrace();
            }
        });

        return;
    }

    public void setDate(List<regionCode> result) {
        ArrayList<String> siDoList = new ArrayList<>();
        codeList = result;

        List<ExpandableListAdapter.Item> data = new ArrayList<>();
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "시도명"));
        for(int i = 0; i < codeList.size(); i++) {
            if(!siDoList.contains(codeList.get(i).getSiDoName())) {
                siDoList.add(codeList.get(i).getSiDoName());
                data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, codeList.get(i).getSiDoName()));
            }
        }
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "시군구명"));
        adapter = new ExpandableListAdapter(data, codeList, recyclerview);
        recyclerview.setAdapter(adapter);
        recyclerview.invalidate();
    }
}
