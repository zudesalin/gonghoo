package com.gonghoo.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.gonghoo.R;
import com.gonghoo.pojo.Activities;
import com.gonghoo.utils.BasicPost;
import com.gonghoo.utils.ImageViewLoader;
import com.gonghoo.utils.utils;
import com.gonghoo.view.ListViewFroScrollView;
import com.gonghoo.view.RoundRectImageView;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {
    ListViewFroScrollView activititListView=null;
    List<Activities> activitiesList=new ArrayList<Activities>();
    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy年MM月dd日");
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:{
                    activititListView=(ListViewFroScrollView) findViewById(R.id.activityListView);
                    activititListView.setAdapter(new listViewAdapter(new ImageViewLoader()));
                }
            }
        }
    };
    private String url="http://m.gonghoo.com/mobile/appActivitiesList.action";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Thread thread=new Thread(runnable);
        thread.start();
    }
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            loadData();
        }
    };
    private void loadData(){
        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            String result="";
            HttpResponse response= BasicPost.post(url, nameValuePairs);
            if(response!=null&&response.getStatusLine().getStatusCode()==200){
                InputStream in=response.getEntity().getContent();
                InputStreamReader InputStreamReader=new InputStreamReader(in);
                BufferedReader br=new BufferedReader(InputStreamReader);
                String line="";
                while ((line=br.readLine())!=null){
                    result+=line;
                }
                JSONObject json=new JSONObject(result);
                JSONArray jsonArray= (JSONArray) json.get("datas");
                for(int i=0;i<jsonArray.length();i++){
                    Activities activities=new Activities();
                    JSONObject activitiesObj= (JSONObject) jsonArray.get(i);
                    activities.setTitle(activitiesObj.getString("title"));
                    activities.setPlace(activitiesObj.getString("province"));
                    activities.setLimitPerson(utils.nullToInteger(activitiesObj.getString("limitPerson")));
                    activities.setHadSignUpCount(activitiesObj.getInt("hadSignUpCount"));
                    activities.setStartDate(simpleDateFormat.parse(activitiesObj.getString("startDate")));
                    activities.setLogo(utils.nullToSpace(activitiesObj.getString("logo")));
                    activitiesList.add(activities);
                }
                Message message=Message.obtain();
                message.what=1;
                handler.sendMessage(message);
            }
        }catch(IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class listViewAdapter extends BaseAdapter{
        private ImageViewLoader imageViewLoader;
        private listViewAdapter(ImageViewLoader imageViewLoader){
            this.imageViewLoader=imageViewLoader;
        }
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
            LayoutInflater inflater= LayoutInflater.from(MainActivity.this);
           ViewHolder  viewHolder=null;
            if(view==null){
                viewHolder=new ViewHolder();
                view = inflater.inflate(R.layout.jianghu_item, null);
                viewHolder.activity_title= (TextView) view.findViewById(R.id.activity_title);
                viewHolder.activity_place= (TextView) view.findViewById(R.id.activity_place);
                viewHolder.activity_people= (TextView) view.findViewById(R.id.activity_people);
                viewHolder.activity_time= (TextView) view.findViewById(R.id.activity_time);
                viewHolder.activity_limit= (TextView) view.findViewById(R.id.activity_limit);
                viewHolder.activity_img= (RoundRectImageView) view.findViewById(R.id.activity_img);
                view.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolder)view.getTag();
            }
            viewHolder.activity_title.setText(activitiesList.get(i).getTitle());
            viewHolder.activity_place.setText(activitiesList.get(i).getPlace());
            viewHolder.activity_people.setText(utils.nullToSpace(activitiesList.get(i).getHadSignUpCount()+""));
            viewHolder.activity_time.setText(dateFormat.format(activitiesList.get(i).getStartDate()));
            viewHolder.activity_limit.setText(utils.nullToSpace(activitiesList.get(i).getLimitPerson()==0?"无限制":"限"+activitiesList.get(i).getLimitPerson()+"人"));
            viewHolder.activity_img.setImageResource(R.drawable.masternopic);
            viewHolder.activity_img.setTag("http://m.gonghoo.com/cms/banner/"+activitiesList.get(i).getLogo());
            imageViewLoader.loadImageViewByAsyncTask(viewHolder.activity_img,"http://m.gonghoo.com/cms/banner/"+activitiesList.get(i).getLogo());//异步加载图片
            return view;
        }

        class ViewHolder{
            TextView activity_title,activity_place,activity_time,activity_people, activity_limit;
            RoundRectImageView activity_img;

        }
    }
}
