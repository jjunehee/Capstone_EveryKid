package com.capstone.everykid.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.capstone.everykid.Model.RecyclerItem;
import com.capstone.everykid.RetrofitAPI.OnItemClickListener;
import com.capstone.everykid.R;

import java.util.ArrayList;

public class NoticeItemAdapter extends RecyclerView.Adapter<NoticeItemAdapter.ViewHolder> implements OnItemClickListener {
    private ArrayList<RecyclerItem> mData =null;
    OnItemClickListener listener;
    public NoticeItemAdapter(ArrayList<RecyclerItem> list){ mData=list;}



    @Override
    public NoticeItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View view = inflater.inflate(R.layout.notice_item, parent, false) ;
        NoticeItemAdapter.ViewHolder vh = new NoticeItemAdapter.ViewHolder(view) ;
        return vh ;
    }
    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull NoticeItemAdapter.ViewHolder holder, int position) {
        RecyclerItem item = mData.get(position) ;
        holder.notice_title.setText(item.getNotice_title()) ;
    }
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    public void setOnItemClicklistener(OnItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(NoticeItemAdapter.ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder,view,position);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView notice_title ;

        ViewHolder(View itemView) {
            super(itemView) ;
            notice_title = itemView.findViewById(R.id.notice_title);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int position = getAdapterPosition();
                    if(listener!=null){
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });

        }
    }
}
