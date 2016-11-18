package com.gonghoo.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.gonghoo.R;
import com.gonghoo.utils.Configure;
import com.gonghoo.utils.CustomProgressDialog;
import com.gonghoo.view.RoundRectImageView;
import com.gonghoo.volleyInterface.VolleyInterface;
import com.gonghoo.volleyInterface.VolleyRequest;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by zudesalin on 2016/11/15.
 */
@SuppressLint("ValidFragment")
public class MineFragment extends Fragment implements View.OnClickListener{
    View view=null;
    private Context context;
    String url="";
    RoundRectImageView mine_headeImg;
    private JSONObject jsonObject=null;
    TextView mine_integralLevel_tv=null,friendsCount_tv=null,integralCount_tv=null,cardCount_tv,userName_tv,mine_job_tv,mine_profit_tv,contectionsCount_tv;
    LinearLayout cardCount_ly = null, main_personInfo_ly = null, main_jianghu_ly = null, main_signupActivity_ly, main_createActivity_ly, main_myinvite_ly, main_myshare_ly, mine_other_ly;
    ImageView main_jianghu_jiantou = null;
    boolean rotation = false;//是否旋转
    public MineFragment(Context context){
        this.context=context;
    }
    CustomProgressDialog dialog=null;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dialog.hide();
            jsonObject=(JSONObject) msg.obj;
            JSONObject jsonObject= (JSONObject) msg.obj;
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
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.mine,container,false);
        url= Configure.getPropertiesURL(context)+"/contacts/userCenterData.action?userId=934";
        dialog =new CustomProgressDialog(context, "正在加载中");
        loadData();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //注册点击事件
        cardCount_ly= (LinearLayout) getActivity().findViewById(R.id.cardCount_ly);
        main_personInfo_ly= (LinearLayout) getActivity().findViewById(R.id.main_personInfo_ly);
        main_jianghu_ly = (LinearLayout) getActivity().findViewById(R.id.main_jianghu_ly);
        cardCount_ly.setOnClickListener(this);
        main_personInfo_ly.setOnClickListener(this);
        main_jianghu_ly.setOnClickListener(this);
        main_signupActivity_ly = (LinearLayout) view.findViewById(R.id.main_signupActivity_ly);
        main_createActivity_ly = (LinearLayout) view.findViewById(R.id.main_createActivity_ly);
        main_myinvite_ly = (LinearLayout) view.findViewById(R.id.main_myinvite_ly);
        main_myshare_ly = (LinearLayout) view.findViewById(R.id.main_myshare_ly);
        mine_other_ly = (LinearLayout) view.findViewById(R.id.mine_other_ly);
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

        main_jianghu_jiantou = (ImageView) view.findViewById(R.id.main_jianghu_jiantou);


    }
    private void loadData(){
        initView();
        dialog.show();
        VolleyRequest.jsonGet(context, url, null, "personInfo", new VolleyInterface(context,VolleyInterface.listener,VolleyInterface.errorListener) {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                Message msg=Message.obtain();
                msg.obj=jsonObject;

                handler.sendMessage(msg);
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
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cardCount_ly:
                MyCouponFragment myCouponFragment=new MyCouponFragment(context);
                FragmentManager fm=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=fm.beginTransaction();
                transaction.add(R.id.deskFrameLayout,myCouponFragment);
                transaction.addToBackStack("mineFragment");
                transaction.commit();
                break;
            case R.id.main_personInfo_ly:
                PersonInfoFragment personInfoFragment=new PersonInfoFragment(context);
                Bundle bundle=new Bundle();
                bundle.putString("userInfo", jsonObject.toString());
                personInfoFragment.setArguments(bundle);
                fm=getActivity().getSupportFragmentManager();
                transaction=fm.beginTransaction();
                transaction.add(R.id.deskFrameLayout,personInfoFragment);
                transaction.addToBackStack("mineFragment");
                transaction.commit();
                break;
            case R.id.main_jianghu_ly:
                ObjectAnimator rotationAnimator = null;
                ObjectAnimator translationAnimator = null;
                AnimatorSet animatorSet = new AnimatorSet();

                if (!rotation) {
                    rotationAnimator = ObjectAnimator.ofFloat(main_jianghu_jiantou, "rotation", 0f, 90f);
                    translationAnimator = ObjectAnimator.ofFloat(mine_other_ly, "translationY", 0, main_signupActivity_ly.getHeight() + main_createActivity_ly.getHeight());
                    rotation = true;
                } else {
                    rotationAnimator = ObjectAnimator.ofFloat(main_jianghu_jiantou, "rotation", 90f, 0f);
                    translationAnimator = ObjectAnimator.ofFloat(mine_other_ly, "translationY", main_signupActivity_ly.getHeight() + main_createActivity_ly.getHeight(), 0);
                    rotation = false;
                }
                animatorSet.playTogether(rotationAnimator, translationAnimator);
                animatorSet.setDuration(500);
                animatorSet.start();
                break;
        }
    }
}
