package com.gonghoo.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.gonghoo.R;
import com.gonghoo.utils.Configure;
import com.gonghoo.view.RoundRectImageView;
import com.gonghoo.volleyInterface.VolleyInterface;
import com.gonghoo.volleyInterface.VolleyRequest;
import com.squareup.picasso.Picasso;
import org.json.JSONObject;

/**
 * Created by zudesalin on 2016/11/15.
 */
@SuppressLint("ValidFragment")
public class MineFragment extends Fragment {
    View view=null;
    private Context context;
    private FragmentTransaction transaction;
    String url="";
    RoundRectImageView mine_headeImg;
    TextView mine_integralLevel_tv=null,friendsCount_tv=null,integralCount_tv=null,cardCount_tv,userName_tv,mine_job_tv,mine_profit_tv,contectionsCount_tv;
    LinearLayout cardCount_ly=null;
    public MineFragment(Context context,FragmentTransaction transaction){
        this.context=context;
        this.transaction=transaction;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.mine,container,false);
        url= Configure.getPropertiesURL(context,"url")+"/contacts/userCenterData.action?userId=934";
        loadData();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //注册点击事件
        cardCount_ly= (LinearLayout) getActivity().findViewById(R.id.cardCount_ly);
        cardCount_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCouponFragment myCouponFragment = new MyCouponFragment(context);
                transaction.add(myCouponFragment, "myCouponFragment");
                transaction.replace(R.id.deskFrameLayout, myCouponFragment);
                transaction.commit();
            }

        });
    }

    private void initView(){
        mine_integralLevel_tv= (TextView) view.findViewById(R.id.mine_integralLevel);
        friendsCount_tv= (TextView) view.findViewById(R.id.friendsCount_tv);
        integralCount_tv= (TextView) view.findViewById(R.id.integralCount_tv);
        cardCount_tv= (TextView) view.findViewById(R.id.cardCount_tv);
        userName_tv= (TextView) view.findViewById(R.id.userName_tv);
        mine_job_tv= (TextView) view.findViewById(R.id.mine_job_tv);
        mine_profit_tv= (TextView) view.findViewById(R.id.mine_profit_tv);
        contectionsCount_tv= (TextView) view.findViewById(R.id.contectionsCount_tv);
        mine_headeImg= (RoundRectImageView) view.findViewById(R.id.mine_headeImg);


    }
    private void loadData(){
        initView();
        VolleyRequest.jsonGet(context, url, null, "personInfo", new VolleyInterface(context,VolleyInterface.listener,VolleyInterface.errorListener) {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                try {
                    JSONObject userJson=jsonObject.getJSONObject("user");
                    mine_integralLevel_tv.setText(jsonObject.getString("integralLevel"));
                    friendsCount_tv.setText(jsonObject.getString("friendsCount"));
                    integralCount_tv.setText("积分："+jsonObject.getString("integralCount"));
                    cardCount_tv.setText(jsonObject.getString("cardCount")+"张");
                    userName_tv.setText(userJson.getString("name"));
                    mine_job_tv.setText(userJson.getString("companyName"));
                    mine_profit_tv.setText("￥"+userJson.getString("profit"));
                    contectionsCount_tv.setText(jsonObject.getString("contectionsCount"));
                    Picasso.with(context).load(userJson.getString("logo")).resize(100, 100).error(R.drawable.masternopic).into(mine_headeImg);
                }catch (Exception e){
                    Log.i("zzz","json解析错误");
                }
            }

            @Override
            public void onError(VolleyError volleyError) {
                Log.i("zzz","请求错误");
            }
        });


    }

    /**
     * 点击事件
     * @param v
     */
/*    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cardCount_ly:
                MyCouponFragment myCouponFragment=new MyCouponFragment(context);
                transaction.add(myCouponFragment,"myCouponFragment");
                transaction.replace(R.id.deskFrameLayout,myCouponFragment);
                transaction.commit();
                break;
        }
    }*/
}
