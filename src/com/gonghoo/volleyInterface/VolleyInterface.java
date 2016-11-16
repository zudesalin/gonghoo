package com.gonghoo.volleyInterface;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.gonghoo.Application.GhApplication;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zudesalin on 2016/8/8.
 */
public abstract class VolleyInterface {
    private Context mContext;
    public static Response.Listener<JSONObject> listener;
    public static Response.ErrorListener errorListener;
    public VolleyInterface(Context context, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        this.mContext=context;
        this.listener=listener;
        this.errorListener=errorListener;
    }
    public abstract void onSuccess(JSONObject jsonObject);
    public abstract void onError(VolleyError volleyError);
    public Response.Listener listenerLoader(){
        listener=new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                onSuccess(jsonObject);
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
