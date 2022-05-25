package com.capstone.everykid.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.capstone.everykid.Model.RecyclerItem;
import com.capstone.everykid.RetrofitAPI.OnItemClickListener;
import com.capstone.everykid.R;

import java.util.ArrayList;

public class RecyclerImageTextAdapter extends RecyclerView.Adapter<RecyclerImageTextAdapter.ViewHolder>  {
//    implements OnItemClickListener
    private ArrayList<RecyclerItem> mData = null ;
    OnItemClickListener listener;
    // 생성자에서 데이터 리스트 객체를 전달받음.
    public RecyclerImageTextAdapter(ArrayList<RecyclerItem> list) {
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public RecyclerImageTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.notice_item, parent, false) ;
        RecyclerImageTextAdapter.ViewHolder vh = new RecyclerImageTextAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(RecyclerImageTextAdapter.ViewHolder holder, int position) {

        RecyclerItem item = mData.get(position) ;
//
//        holder.icon.setImageDrawable(item.getIcon()) ;
//        holder.title.setText(item.getTitle()) ;
        holder.noticetitle.setText(item.getNotice_title()) ;
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }
    public void setOnItemClicklistener(OnItemClickListener listener){
        this.listener = listener;
    }


//    @Override
//    public void onItemClick(RecyclerImageTextAdapter.ViewHolder holder, View view, int position) {
//        if(listener != null){
//            listener.onItemClick(holder, view, position);
//        }
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView icon ;
        TextView noticetitle ;

        ViewHolder(View itemView) {
            super(itemView) ;
            // 뷰 객체에 대한 참조. (hold strong reference)
//            icon = itemView.findViewById(R.id.kid_profile) ;
//            icon.setClipToOutline(true);
//            title = itemView.findViewById(R.id.kid_name) ;

            noticetitle = itemView.findViewById(R.id.notice_title) ;

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int position = getAdapterPosition();
                    if(listener!=null){
                        //listener.onItemClick(RecyclerImageTextAdapter.ViewHolder.this, v, position);
                    }
                }
            });
        }
    }
}