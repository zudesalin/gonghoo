package com.gonghoo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.gonghoo.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**江湖报名
 * Created by zudesalin on 2016/8/19.
 */
public class JianghuAttendActivity extends Activity {
    private ListView ticketsInfoListView= null;
    JSONArray jsonArray=null;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jianghu_attend);
        context=JianghuAttendActivity.this;
        String ticketsInfo=getIntent().getStringExtra("ticketsInfo");
        try {
            jsonArray= (JSONArray) new JSONObject(ticketsInfo).get("purchased_ticket");
            if(jsonArray==null){
                jsonArray=new JSONArray();
            }
            initView();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initView(){
        ticketsInfoListView= (ListView) findViewById(R.id.jianghu_attend_tickets_listView);
        ticketsInfoListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return jsonArray.length();
            }

            @Override
            public Object getItem(int position) {
                try {
                    return jsonArray.get(position);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView= LayoutInflater.from(context).inflate(R.layout.jianghu_tickets_choose_item,null);
                try {
                    ((TextView) convertView.findViewById(R.id.jianghu_attend_tickets_item_type)).setText(((JSONObject) jsonArray.get(position)).get("type").toString());
                    ((TextView) convertView.findViewById(R.id.jianghu_attend_tickets_item_num)).setText("×"+((JSONObject) jsonArray.get(position)).get("num").toString());
                    ((TextView) convertView.findViewById(R.id.jianghu_attend_tickets_unitPrice)).setText("￥"+((JSONObject) jsonArray.get(position)).get("unitPrice").toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
                return convertView;
            }
        });

    }
}
