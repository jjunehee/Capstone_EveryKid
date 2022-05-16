package com.capstone.everykid.Model;

public class FcmUser {

    public String name;
    public String email;
    public String token;

    //비어 있는 생성자가 필요하다.
    //DataSnapshot.getValue(User.class)
    public FcmUser() {

    }

    public FcmUser(String name, String email, String token) {
        this.name = name;
        this.email = email;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }
}
