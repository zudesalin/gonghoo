package com.gonghoo.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.webkit.*;
import android.widget.*;
import com.android.volley.VolleyError;
import com.gonghoo.R;
import com.gonghoo.pojo.ActivitiesTicket;
import com.gonghoo.volleyInterface.VolleyInterface;
import com.gonghoo.volleyInterface.VolleyRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zudesalin on 2016/8/15.
 */
public class JianghuDetailActivity extends Activity{
    WebView mWebView=null;
    String url="";
    TextView jianghu_detail_title_tv=null;
    private View popupWindowView=null;
    Context context=null;
    LinearLayout jianghu_detail_bottom_LinnerLayout;
    List<ActivitiesTicket> activitiesTickets=new ArrayList<>();
    JSONObject ticketsInfo=new JSONObject();
    ListView listView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jianghu_detail);
        context=JianghuDetailActivity.this;
        Intent intent=getIntent();
        String actId=intent.getStringExtra("id");
        url="http://m.gonghoo.com/mobile/appActivitiesPrivew.action?actId="+actId+"&userId=667";
        initView();
        loadTickets();
    }
    private List<ActivitiesTicket> loadTickets(){
        String url="http://m.gonghoo.com/mobile/appPayTickets.action?actId=2502&orderId=2763";
        final JSONObject requestJson=new JSONObject();
        VolleyRequest.jsonGet(context, url, requestJson, "tickets_list", new VolleyInterface(context,VolleyInterface.listener,VolleyInterface.errorListener) {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray =(JSONArray) jsonObject.get("tickets");
                    for(int i=0;i<jsonArray.length();i++){
                        ActivitiesTicket activitiesTicket=new ActivitiesTicket();
                        JSONObject resultJson= new JSONObject(jsonArray.get(i).toString());
                        activitiesTicket.setId(resultJson.getInt("id"));
                        activitiesTicket.setType(resultJson.getString("type"));
                        activitiesTicket.setSurplusNum(resultJson.getInt("surplusNum"));
                        activitiesTicket.setPrice(resultJson.getDouble("price"));
                        activitiesTickets.add(activitiesTicket);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VolleyError volleyError) {

            }
        });
        return activitiesTickets;
    }
    private void popWindow(){
        popupWindowView= LayoutInflater.from(context).inflate(R.layout.ticket_choose_pop,null);
        final PopupWindow  popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.rgb(193, 193, 193)));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.getContentView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.setFocusable(false);
                popupWindow.dismiss();
                return true;
            }
        });

        listView=(ListView) popupWindowView.findViewById(R.id.popWindow_listView);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return activitiesTickets.size();
            }

            @Override
            public Object getItem(int position) {
                return activitiesTickets.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int i, View convertView, ViewGroup parent) {
                convertView=LayoutInflater.from(JianghuDetailActivity.this).inflate(R.layout.ticket_choose_pop_item,null);
                convertView.setTag(activitiesTickets.get(i).getId().toString());
                ((TextView)convertView.findViewById(R.id.jh_detail_ticket_type)).setText(activitiesTickets.get(i).getType());
                ((TextView)convertView.findViewById(R.id.jh_detail_ticket_price)).setText(activitiesTickets.get(i).getPrice().toString()+"元");
                final Integer remindNum=activitiesTickets.get(i).getSurplusNum();
                ((TextView)convertView.findViewById(R.id.jh_detail_ticket_left)).setText("剩余"+remindNum+"张");
                final TextView ticketNum_tv= ((TextView)convertView.findViewById(R.id.ticket_num));
                convertView.findViewById(R.id.ticket_payjia).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int num=Integer.parseInt(ticketNum_tv.getText().toString());
                        if(num>=10){
                            Toast.makeText(context,"票数不允许超过10张",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(num>=remindNum){
                            Toast.makeText(context,"票数不允许超过剩余票数",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        ticketNum_tv.setText(num+1+"");
                    }
                });
                convertView.findViewById(R.id.ticket_payjian).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int num=Integer.parseInt(ticketNum_tv.getText().toString());
                        if(num<=0){
                            Toast.makeText(context,"不能再减了...",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        ticketNum_tv.setText(num-1+"");
                    }
                });
                return convertView;
            }
        });

        popupWindow.setAnimationStyle(R.style.popupAnimation);
        popupWindow.showAtLocation(popupWindowView, Gravity.CENTER, 0, 900);
        popupWindow.setFocusable(true);
    }

    private void initView() {
        jianghu_detail_title_tv= (TextView) findViewById(R.id.jianghu_detail_title_tv);
        jianghu_detail_bottom_LinnerLayout= (LinearLayout) findViewById(R.id.jianghu_detail_bottom_LinnerLayout);
        mWebView = (WebView) findViewById(R.id.jianghu_detail_webview);
        {
            mWebView = (WebView) findViewById(R.id.jianghu_detail_webview);
            mWebView.loadUrl(url);
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            mWebView.addJavascriptInterface(new getWebViewInfo(),"jh_detail");
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
            mWebView.requestFocus();
            mWebView.setWebViewClient(new MyWebViewClient());
            mWebView.setWebChromeClient(new WebChromeClient() {
                @Override
                public boolean onJsAlert(WebView view, String url, String message,
                                         final JsResult result) {
                    AlertDialog.Builder b2 = new AlertDialog.Builder(
                            JianghuDetailActivity.this)
                            .setTitle("")
                            .setMessage(message)
                            .setPositiveButton("确定",
                                    new AlertDialog.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                            result.confirm();
                                            // MyWebView.this.finish();
                                        }
                                    });

                    b2.setCancelable(false);
                    b2.create();
                    b2.show();
                    return true;
                }
            });
        }
    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jianghu_detail_btn_baoming:
                popWindow();
                break;
            case R.id.jianghu_detail_ticketchoosed_baoming:
                if(listView!=null){
                    JSONArray jsonArray=null;
                    JSONObject ticketJson=null;
                    jsonArray=new JSONArray();
                    try {
                        for(int i=0;i<listView.getChildCount();i++){
                        View view=listView.getChildAt(i);
                        Integer ticketNum=Integer.parseInt(((TextView)view.findViewById(R.id.ticket_num)).getText().toString());
                        if(ticketNum<=0) {
                            continue;
                        }
                        ticketJson=new JSONObject();
                        ticketJson.put("id",view.getTag()+"");
                        ticketJson.put("type",((TextView)view.findViewById(R.id.jh_detail_ticket_type)).getText().toString());
                        ticketJson.put("unitPrice",((TextView)view.findViewById(R.id.jh_detail_ticket_price)).getText().toString());
                        ticketJson.put("num",ticketNum.toString());
                        jsonArray.put(ticketJson);
                    }
                        ticketsInfo.put("purchased_ticket",jsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent=new Intent(context,JianghuAttendActivity.class);
                intent.putExtra("ticketsInfo",ticketsInfo.toString());
                startActivity(intent);
                break;
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            view.loadUrl("javascript:$function(){window.jh_detail.getTitle('123')}");
        }

    }
    private class getWebViewInfo{
        @JavascriptInterface
        public void getTitle(String title){
            Log.i("zzz",title);
        }

    }
}
