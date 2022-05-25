package com.capstone.everykid.Model;

import android.app.Application;

public class CreateAccountItem extends Application {

    private String Name;
    private String Phone;
    private String Email;
    //private String K_id;
    private String Id;
    private String Pwd;
    private String Alias;
    private String Kindergarten;


    @Override
    public void onCreate() {
        Name=null;
        Phone = null;
        Email = null;
        //K_id=null;
        Id=null;
        Pwd=null;
        Alias=null;
        Kindergarten=null;
        super.onCreate();
    }

    @Override
    public void onTerminate(){
        super.onTerminate();
    }

    public void setName(String name){  this.Name = name; }
    public void setPhone(String number){ this.Phone = number; }
    public void setEmail(String email){  this.Email = email; }
    //public void setK_id(String k_id){ this.K_id = k_id; }
    public void setId(String id){  this.Id = id; }
    public void setPwd(String pwd){ this.Pwd = pwd; }
    public void setAlias(String alias){this.Alias = alias;}
    public void setKindergarten(String kindergarten){this.Kindergarten = kindergarten;}



    public String getName(){ return Name; }
    public String getPhone(){ return Phone; }
    public String getEmail(){  return Email; }
    //public String getK_id(){ return K_id; }
    public String getId(){  return Id; }
    public String getPwd(){ return Pwd; }
    public String getAlias(){return Alias;}
    public String getKindergarten(){return Kindergarten;}
}
