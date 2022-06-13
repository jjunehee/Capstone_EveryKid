package com.capstone.everykid.View.Activity;

import com.google.gson.annotations.SerializedName;

public class ChildData {

    // @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함
    @SerializedName("cname")
    public String name;

    @SerializedName("cage")
    public String age;


    // toString()을 Override 해주지 않으면 객체 주소값을 출력함
    @Override
    public String toString() {
        return "ChildResult{" +
                "name=" + name +
                ", age=" + age + '\'' +
                '}';
    }
}