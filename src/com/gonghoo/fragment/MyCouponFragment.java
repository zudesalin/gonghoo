package com.gonghoo.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.gonghoo.R;
import com.gonghoo.view.ListViewFroScrollView;

/**
 * Created by zudesalin on 2016/11/16.
 */
@SuppressLint("ValidFragment")
public class MyCouponFragment extends Fragment {
    private View view=null;
    private Context context;
    ListViewFroScrollView listViewFroScrollView;
    public MyCouponFragment(Context context){
        this.context=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.mycoupon,container,false);
        listViewFroScrollView= (ListViewFroScrollView) view.findViewById(R.id.myCouponListView);
        listViewFroScrollView.setAdapter(new CustomerAdapter());
        return view;
    }

    /**
     * 适配器
     */
    class CustomerAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object getItem(int position) {
            return position;
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
            mycoupon_money_tv.setText("10");
            mycoupon_limit_tv.setText("满10元使用");
            mycoupon_type_tv.setText("咖啡券");
            mycoupon_date_tv.setText("有效期 2016.01.5");
            return convertView;
        }
    }
}
