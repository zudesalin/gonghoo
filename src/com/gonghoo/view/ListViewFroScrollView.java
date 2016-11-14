package com.gonghoo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by zudesalin on 2016/8/5.
 */
public class ListViewFroScrollView extends ListView {
    public ListViewFroScrollView(Context context) {
        super(context);
    }

    public ListViewFroScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewFroScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
