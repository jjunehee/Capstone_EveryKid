package com.capstone.everykid.Model;


import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper
{
    private final String INTRO = "intro";
    private final String NAME = "name";
    private final String HOBBY = "hobby";
    private SharedPreferences app_prefs;
    private Context context;

    public PreferenceHelper(Context context)
    {
        app_prefs = context.getSharedPreferences("shared", 0);
        this.context = context;
    }

    public void putIsLogin(boolean loginOrOut)
    {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(INTRO, loginOrOut);
        edit.apply();
    }

    public void putName(String loginOrOut)
    {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(NAME, loginOrOut);
        edit.apply();
    }

    public String getName()
    {
        return app_prefs.getString(NAME, "");
    }

    public void putHobby(String loginOrOut)
    {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(HOBBY, loginOrOut);
        edit.apply();
    }

    public String getHobby()
    {
        return app_prefs.getString(HOBBY, "");
    }
}