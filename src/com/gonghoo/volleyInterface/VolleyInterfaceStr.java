package com.gonghoo.volleyInterface;

import android.content.Context;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import org.json.JSONObject;

/**
 * Created by zudesalin on 2016/8/8.
 */
public abstract class VolleyInterfaceStr {
    public static Response.Listener<String> listener;
    public static Response.ErrorListener errorListener;
    public VolleyInterfaceStr(Response.Listener<String> listener, Response.ErrorListener errorListener){
        this.listener=listener;
        this.errorListener=errorListener;
    }
    public abstract void onSuccess(String str);
    public abstract void onError(VolleyError volleyError);
    public Response.Listener listenerLoader(){
        listener=new Response.Listener<String>() {
            @Override
            public void onResponse(String str) {
                onSuccess(str);
            }
        };
        return listener;
    }
    public Response.ErrorListener errorListenerLoader(){
        errorListener=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onError(volleyError);
            }
        };
        return errorListener;
    }
}
