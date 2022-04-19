package com.capstone.everykid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListItemAdapter extends BaseAdapter {
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
        //화면에 보여질 데이터 참조
        TextView Date =  convertView.findViewById(R.id.date);

        //데이터를 set해줌
        Date.setText(listItem.getDate());

        return convertView;
    }

    public void addItem(ListItem item){
        items.add(item);
    }
}
