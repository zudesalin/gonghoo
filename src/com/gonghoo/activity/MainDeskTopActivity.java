package com.gonghoo.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.gonghoo.R;
import com.gonghoo.fragment.BackHandledFragment;
import com.gonghoo.fragment.JianghuFragment;
import com.gonghoo.fragment.MasterFragment;
import com.gonghoo.fragment.MineFragment;
import com.gonghoo.todo.BackHandledInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zudesalin on 2016/11/20.
 */
public class MainDeskTopActivity extends FragmentActivity implements BackHandledInterface {
    private ViewPager mViewPager;
    Context context;
    TextView main_footer_jianghu_tv, main_footer_daxia_tv, main_footer_mine_tv;
    ImageButton main_footer_jianghu_bt, main_footer_daxia_bt, main_footer_mine_bt;
    List<Fragment> fragments = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desktop);
        context = MainDeskTopActivity.this;
        initPage();
        initView();
    }

    private void initPage() {
        fragments = new ArrayList<Fragment>();
        JianghuFragment jianghuFragment = new JianghuFragment(context);
        MineFragment mineFragment = new MineFragment(context);
        MasterFragment masterFragment = new MasterFragment();
        fragments.add(jianghuFragment);
        fragments.add(masterFragment);
        fragments.add(mineFragment);
    }

    private void initView() {
        main_footer_jianghu_bt = (ImageButton) findViewById(R.id.main_footer_jianghu_bt);
        main_footer_daxia_bt = (ImageButton) findViewById(R.id.main_footer_daxia_bt);
        main_footer_mine_bt = (ImageButton) findViewById(R.id.main_footer_mine_bt);

        main_footer_jianghu_tv = (TextView) findViewById(R.id.main_footer_jianghu_tv);
        main_footer_daxia_tv = (TextView) findViewById(R.id.main_footer_daxia_tv);
        main_footer_mine_tv = (TextView) findViewById(R.id.main_footer_mine_tv);
        mViewPager = (ViewPager) findViewById(R.id.deskFrameLayout);
        iconChoolsed(0);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                iconChoolsed(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        FragmentManager mFragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(mFragmentManager) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }

    public void click(View v) {
        switch (v.getId()) {
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

    public void iconChoolsed(int index) {
        main_footer_jianghu_tv.setTextColor(Color.parseColor("#999999"));
        main_footer_daxia_tv.setTextColor(Color.parseColor("#999999"));
        main_footer_mine_tv.setTextColor(Color.parseColor("#999999"));

        main_footer_jianghu_bt.setImageResource(R.drawable.main_footer_jianghu);
        main_footer_daxia_bt.setImageResource(R.drawable.main_footer_daxia);
        main_footer_mine_bt.setImageResource(R.drawable.main_footer_mine);
        switch (index) {
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

    private void selectFragment(Integer i) {
        iconChoolsed(i);
        mViewPager.setCurrentItem(i);
    }

    @Override
    public void setSelectedFragment(BackHandledFragment selectedFragment) {

    }
}
