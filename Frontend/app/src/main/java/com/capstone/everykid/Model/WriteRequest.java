package com.capstone.everykid.Model;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WriteRequest extends StringRequest {

    final static private String URL ="";
    private Map<String, String> map;

    public WriteRequest (int b_ID, String date, String userID, String title, String content, int hits, Response.Listener<String>listener ){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID",userID);
        map.put("b_ID",b_ID+"");
        map.put("title",title);
        map.put("content",content);
        map.put("hits",hits+"");
        map.put("WRITE_ DATE", date);

    }
    @Override
    protected Map<String,String> getParams()throws AuthFailureError {
        return map;
    }
}
