package com.gonghoo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.LruCache;
import com.gonghoo.R;
import com.gonghoo.view.RoundRectImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by DESKTOP-M874RE3$ on 2016/8/4.
 */
public class ImageViewLoader {
    LruCache<String,Bitmap> lruCache=null;
    public ImageViewLoader(){
        int memorySize=(int)Runtime.getRuntime().maxMemory();
        int cacheSize=memorySize/4;
        lruCache=new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }
    private void addLruCache(String url,Bitmap bitmap){
        if(lruCache.get(url)==null){
            lruCache.put(url,bitmap);
        }
    }
    private Bitmap getBitMapFromCache(String url){
            return lruCache.get(url);
    }

    public Bitmap getBitMapFromUrl(String urlString) {
        InputStream in = null;
        Bitmap bitmap = null;
        try {
            URL url=new URL(urlString);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            in=new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(in);
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    public void  loadImageViewByAsyncTask(RoundRectImageView mImageView,String url){
        if(lruCache.get(url)!=null){
            mImageView.setImageBitmap(getBitMapFromCache(url));
        }else {
            new LoadImageView(mImageView, url).execute(url);
        }
    }

    private class LoadImageView extends  AsyncTask<String,Void,Bitmap>{
        RoundRectImageView mImageView;
        String urlString="";
        public LoadImageView(RoundRectImageView imageView,String urlString){
            this.mImageView=imageView;
            this.urlString=urlString;
        }
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap=null;
            bitmap=getBitMapFromUrl(params[0]);
            if(bitmap!=null){
              addLruCache(params[0],bitmap);
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap==null){
                mImageView.setImageResource(R.drawable.masternopic);
            }else{
                if(mImageView.getTag().equals(urlString)){
                    mImageView.setImageBitmap(bitmap);
                }
            }
        }
    }
}
