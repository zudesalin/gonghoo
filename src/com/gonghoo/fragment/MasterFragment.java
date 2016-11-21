package com.gonghoo.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
public class MasterFragment extends BackHandledFragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.master, null);
        return view;
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}