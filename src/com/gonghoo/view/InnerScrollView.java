package com.gonghoo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 主要是嵌套listView 让父控件交出滑动事件控制权，让listView有滚动监听
 * Created by zudesalin on 2016/8/10.
 */
public class InnerScrollView extends ScrollView {


    public InnerScrollView(Context context) {
        super(context);
    }

    public InnerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InnerScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

/*    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }*/
}
