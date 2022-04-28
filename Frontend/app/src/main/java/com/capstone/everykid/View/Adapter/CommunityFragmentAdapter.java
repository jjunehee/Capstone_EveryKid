package com.capstone.everykid.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.capstone.everykid.Model.Board;
import com.capstone.everykid.R;

import java.util.ArrayList;

public class CommunityFragmentAdapter extends BaseAdapter {

    ArrayList<Board> items = new ArrayList<>();
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
        Board board=items.get(position);

        //listview item을 inflate해서 convertview를 참조
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.community_item,parent,false);

        }
        //화면에 보여질 데이터 참조
        TextView title =  convertView.findViewById(R.id.title_cm);

        //데이터를 set해줌
        title.setText(board.getWRITE_SUBJECT());

        return convertView;
    }

    public void addItem(Board item){
        items.add(item);
    }

}
