package com.capstone.everykid;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    final static private String URL = "http://";
    private Map<String, String> parameters;


    public RegisterRequest(String userID, String userPassword, String userName, String userEmail,int userPhone,String Nickname, String K_ID, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userName",userName);
        parameters.put("userEmail",userEmail);
        parameters.put("userPhonenumber",userPhone+"");
        parameters.put("Nickname",Nickname);
        parameters.put("kindergartenID",K_ID);




    }
    @Override
    public Map<String, String> getParams(){
        return parameters;

    }

}
