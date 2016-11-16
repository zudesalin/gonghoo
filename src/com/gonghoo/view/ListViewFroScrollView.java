package com.gonghoo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import com.gonghoo.R;

/**
 * Created by zudesalin on 2016/8/5.
 */
public class ListViewFroScrollView extends ListView implements AbsListView.OnScrollListener{
    private View footer;
    private int lastVisibleItem;
    private ILoadingData iLoadingData;
    private int totalItemCount;
    private boolean isLoading;
    public ListViewFroScrollView(Context context) {
        super(context);
        initFooterView(context);
    }

    public ListViewFroScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFooterView(context);
    }

    public ListViewFroScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initFooterView(context);
    }
    private void initFooterView(Context context){
        LayoutInflater inflater=LayoutInflater.from(context);
        footer=inflater.inflate(R.layout.pulltofresh_foot,null);
        footer.findViewById(R.id.listView_footer).setVisibility(View.GONE);
        this.addFooterView(footer);
        this.setOnScrollListener(this);
    }


    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibleItem=firstVisibleItem+visibleItemCount;
        this.totalItemCount=totalItemCount;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //状态为滚动停止
        if(totalItemCount==lastVisibleItem&&scrollState==SCROLL_STATE_IDLE){
            footer.findViewById(R.id.listView_footer).setVisibility(View.VISIBLE);
            //加载更多
            if(!isLoading){
                isLoading=true;
                iLoadingData.load();
            }
        }
    }
    public void setILoadingData(ILoadingData iLoadingData){
        this.iLoadingData=iLoadingData;
    }
    public void finishFresh(){
        isLoading=false;
        footer.findViewById(R.id.listView_footer).setVisibility(View.GONE);
    }
    public interface  ILoadingData{
        public void load();
    }
}
