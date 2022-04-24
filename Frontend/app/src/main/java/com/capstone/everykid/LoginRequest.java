package com.capstone.everykid;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    final static private String URL ="http/";
    private Map<String, String> map;

    public LoginRequest (String userID,String userPW, Response.Listener<String>listener ){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID",userID);
        map.put("userPassword",userPW);

    }
    @Override
    protected Map<String,String> getParams()throws AuthFailureError {
        return map;
    }
}
