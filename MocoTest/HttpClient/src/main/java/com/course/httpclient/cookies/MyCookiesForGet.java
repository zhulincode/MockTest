package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {
    private String url;
    private ResourceBundle bundle;  //读取配置文件
    //用来存储cookie信息的变量
    private CookieStore store;
    private String result;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }
    @Test
    public void testGetCookies() throws IOException {
        String result;
        String url = bundle.getString("getCookies.url");

        HttpGet get = new HttpGet(this.url);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //获取cookies信息
        this.store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();

        for (Cookie cookie : cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.printf("cookie name = " + name
                    + "; cookie value = " + value);
        }
   }
   @Test(dependsOnMethods = "testgetwithcookies")
   public void testGetWithCookies() throws IOException {
        String uri = bundle.getString("test.get.with.cookies");
        String testurl = this.url+uri;
        HttpGet get = new HttpGet(testurl);
        DefaultHttpClient client = new DefaultHttpClient();

        //设置cookies信息
       client.setCookieStore(this.store);
       HttpResponse response = client.execute(get);

       //获取响应的状态吗
       int statuCode = response.getStatusLine().getStatusCode();
       System.out.printf("statusCode = "+statuCode);
       if (statuCode == 200){
           result = EntityUtils.toString(response.getEntity(),"utf-8");
           System.out.println(result);
       }
   }
}
