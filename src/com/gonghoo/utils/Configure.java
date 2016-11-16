package com.gonghoo.utils;

import android.content.Context;

import java.util.Properties;

/**
 * Created by zudesalin on 2016/11/16.
 */
public class Configure {
    public static String getPropertiesURL(Context c, String s) {
        String url = null;
        Properties properties = new Properties();
        try {
            properties.load(c.getAssets().open("property.properties"));
            url = properties.getProperty(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }
}
