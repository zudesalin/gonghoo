package com.gonghoo.utils;

import java.io.IOException;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

/**
 *
 * @Title: BasicPost.java
 * @Package com.entersoft.entercrm.util
 * @Description: 对httpPost的简单封装
 * @author lizd
 * @date 2015-9-21 下午2:09:53
 * @version V1.0
 */
public class BasicPost {
    private static DefaultHttpClient httpClient =null;
    private static HttpResponse response= null;
    private static int Timer=60000;
    public static void setTimer(int timer) {
        Timer = timer;
    }
    public static HttpResponse post(String url,List<NameValuePair> parameters) throws IOException{
        httpClient =(DefaultHttpClient) CustomHttpClient.getCustomHttpClient();
        HttpPost post=new HttpPost(url);
        //post.addHeader("User-Agent", "iPhone");
        UrlEncodedFormEntity formEntity=null;
        if(parameters!=null){
            formEntity= new UrlEncodedFormEntity(parameters);
            post.setEntity(formEntity);
        }
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, Timer); //请求超时
        response=httpClient.execute(post);
        return response;
    }
}
