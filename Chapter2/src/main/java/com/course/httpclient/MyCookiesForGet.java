package com.course.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
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

/**
 * Created by mgg on 2020/5/28
 */

public class MyCookiesForGet {

    private String url;
    private ResourceBundle bundle;
    private CookieStore store;
    String result;


    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CANADA);
        url = bundle.getString("test.url");
    }

    @Test
    public void getCookies() throws IOException {
        HttpGet getUrl = new HttpGet(url+bundle.getString("returnCookie.uri"));
        DefaultHttpClient client = new  DefaultHttpClient();

        HttpResponse response = client.execute(getUrl);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "utf-8");
        System.out.println(result);

        this.store = client.getCookieStore();
        List<Cookie> cookies = store.getCookies();

        for (Cookie cookie: cookies){
            System.out.println(cookie.getName()+":"+cookie.getValue());
        }
    }

    @Test(dependsOnMethods = "getCookies")
    public void useCookie() throws IOException {
        HttpGet getUrl = new HttpGet(url+bundle.getString("useCookie.uri"));
        DefaultHttpClient client = new  DefaultHttpClient();

        client.setCookieStore(store);
        HttpResponse response = client.execute(getUrl);
        HttpEntity entity = response.getEntity();
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);

        if (statusCode == 200){
            result = EntityUtils.toString(entity, "utf-8");
            System.out.println(result);
        }
    }

}
