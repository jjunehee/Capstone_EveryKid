package com.capstone.everykid.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.capstone.everykid.Model.ListItem;
import com.capstone.everykid.R;
import com.capstone.everykid.View.Activity.LoadActivity;

import java.util.ArrayList;

public class ListItemAdapter extends BaseAdapter{
   ArrayList<ListItem> items = new ArrayList<ListItem>();
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
       ListItem listItem=items.get(position);

       //listview item을 inflate해서 convertview를 참조
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.listview_item,parent,false);
        }
        TextView Date =  convertView.findViewById(R.id.date);
        Date.setText(listItem.getDate());

//        등원버튼
        Button btn1 =(Button)convertView.findViewById(R.id.btn_attend);
                btn1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(context, LoadActivity.class);
                intent.putExtra("name", "Junhee");
                intent.putExtra("date", listItem.getDate());
                intent.putExtra("time","등원");
                context.startActivity(intent);
            }
        });
//         하원버튼
        Button btn2 =(Button)convertView.findViewById(R.id.btn_exit);
        btn2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(context, LoadActivity.class);
                intent.putExtra("name", "Junhee");
                intent.putExtra("date", listItem.getDate());
                intent.putExtra("time","하원");
                context.startActivity(intent);
            }
        });


        return convertView;
    }

    public void addItem(ListItem item){
        items.add(item);
    }
}
