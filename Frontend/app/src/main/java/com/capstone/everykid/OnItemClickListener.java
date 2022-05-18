package com.capstone.everykid;

import android.view.View;

import com.capstone.everykid.View.Adapter.NoticeItemAdapter;

public interface OnItemClickListener {
    public void onItemClick(NoticeItemAdapter.ViewHolder holder, View view, int position);

}
