package com.capstone.everykid.View.Activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.capstone.everykid.Model.CreateAccountItem;
import com.capstone.everykid.Model.Notice;
import com.capstone.everykid.Model.RecyclerItem;
import com.capstone.everykid.RetrofitAPI.OnItemClickListener;
import com.capstone.everykid.R;
import com.capstone.everykid.RetrofitAPI.RetrofitAPI;
import com.capstone.everykid.View.Adapter.NoticeItemAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {
    private View view;

    public Button noticeWrite_btn;
    RecyclerView mRecyclerView=null;
    NoticeItemAdapter mAdapter=null;
    ArrayList<RecyclerItem> mList= new ArrayList<RecyclerItem>();
    CreateAccountItem createAccountItem;
    RetrofitAPI retrofitAPI;
    Call call;
    List<Notice> noticeList;
    Notice todayNotice = null;


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
        mList= new ArrayList<RecyclerItem>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_home, container, false);
        noticeWrite_btn = (Button)view.findViewById(R.id.noticeWrite_btn); //공지사항 글쓰는 버튼
        if(createAccountItem.User.equals("t")){
            noticeWrite_btn.setVisibility(View.VISIBLE);
        }


        noticeWrite_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notice noticeSelected = getTodayNotice();

                Intent intent = new Intent(getActivity(), NoticeWriteActivity.class);
                try {
                    intent.putExtra("subject", noticeSelected.getWriteSubject());
                    intent.putExtra("contents", noticeSelected.getContents());
                } catch(NullPointerException e) {
                    
                }
                startActivity(intent);
            }
        });
//        달력
        List<EventDay> events = new ArrayList<>(); //event(emoji)목록
        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = clickedDayCalendar.getTime();
                String dateToStr = dateFormat.format(date);

                Notice noticeSelected = null;
                if(noticeList != null) {
                    for(int i = 0; i < noticeList.size(); i++) {
                        if(dateFormat.format(noticeList.get(i).getWriteDate()).equals(dateToStr)) {
                            System.out.println(dateToStr);
                            System.out.println(dateFormat.format(noticeList.get(i).getWriteDate()));
                            noticeSelected = noticeList.get(i);
                            break;
                        }
                    }
                }

                String context = null;
                String subject = null;
                try {
                    context = noticeSelected.getContents();//그 날짜의 일정 가져와야함
                    subject = noticeSelected.getWriteSubject();
                } catch(NullPointerException e) {
                    return;
                }

                Log.d("CREATION", dateToStr);
                Intent intent = new Intent(view.getContext(), ScheduleActivity.class); //스케쥴 보여주는 창

                //날짜와 그날의 학사일정 값 넘겨주기
                intent.putExtra("date", dateToStr);
                intent.putExtra("subject", subject);
                intent.putExtra("context", context);

                view.getContext().startActivity(intent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitAPI = retrofit.create(RetrofitAPI.class);

        call = retrofitAPI.getNoticeList(CreateAccountItem.K_kid);
        call.enqueue(new Callback<List<Notice>>() {
            @Override
            public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                if(response.isSuccessful()) {
                    //통신이 성공된 경우
                    List<Notice> result = response.body();
                    noticeList = result;
                    SimpleDateFormat dtFormat;
                    String formatDate;
                    for(int i = 0; i < noticeList.size(); i++) {
                        dtFormat = new SimpleDateFormat("yyyy-MM-dd");
                        formatDate = dtFormat.format(noticeList.get(i).getWriteDate());
                        String year = formatDate.substring(0, 4);
                        String month = formatDate.substring(5, 7);
                        String day = formatDate.substring(8, 10);

                        Calendar calendar = Calendar.getInstance();
                        events.add(new EventDay(calendar, R.drawable.conversation));
                        calendar.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day)); //month는 1빼야함
                        calendarView.setEvents(events);
                    }
                    for(int i=0; i<result.size(); i++) {
                        System.out.println(result.get(i).getContents());
                    }

                    if(mList.size() > 0)
                        mList.remove(0);
                    try {
                        todayNotice = getTodayNotice();
                        addItem(todayNotice.getWriteSubject()); //리사이클러뷰어댑터에 아이템 임시 추가
                    } catch(NullPointerException e) {
                        addItem("오늘의 알림장이 없습니다."); //리사이클러뷰어댑터에 아이템 임시 추가
                    }
                    mAdapter.notifyDataSetChanged();
                    calendarView.invalidate();
                    System.out.println("통신 완료");
                    //data 에서 필요한 내용 꺼내 쓰기
                } else {
                    //통신이 실패한 경우
                }
            }

            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {
                //통신 실패 (인터넷 끊김, 예외 발생 등 시스템적인 이유)
                t.printStackTrace();
            }
        });



//        공지사항 리사이클러뷰
        mRecyclerView = view.findViewById(R.id.notice_recycler);
        mAdapter = new NoticeItemAdapter(mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        addItem("");
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClicklistener(new OnItemClickListener() {
            @Override
            public void onItemClick(NoticeItemAdapter.ViewHolder holder, View view, int position) {
                //RecyclerItem item = mAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), NoticeActivity.class);
                if(todayNotice != null) {
                    intent.putExtra("subject", todayNotice.getWriteSubject());
                    intent.putExtra("contents", todayNotice.getContents());
                } else {
                    intent.putExtra("subject", getTodayNotice().getWriteSubject());
                    intent.putExtra("contents", getTodayNotice().getContents());
                }
                startActivity(intent);
            }
        });
        mAdapter.notifyDataSetChanged();
        return view;
    }
    public void addItem(String title){
        RecyclerItem item = new RecyclerItem();
        item.setNotice_title(title);
        mList.add(item);
    }

    public Notice getTodayNotice() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String dateToStr = dateFormat.format(date);
        Notice noticeSelected = null;
        if(noticeList != null) {
            for(int i = 0; i < noticeList.size(); i++) {
                if(dateFormat.format(noticeList.get(i).getWriteDate()).equals(dateToStr)) {
                    System.out.println(dateToStr);
                    System.out.println(dateFormat.format(noticeList.get(i).getWriteDate()));
                    noticeSelected = noticeList.get(i);
                    break;
                }
            }
        }
        return noticeSelected;
    }
}

