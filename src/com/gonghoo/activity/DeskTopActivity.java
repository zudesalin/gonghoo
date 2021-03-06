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
import com.gonghoo.fragment.BackHandledFragment;
import com.gonghoo.fragment.JianghuFragment;
import com.gonghoo.fragment.MineFragment;
import com.gonghoo.todo.BackHandledInterface;

/**
 * Created by zudesalin on 2016/11/15.
 */
public class DeskTopActivity extends FragmentActivity implements BackHandledInterface {
    Context context=DeskTopActivity.this;
    TextView  main_footer_jianghu_tv,main_footer_daxia_tv,main_footer_mine_tv;
    ImageButton main_footer_jianghu_bt,main_footer_daxia_bt,main_footer_mine_bt;

    Fragment jianghuFragment;
    FragmentManager mFragmentManager;
    FragmentTransaction fragmentTransaction;

    MineFragment mineFragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desktop);
        mFragmentManager=getSupportFragmentManager();
        initView();
        selectFragment(0);
    }
    private void initView(){
        main_footer_jianghu_bt= (ImageButton) findViewById(R.id.main_footer_jianghu_bt);
        main_footer_daxia_bt= (ImageButton) findViewById(R.id.main_footer_daxia_bt);
        main_footer_mine_bt= (ImageButton) findViewById(R.id.main_footer_mine_bt);

        main_footer_jianghu_tv= (TextView) findViewById(R.id.main_footer_jianghu_tv);
        main_footer_daxia_tv= (TextView) findViewById(R.id.main_footer_daxia_tv);
        main_footer_mine_tv= (TextView) findViewById(R.id.main_footer_mine_tv);
    }

    private void selectFragment(Integer i) {
        fragmentTransaction=mFragmentManager.beginTransaction();
        hideFragment();
        iconChoolsed(i);
        switch (i) {
            case 0:
                if (jianghuFragment == null) {
                    jianghuFragment = new JianghuFragment(context);
                    fragmentTransaction.add(R.id.deskFrameLayout, jianghuFragment, "jhFragment");
                } else {
                    fragmentTransaction.show(jianghuFragment);
                }
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentTransaction.add(R.id.deskFrameLayout, jianghuFragment, "jhFragment");
                fragmentTransaction.show(jianghuFragment);
                fragmentTransaction.commit();
                break;
            case 2:
                if (mineFragment == null) {
                    mineFragment = new MineFragment(context);
                    fragmentTransaction.add(R.id.deskFrameLayout, mineFragment, "mineFragment");
                } else {
                    fragmentTransaction.show(jianghuFragment);
                }
                fragmentTransaction.commit();
                break;

        }


    }

    private void hideFragment() {
        if (jianghuFragment != null) {
            fragmentTransaction.hide(jianghuFragment);
        }
        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }

    }
    public void iconChoolsed(int index){
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
                selectFragment(0);
                break;
            case R.id.main_footer_daxia_layout:
                iconChoolsed(1);
                break;
            case R.id.main_footer_mine_layout:
                selectFragment(2);
                break;
        }
    }

    private BackHandledFragment mBackHandedFragment;
    private boolean hadIntercept;

    @Override
    public void setSelectedFragment(BackHandledFragment selectedFragment) {
        this.mBackHandedFragment = selectedFragment;
    }

    @Override
    public void onBackPressed() {
        if(mBackHandedFragment == null || !mBackHandedFragment.onBackPressed()){
            if(getSupportFragmentManager().getBackStackEntryCount() == 0){
                super.onBackPressed();
            }else{
                getSupportFragmentManager().popBackStack();
            }
        }
    }
}
