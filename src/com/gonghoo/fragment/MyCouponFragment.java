package com.gonghoo.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.gonghoo.R;
import com.gonghoo.activity.DeskTopActivity;
import com.gonghoo.utils.Configure;
import com.gonghoo.volleyInterface.VolleyInterface;
import com.gonghoo.volleyInterface.VolleyInterfaceStr;
import com.gonghoo.volleyInterface.VolleyRequest;
import com.gonghoo.volleyInterface.VolleyStrRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zudesalin on 2016/11/16.
 */
@SuppressLint("ValidFragment")
public class MyCouponFragment extends BackHandledFragment {
    private View view=null;
    private Context context;
    ListView listView;
    String url= "";
    JSONArray jsonArray =new JSONArray();
    public MyCouponFragment(Context context){
        this.context=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.mycoupon,container,false);
        url=Configure.getPropertiesURL(context)+"/personCenter/showCouponList.action?userId=934";
        new Thread(runnable).start();
        listView= (ListView) view.findViewById(R.id.myCouponListView);

        return view;
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(jsonArray.length()==0){
                view.findViewById(R.id.mycoupon_noData).setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            }else{
                view.findViewById(R.id.mycoupon_noData).setVisibility(View.GONE);
                listView.setAdapter(new CustomerAdapter());
            }
        }
    };


    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            loadData();
        }
    };
    private void loadData(){
        VolleyStrRequest.strGet(context,url,"mycouponList", new VolleyInterfaceStr(VolleyInterfaceStr.listener, VolleyInterfaceStr.errorListener) {
            @Override
            public void onSuccess(String str) {
                try {
                    jsonArray=new JSONArray(str);
                    Message message=Message.obtain();
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VolleyError volleyError) {
                Log.i("zzz",volleyError.getMessage());
               volleyError.printStackTrace();
            }
        });
    }

    @Override
    public boolean onBackPressed() {
        ((DeskTopActivity) getActivity()).iconChoolsed(2);
        getActivity().getSupportFragmentManager().popBackStack();
        return true;
    }

    /**
     * 适配器
     */
    class CustomerAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return jsonArray.length();
        }

        @Override
        public Object getItem(int position) {
            JSONObject jsonObject=null;
            try {
                jsonObject=jsonArray.getJSONObject(position);
            }catch (Exception e){
                e.printStackTrace();
            }
            return  jsonObject;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.mycoupon_item,null);
            TextView mycoupon_money_tv= (TextView) convertView.findViewById(R.id.mycoupon_money_tv);
            TextView  mycoupon_limit_tv=(TextView) convertView.findViewById(R.id.mycoupon_limit_tv);
            TextView  mycoupon_type_tv=(TextView) convertView.findViewById(R.id.mycoupon_type_tv);
            TextView  mycoupon_date_tv=(TextView) convertView.findViewById(R.id.mycoupon_date_tv);
            try {
                JSONObject jsonObject= (JSONObject) jsonArray.get(position);
                mycoupon_money_tv.setText(jsonObject.getString("money"));
                mycoupon_limit_tv.setText("满"+jsonObject.getString("limit_max")+"元使用");
                mycoupon_type_tv.setText(jsonObject.getJSONObject("couponType").getString("name"));
                mycoupon_date_tv.setText("有效期 "+jsonObject.getString("create_time"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return convertView;
        }
    }
}
