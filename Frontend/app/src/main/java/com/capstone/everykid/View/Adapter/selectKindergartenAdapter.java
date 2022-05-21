package com.capstone.everykid.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.capstone.everykid.Model.ItemClass;
import com.capstone.everykid.R;
import com.capstone.everykid.RetrofitAPI.RetrofitAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class selectKindergartenAdapter extends BaseAdapter {

    Call call;
    RetrofitAPI retrofitAPI;
    ArrayList<ItemClass> items = new ArrayList<>();
    Context context;

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
        ItemClass item = items.get(position);

        //listview item을 inflate해서 convertview를 참조
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.selectkindergarten_item,parent,false);
        }
        //화면에 보여질 데이터 참조
        TextView title =  convertView.findViewById(R.id.title_cm);
        TextView address = convertView.findViewById(R.id.address_kg);

        //데이터를 set해줌
        title.setText(item.getCrname());
        address.setText(item.getCraddr());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(title.getText().toString());
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:8080/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                retrofitAPI = retrofit.create(RetrofitAPI.class);
                ItemClass item = (ItemClass) getItem(position);

                call = retrofitAPI.selectKindergarten(item.getStcode(), item.getCrtel(), item.getCraddr(), item.getCrname());
                call.enqueue(new Callback<Long>() {
                    @Override
                    public void onResponse(Call<Long> call, Response<Long> response) {
                        if(response.isSuccessful()) {
                            //통신이 성공된 경우
                            Long result = response.body();
                            System.out.println(result);
                            System.out.println("통신 완료");
                            //data 에서 필요한 내용 꺼내 쓰기
                        } else {
                            //통신이 실패한 경우
                        }
                    }

                    @Override
                    public void onFailure(Call<Long> call, Throwable t) {
                        //통신 실패 (인터넷 끊김, 예외 발생 등 시스템적인 이유)
                        t.printStackTrace();
                    }
                });
            }
        });

        return convertView;
    }

    public void addItem(ItemClass item){
        items.add(item);
    }

    public void removeItem() { items = new ArrayList<>(); }

}
