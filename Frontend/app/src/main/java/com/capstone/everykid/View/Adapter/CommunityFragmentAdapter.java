package com.capstone.everykid.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.capstone.everykid.Model.Board;
import com.capstone.everykid.Model.BoardList;
import com.capstone.everykid.R;
import com.capstone.everykid.RetrofitAPI.RetrofitAPI;
import com.capstone.everykid.View.Activity.CommunityFragment;
import com.capstone.everykid.View.Activity.MainActivity;
import com.capstone.everykid.View.Activity.PostActivity;
import com.lakue.pagingbutton.OnPageSelectListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommunityFragmentAdapter extends BaseAdapter {

    ArrayList<Board> items = new ArrayList<>();
    Context context;
    RetrofitAPI retrofitAPI;
    Call call;

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context = parent.getContext();
        Board board=items.get(position);

        //listview item을 inflate해서 convertview를 참조
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.community_item,parent,false);

        }
        //화면에 보여질 데이터 참조
        TextView titleView =  convertView.findViewById(R.id.title_cm);
        TextView idView =  convertView.findViewById(R.id.id_cm);
        TextView dateView =  convertView.findViewById(R.id.date_cm);
        TextView HITSView = convertView.findViewById(R.id.HITS_cm);

        //데이터를 set해줌
        titleView.setText(" "+board.getWRITE_SUBJECT());
        String id = (board.getP_ID() == null) ? board.getT_ID() : board.getP_ID();
        idView.setText(" "+id);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateView.setText(format.format(board.getWRITE_DATE()));
        HITSView.setText("조회수: " + board.getHITS());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:8080/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                retrofitAPI = retrofit.create(RetrofitAPI.class);
                call = retrofitAPI.selectBoard(board.getBKID());

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()) {
                            //통신이 성공된 경우
                            System.out.println("통신 완료");
                            //data 에서 필요한 내용 꺼내 쓰기
                        } else {
                            System.out.println("통신 실패");
                            //통신이 실패한 경우
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        //통신 실패 (인터넷 끊김, 예외 발생 등 시스템적인 이유)
                        System.out.println("통신 실패");
                        t.printStackTrace();
                    }
                });

                Intent intent = new Intent(context, PostActivity.class);
                intent.putExtra("board", board);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    public void addItem(Board item){
        items.add(item);
    }

    public void removeItem() { items = new ArrayList<>(); }

}
