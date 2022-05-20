package com.capstone.everykid.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.capstone.everykid.Model.BoardList;
import com.capstone.everykid.R;
import com.capstone.everykid.RetrofitAPI.RetrofitAPI;
import com.capstone.everykid.View.Adapter.CommunityFragmentAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lakue.pagingbutton.LakuePagingButton;
import com.lakue.pagingbutton.OnPageSelectListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommunityFragment extends Fragment {

    public static BoardList boardList;
    private View view;
    private ListView listView;
    private CommunityFragmentAdapter adapter;
    private FloatingActionButton btn;
    private LakuePagingButton lpb_buttonlist;
    private Call<BoardList> call;
    private EditText search;
    RetrofitAPI retrofitAPI;
    public static final int PAGE_ITEM_COUNT = 5;

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

        retrofitAPI = retrofit.create(RetrofitAPI.class);

        getBoardList(0, 1, ""); //서버에 페이지를 요청할 때 -1, 초기값은 0
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_community, container, false);
        listView = view.findViewById(R.id.cmListView);
        adapter = new CommunityFragmentAdapter();
        listView.setAdapter(adapter);

        lpb_buttonlist = (LakuePagingButton) view.findViewById(R.id.lpb_buttonlist);
        btn = (FloatingActionButton) view.findViewById(R.id.btn_reg);
<<<<<<< HEAD

=======
>>>>>>> 1c4213b8168f398d98c6ca11fd31b985800d7d97

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        search = (EditText) view.findViewById(R.id.searchBoard);
        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER) {
                    getBoardList(0, 2, search.getText().toString());
                    return true;
                }

                return false;
            }
        });

        return view;
    }

    public void getBoardList(int page, int whichMethod, String key) { //key는 searchBoard에서 사용
        if(whichMethod == 1)
            call = retrofitAPI.listBoard("1", page); //kID 사용자의 kID로 바꿔주기
        else if(whichMethod == 2)
            call = retrofitAPI.searchBoard("1", key, page);


        call.enqueue(new Callback<BoardList>() {
            @Override
            public void onResponse(Call<BoardList> call, Response<BoardList> response) {
                if(response.isSuccessful()) {
                    //통신이 성공된 경우
                    BoardList result = response.body();
                    boardList = result;
                    adapter.removeItem();
                    for(int i=0; i<boardList.getBoardList().size(); i++) {
                        adapter.addItem(boardList.getBoardList().get(i));
                        System.out.println(boardList.getBoardList().get(i));
                    }
                    listView.setAdapter(adapter);
                    listView.invalidate();

                    //Number of buttons displayed at one time (Default : 5)
                    lpb_buttonlist.setPageItemCount(PAGE_ITEM_COUNT);
                    //총 페이지 버튼 수와 현재 페이지 설정
                    if(page == 0)
                        lpb_buttonlist.addBottomPageButton(boardList.getTotalPage(), 1);

                    //lpb_buttonlist.
                    lpb_buttonlist.setOnPageSelectListener(new OnPageSelectListener() {
                        //PrevButton Click
                        @Override
                        public void onPageBefore(int now_page) {
                            //prev 버튼을 클릭하면 버튼이 재설정되고 버튼이 그려집니다.
                            lpb_buttonlist.addBottomPageButton(boardList.getTotalPage(), now_page);
                            getBoardList(now_page - 1, whichMethod, key);
                            //해당 페이지에 대한 소스 코드 작성
                        }

                        @Override
                        public void onPageCenter(int now_page) {
                            //Write source code for there page
                            getBoardList(now_page - 1, whichMethod, key); //0페이지가 첫 페이지이므로 -1
                        }

                        //NextButton Click
                        @Override
                        public void onPageNext(int now_page) {
                            //next 버튼을 클릭하면 버튼이 재설정되고 버튼이 그려집니다.
                            lpb_buttonlist.addBottomPageButton(boardList.getTotalPage(), now_page);
                            getBoardList(now_page - 1, whichMethod, key);
                            //해당 페이지에 대한 소스 코드 작성
                        }
                    });

                    System.out.println("통신 완료");
                    //data 에서 필요한 내용 꺼내 쓰기
                } else {
                    //통신이 실패한 경우
                }
            }

            @Override
            public void onFailure(Call<BoardList> call, Throwable t) {
                //통신 실패 (인터넷 끊김, 예외 발생 등 시스템적인 이유)
                t.printStackTrace();
            }
        });

        return;
    }
}