package com.gonghoo.Application;

import android.app.Application;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ClearCacheRequest;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.Volley;

import java.io.File;

/**
 * Created by zudesalin on 2016/8/8.
 */
public class GhApplication extends Application {
    private static RequestQueue mRequestQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        mRequestQueue= Volley.newRequestQueue(getApplicationContext());

    }
    public static RequestQueue getRequestQueue(){
        return mRequestQueue;
    }
}
