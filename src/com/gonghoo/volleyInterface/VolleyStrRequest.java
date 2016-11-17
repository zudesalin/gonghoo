package com.gonghoo.volleyInterface;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.gonghoo.Application.GhApplication;

import java.util.Map;

/**
 * Created by zudesalin on 2016/8/8.
 */
public class VolleyStrRequest {
    private static RequestQueue requestQueue=GhApplication.getRequestQueue();
    public static void strGet(Context context, String url,String tag,VolleyInterfaceStr volleyInterface){
        requestQueue.cancelAll(tag);
        StringRequest stringRequest=new StringRequest(Request.Method.GET,url,volleyInterface.listenerLoader(),volleyInterface.errorListenerLoader());
        stringRequest.setTag(tag);
        requestQueue.add(stringRequest);
        requestQueue.start();
    }

    public static void strPost(Context context, String url,String tag,VolleyInterface volleyInterface){
        requestQueue.cancelAll(tag);
        StringRequest stringRequest=new StringRequest(Request.Method.GET,url,volleyInterface.listenerLoader(),volleyInterface.errorListenerLoader());
        stringRequest.setTag(tag);
        requestQueue.add(stringRequest);
        requestQueue.start();
    }
}
