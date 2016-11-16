package com.gonghoo.volleyInterface;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by zudesalin on 2016/8/8.
 */
public class BitMapCache implements ImageLoader.ImageCache {
    int maxSize=(int)Runtime.getRuntime().maxMemory();
    LruCache<String,Bitmap> lruCache=new LruCache<String,Bitmap>(maxSize){
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes()*value.getHeight();
        }
    };
    @Override
    public Bitmap getBitmap(String s) {
        return lruCache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        lruCache.put(s,bitmap);
    }
}
