package com.gonghoo.volleyInterface;

import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.gonghoo.Application.GhApplication;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by zudesalin on 2016/8/8.
 */
public class VolleyRequest {
    private static RequestQueue requestQueue=GhApplication.getRequestQueue();
    public static void jsonGet(Context context, String url, JSONObject jsonRequest,String tag,VolleyInterface volleyInterface){
        requestQueue.cancelAll(tag);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,jsonRequest,volleyInterface.listenerLoader(),volleyInterface.errorListenerLoader());
        jsonObjectRequest.setTag(tag);
        requestQueue.add(jsonObjectRequest);
        requestQueue.start();
    }

    public static void jsonPost(Context context, String url, JSONObject jsonRequest,String tag,VolleyInterface volleyInterface){
        requestQueue.cancelAll(tag);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,url,jsonRequest,volleyInterface.listener,volleyInterface.errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        jsonObjectRequest.setTag(tag);
        requestQueue.add(jsonObjectRequest);
        requestQueue.start();
    }
}
