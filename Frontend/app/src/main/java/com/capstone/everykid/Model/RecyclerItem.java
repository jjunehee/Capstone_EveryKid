package com.capstone.everykid.Model;

import android.graphics.drawable.Drawable;

public class RecyclerItem {
    private Drawable iconDrawable ;
    private String titleStr ;
    private String notice_title;


    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setNotice_title(String title){ notice_title=title;}

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getNotice_title(){ return this.notice_title;}

}