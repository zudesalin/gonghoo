package com.gonghoo.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.gonghoo.R;
import com.gonghoo.fragment.JianghuFragment;
import com.gonghoo.fragment.MineFragment;

/**
 * Created by zudesalin on 2016/11/15.
 */
public class DeskTopActivity extends FragmentActivity {
    Context context=DeskTopActivity.this;
    TextView  main_footer_jianghu_tv,main_footer_daxia_tv,main_footer_mine_tv;
    ImageButton main_footer_jianghu_bt,main_footer_daxia_bt,main_footer_mine_bt;

    Fragment jianghuFragment;
    FragmentManager mFragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desktop);
        jianghuFragment=new JianghuFragment(context);
        mFragmentManager=getSupportFragmentManager();
        initView();
        initFragment();
    }
    private void initView(){
        main_footer_jianghu_bt= (ImageButton) findViewById(R.id.main_footer_jianghu_bt);
        main_footer_daxia_bt= (ImageButton) findViewById(R.id.main_footer_daxia_bt);
        main_footer_mine_bt= (ImageButton) findViewById(R.id.main_footer_mine_bt);

        main_footer_jianghu_tv= (TextView) findViewById(R.id.main_footer_jianghu_tv);
        main_footer_daxia_tv= (TextView) findViewById(R.id.main_footer_daxia_tv);
        main_footer_mine_tv= (TextView) findViewById(R.id.main_footer_mine_tv);
    }
    private void initFragment(){
        fragmentTransaction=mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.deskFrameLayout,jianghuFragment);
        iconChoolsed(0);
        fragmentTransaction.commit();
    }

    private void iconChoolsed(int index){
        main_footer_jianghu_tv.setTextColor(Color.parseColor("#999999"));
        main_footer_daxia_tv.setTextColor(Color.parseColor("#999999"));
        main_footer_mine_tv.setTextColor(Color.parseColor("#999999"));

        main_footer_jianghu_bt.setImageResource(R.drawable.main_footer_jianghu);
        main_footer_daxia_bt.setImageResource(R.drawable.main_footer_daxia);
        main_footer_mine_bt.setImageResource(R.drawable.main_footer_mine);
        switch (index){
            case 0:
                main_footer_jianghu_tv.setTextColor(Color.parseColor("#FF6633"));
                main_footer_jianghu_bt.setImageResource(R.drawable.main_footer_jianghu_choosed);
                break;
            case 1:
                main_footer_daxia_tv.setTextColor(Color.parseColor("#FF6633"));
                main_footer_daxia_bt.setImageResource(R.drawable.main_footer_daxia_choosed);
                break;
            case 2:
                main_footer_mine_tv.setTextColor(Color.parseColor("#FF6633"));
                main_footer_mine_bt.setImageResource(R.drawable.main_footer_mine_choosed);
                break;
        }
    }
    /**
     * 导航点击事件
     */
    public void click(View v){

        switch (v.getId()){
            case R.id.main_footer_jianghu_layout:
                initFragment();
                iconChoolsed(0);
                break;
            case R.id.main_footer_daxia_layout:
                iconChoolsed(1);
                break;
            case R.id.main_footer_mine_layout:
                fragmentTransaction=mFragmentManager.beginTransaction();
                MineFragment mineFragment=null;
                if(mineFragment==null){
                    mineFragment=new MineFragment(context);
                }
                fragmentTransaction.add(R.id.deskFrameLayout,mineFragment);
                //fragmentTransaction.attach(mineFragment);
                iconChoolsed(2);
                fragmentTransaction.commit();
                iconChoolsed(2);
                break;
        }
    }
}
