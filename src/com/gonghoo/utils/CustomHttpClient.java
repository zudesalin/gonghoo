package com.gonghoo.utils;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;


public class CustomHttpClient{
	/**
	 * @Title: CustomHttpClient.java
	 * @Package com.entersoft.entercrm.util
	 * @Description: TODO 使用线程安全 共享一个httpclient对象
	 * @author A18ccms A18ccms_gmail_com
	 * @date 2015-9-22 上午8:35:28
	 * @version V1.0
	 */
	private static HttpClient client = null;  //应用共享的对象

	/* 采用private的构造器，禁止了其他类通过CustomHttpClient xx = new CustomHttpClient();这种方式创建对象，确保对象的唯一性 */
	private CustomHttpClient(){
	}
	/* 通过静态调用获取对象，第一次调用为空时进行创建 */
	public static synchronized HttpClient getCustomHttpClient(){
		if(client == null){
	           /*如果对象为空，创建之*/

			HttpParams params = new BasicHttpParams();
	        	/* 设置HttpParam是的基本参数，其实都是对应http请求的消息头。其中三个都很好理解，重点介绍一些setUserExpectContinue。 一般都设置为flase，设置为true通常是传递request消息很大（例携带大文件），而服务器可能需要认证，我们不希望传完这个大文件，才收到服务器的拒绝。HTTP是TCP流方式，当server收到请求的头字段是Except：100-continue， 不在等待整个请求，返回100 continue应答继续读取，或者给出拒绝请求（final Status code，如4xx）。 具体可以参考：http://www.w3.org/Protocols/rfc2616/rfc2616-sec8.html#sec8.2.3 */
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.DEFAULT_CONTENT_CHARSET);
			HttpProtocolParams.setUseExpectContinue(params, true);
			HttpProtocolParams.setUserAgent(params, "Mozilla/5.0 (Linux; U; Android 2.2.1; en-us; Nexus One Build/FRG83)" +
					" AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
	        	/* 设置超时时间。超时的异常均属于IOException，此外ClientProtocolException也是与IOException*/
			// 从ClientConnectionManager获取连接的时间，这是从连接池中获取连接的超时设置，只有在连接池所有连接都在使用的情况下才可能出现超时。超时会扔出ConnectionPoolTimeoutException。一个HttpClient对应管理器，有连接池，里面有多个连接（socket），这是我对其架构的猜测。
			ConnManagerParams.setTimeout(params, 1000);
			// 这是连接到远端web server的超时设置，超时会扔出ConnectTimeoutException
			HttpConnectionParams.setConnectionTimeout(params, 5000);//连接超时
			// 这是发送请求消息后，最多等待多长时间得到响应的设置，超时会扔出SocketTimeoutException
			HttpConnectionParams.setSoTimeout(params, 10000);//socket超时
			//【2.2】设置Sheme，注册了http和https
			SchemeRegistry schReg = new SchemeRegistry();
			schReg.register(new Scheme("http",PlainSocketFactory.getSocketFactory(), 80));
			schReg.register(new Scheme("https",PlainSocketFactory.getSocketFactory(), 443));

			//【2】ClientConnectionManager用于管理HTTP连接，我们使用同一个client来处理请求，要确保多线程的使用安全，采用ThreadSafeClientConnManager，是线程安全的连接池。如果多个线程同时请求，或有延迟情况。
			ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params,schReg);
			//【1】以ThreadSafeClientConnManager为管理器参数，创建可进行多线程调用的同步保护的HttpClient对象
			client = new DefaultHttpClient(conMgr,params);

		}
		return client;
	}
	/*禁止clone，同样也是保证对象的唯一性*/
	public Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}
}
