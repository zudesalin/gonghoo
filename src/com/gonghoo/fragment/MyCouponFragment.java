package com.gonghoo.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
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
public class MyCouponFragment extends Fragment {
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
        loadData();
        listView= (ListView) view.findViewById(R.id.myCouponListView);
        listView.setAdapter(new CustomerAdapter());
        return view;
    }

    private void loadData(){
        VolleyStrRequest.strGet(context,url,"mycouponList", new VolleyInterfaceStr(VolleyInterfaceStr.listener, VolleyInterfaceStr.errorListener) {
            @Override
            public void onSuccess(String str) {
                try {
                    jsonArray=new JSONArray(str);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VolleyError volleyError) {
               volleyError.printStackTrace();
            }
        });
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
