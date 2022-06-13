package com.capstone.everykid.Model;

public class MessageItem {

    String name;
    String chatid;
    String message;
    String time;
    public static String pofileUrl;

    public MessageItem(String name, String chatid, String message, String time, String pofileUrl) {
        this.name = name;
        this.chatid = chatid;
        this.message = message;
        this.time = time;
        this.pofileUrl = pofileUrl;
    }

    //firebase DB에 객체로 값을 읽어올 때
    //파라미터가 비어있는 생성자가 필요함.
    public MessageItem() {
    }

    //Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChatId() {return chatid;}

    public void setChatId(String chatid){this.chatid = chatid;}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPofileUrl() {
        return pofileUrl;
    }

    public void setPofileUrl(String pofileUrl) {
        this.pofileUrl = pofileUrl;
    }
}