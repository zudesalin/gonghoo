package com.gonghoo.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.android.volley.VolleyError;
import com.gonghoo.R;
import com.gonghoo.activity.DeskTopActivity;
import com.gonghoo.activity.JianghuDetailActivity;
import com.gonghoo.pojo.Activities;
import com.gonghoo.utils.utils;
import com.gonghoo.view.ListViewFroScrollView;
import com.gonghoo.view.RoundRectImageView;
import com.gonghoo.volleyInterface.VolleyInterface;
import com.gonghoo.volleyInterface.VolleyRequest;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zudesalin on 2016/11/15.
 */
@SuppressLint("ValidFragment")
public class JianghuFragment extends BackHandledFragment implements ListViewFroScrollView.ILoadingData {
    ListViewFroScrollView activititListView = null;
    List<Activities> activitiesList = new ArrayList<Activities>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
    listViewAdapter listViewAdapter = null;
    Integer pageNo = 1;
    JSONObject requestJSONObject = new JSONObject();
    private String url = "http://m.gonghoo.com/mobile/appActivitiesList.action";
    private Context context;
    public JianghuFragment(Context context){
        this.context=context;
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    initView();
                    activititListView.finishFresh();
                    break;
            }
        }
    };

    private void initView() {
        if (listViewAdapter == null) {
            activititListView.setILoadingData(this);
            listViewAdapter = new listViewAdapter();
            activititListView.setAdapter(listViewAdapter);
            activititListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent mIntent = new Intent(context, JianghuDetailActivity.class);
                    mIntent.putExtra("id", activitiesList.get(position).getId() + "");
                    startActivity(mIntent);
                }
            });
        } else {
            listViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main,container,false);
        final ScrollView scrollView = (ScrollView) view.findViewById(R.id.jianghu_scrollView);
        activititListView = (ListViewFroScrollView) view. findViewById(R.id.activityListView);
        activititListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        scrollView.requestDisallowInterceptTouchEvent(false);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        try {
            Thread thread = new Thread(runnable);
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;

    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            loadData();
        }
    };

    private void loadData() {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            requestJSONObject.put("pageNo", pageNo.toString());
            String result = "";
            VolleyRequest.jsonGet(context, url, requestJSONObject, "jhList", new VolleyInterface(context, VolleyInterface.listener, VolleyInterface.errorListener) {
                @Override
                public void onSuccess(JSONObject jsonObject) {
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = (JSONArray) jsonObject.get("datas");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Activities activities = new Activities();
                            JSONObject activitiesObj = (JSONObject) jsonArray.get(i);
                            activities.setId(activitiesObj.getInt("id"));
                            activities.setTitle(activitiesObj.getString("title"));
                            activities.setPlace(activitiesObj.getString("province"));
                            activities.setLimitPerson(utils.nullToInteger(activitiesObj.getString("limitPerson")));
                            activities.setHadSignUpCount(activitiesObj.getInt("hadSignUpCount"));
                            activities.setStartDate(simpleDateFormat.parse(activitiesObj.getString("startDate")));
                            activities.setLogo(utils.nullToSpace(activitiesObj.getString("logo")));
                            activitiesList.add(activities);
                            Message message = Message.obtain();
                            message.what = 1;
                            handler.sendMessage(message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(VolleyError volleyError) {
                    Toast.makeText(context, volleyError.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        //获取更多数据，通知ListView更新数据
        pageNo++;
        loadData();
    }

    @Override
    public boolean onBackPressed() {
       ((DeskTopActivity) getActivity()).iconChoolsed(0);
        getActivity().getSupportFragmentManager().popBackStack();
        return true;
    }

    class listViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return activitiesList.size();
        }

        @Override
        public Object getItem(int i) {
            return activitiesList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(context);
            ViewHolder viewHolder = null;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = inflater.inflate(R.layout.jianghu_item, null);
                viewHolder.activity_title = (TextView) view.findViewById(R.id.activity_title);
                viewHolder.activity_place = (TextView) view.findViewById(R.id.activity_place);
                viewHolder.activity_people = (TextView) view.findViewById(R.id.activity_people);
                viewHolder.activity_time = (TextView) view.findViewById(R.id.activity_time);
                viewHolder.activity_limit = (TextView) view.findViewById(R.id.activity_limit);
                viewHolder.activity_img = (RoundRectImageView) view.findViewById(R.id.activity_img);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.activity_title.setText(activitiesList.get(i).getTitle());
            viewHolder.activity_place.setText(activitiesList.get(i).getPlace());
            viewHolder.activity_people.setText(utils.nullToSpace(activitiesList.get(i).getHadSignUpCount() + ""));
            viewHolder.activity_time.setText(dateFormat.format(activitiesList.get(i).getStartDate()));
            viewHolder.activity_limit.setText(utils.nullToSpace(activitiesList.get(i).getLimitPerson() == 0 ? "无限制" : "限" + activitiesList.get(i).getLimitPerson() + "人"));
            // viewHolder.activity_img.setTag("http://m.gonghoo.com/cms/banner/" +activitiesList.get(i).getLogo());
            Picasso.with(context).load("http://m.gonghoo.com/cms/banner/" + activitiesList.get(i).getLogo()).resize(200, 200).error(R.drawable.masternopic).into(viewHolder.activity_img);
            return view;
        }

        class ViewHolder {
            TextView activity_title, activity_place, activity_time, activity_people, activity_limit;
            RoundRectImageView activity_img;

        }
    }

 /*   @Override
    protected void onStop() {
        super.onStop();
        GhApplication.getRequestQueue().cancelAll("jhList");
    }*/
}