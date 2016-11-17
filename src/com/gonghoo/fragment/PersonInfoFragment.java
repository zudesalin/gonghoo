package com.gonghoo.fragment;

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

/**
 * Created by zudesalin on 2016/11/15.
 */
@SuppressLint("ValidFragment")
public class PersonInfoFragment extends Fragment implements View.OnClickListener{
    View view=null;
    private Context context;
    ImageView personInfo_headImg=null,personInfo_QRImg=null;
    TextView personInfo_position,personInfo_name,personInfo_company,personInfo_mobile,personInfo_email,personInfo_address;
    JSONObject jsonObject= null;
    public PersonInfoFragment(Context context){
        this.context=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.person_info,container,false);
        try {
            jsonObject = new JSONObject(getArguments().getString("userInfo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        loadData();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //注册点击事件

    }

    private void initView(){
        JSONObject userJson= null;
        try {
            userJson = jsonObject.getJSONObject("user");
            personInfo_headImg= (ImageView) view.findViewById(R.id.personInfo_headImg);
            personInfo_QRImg= (ImageView) view.findViewById(R.id.personInfo_QRImg);
            personInfo_position= (TextView) view.findViewById(R.id.personInfo_position);
            personInfo_name= (TextView) view.findViewById(R.id.personInfo_name);
            personInfo_company= (TextView) view.findViewById(R.id.personInfo_company);
            personInfo_mobile= (TextView) view.findViewById(R.id.personInfo_mobile);
            personInfo_email= (TextView) view.findViewById(R.id.personInfo_email);
            personInfo_address= (TextView) view.findViewById(R.id.personInfo_address);;

            Picasso.with(context).load(userJson.getString("logo")).resize(100, 100).error(R.drawable.masternopic).into(personInfo_headImg);
            Picasso.with(context).load(jsonObject.getString("userQrcode")).resize(100, 100).error(R.drawable.masternopic).into(personInfo_QRImg);
            personInfo_position.setText(userJson.getString("position"));
            personInfo_name.setText(userJson.getString("name"));
            personInfo_company.setText(userJson.getString("companyName"));
            personInfo_mobile.setText(userJson.getString("phoneNumber"));
            personInfo_email.setText(userJson.getString("emailAddress"));
            personInfo_address.setText(userJson.getString("county")+"."+userJson.getString("province")+"."+userJson.getString("city"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void loadData(){
        initView();


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
        }
    }
}
