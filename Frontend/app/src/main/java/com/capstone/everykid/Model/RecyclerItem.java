package com.capstone.everykid.Model;

import android.graphics.drawable.Drawable;

public class RecyclerItem {
    private Drawable iconDrawable ;
    private String titleStr ;


    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }


    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }

}