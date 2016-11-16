package com.gonghoo.volleyInterface;

import android.widget.ImageView;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

/**
 * 重写图片加载进行tag判断 防止图片闪烁错位
 * Created by zudesalin on 2016/8/8.
 */
public class VolleyImageListener extends ImageLoader {
    public VolleyImageListener(RequestQueue queue, ImageCache imageCache) {
        super(queue, imageCache);
    }
    public static ImageLoader.ImageListener getImageListener(final ImageView view, final int defaultImageResId, final int errorImageResId) {
        return new ImageLoader.ImageListener() {
            public void onErrorResponse(VolleyError error) {
                if(errorImageResId != 0) {
                    view.setImageResource(errorImageResId);
                }

            }

            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if(response.getBitmap() != null) {
                    if(view.getTag().equals(response.getRequestUrl())){
                        view.setImageBitmap(response.getBitmap());
                    }
                } else if(defaultImageResId != 0) {
                    view.setImageResource(defaultImageResId);
                }

            }
        };
    }
}
