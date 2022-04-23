package com.capstone.everykid.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.capstone.everykid.Model.Board;
import com.capstone.everykid.R;
import com.capstone.everykid.RetrofitAPI.RetrofitAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommunityFragment extends Fragment {

    private ArrayList<Board> boardList = new ArrayList<Board>();
    private View view;
    private ListView listView;
    private CommunityFragmentAdapter adapter;
    private Button btn;

    public CommunityFragment() {
        // Required empty public constructor
    }

    public static CommunityFragment newInstance(String param1, String param2) {
        CommunityFragment fragment = new CommunityFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<List<Board>> call = retrofitAPI.listBoard("1"); //kID 사용자의 kID로 바꿔주기

        call.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {
                if(response.isSuccessful()) {
                    //통신이 성공된 경우
                    List<Board> result = response.body();
                    boardList = (ArrayList<Board>) result;
                    //data 에서 필요한 내용 꺼내 쓰기
                } else {
                    //통신이 실패한 경우
                }
            }

            @Override
            public void onFailure(Call<List<Board>> call, Throwable t) {
                //통신 실패 (인터넷 끊김, 예외 발생 등 시스템적인 이유)
                t.printStackTrace();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

//        while(boardList.size() < 1) {
//            System.out.println(boardList.size());
//        }

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_community, container, false);
        listView=view.findViewById(R.id.cmListView);
        adapter = new CommunityFragmentAdapter();
        System.out.println(boardList.size());
        for(int i=0; i<boardList.size(); i++) {
            adapter.addItem(boardList.get(i));
            System.out.println(boardList.get(i));
        }
        listView.setAdapter(adapter);

        btn = (Button) view.findViewById(R.id.btn_reg);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        return view;

    }
}