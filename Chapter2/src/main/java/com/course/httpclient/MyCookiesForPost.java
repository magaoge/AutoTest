package com.course.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by mgg on 2020/5/29
 */

public class MyCookiesForPost {

    private String url;
    private ResourceBundle bundle;
    private CookieStore store;
    String result;


    @BeforeTest
    public void beforeTest() {
        bundle = ResourceBundle.getBundle("application", Locale.CANADA);
        url = bundle.getString("test.url");
    }

    @Test
    public void getCookies() throws IOException {
        HttpGet getUrl = new HttpGet(url + bundle.getString("returnCookie.uri"));
        DefaultHttpClient client = new DefaultHttpClient();

        HttpResponse response = client.execute(getUrl);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "utf-8");
        System.out.println(result);

        this.store = client.getCookieStore();
        List<Cookie> cookies = store.getCookies();

        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + ":" + cookie.getValue());
        }
    }


    @Test(dependsOnMethods = "getCookies")
    public void useCookieForPost() throws IOException {
        //生成接口访问地址
        String url =this.url+ bundle.getString("useCookie.get.uri");
        //生成客户端
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //设置cookie信息
        httpClient.setCookieStore(this.store);

        //生成HttpPost请求
        HttpPost httpPost = new HttpPost(url);
        //设置信息头
        httpPost.setHeader("content-type","application/json");

        //封装参数
        JSONObject params = new JSONObject();
        params.put("name","huhansan");
        params.put("age","18");
        //将参数放入HttpPost
        StringEntity entity = new StringEntity(params.toString(),"utf-8");
        httpPost.setEntity(entity);

        //发送请求，获取响应值
        HttpResponse response = httpClient.execute(httpPost);
        //获取响应体
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        //打印结果
        System.out.println(result);

        //将获取的结果转化为json格式
        JSONObject resultJson = new JSONObject(result);
        //做断言
        String huhansan =(String) resultJson.get("huhansan");
        Assert.assertEquals(huhansan,"success");

        System.out.println("预期结果是：success");
        System.out.println("实际结果是："+huhansan);

        String status =(String) resultJson.get("status");
        Assert.assertEquals(status,"1");

        System.out.println("预期结果是：1");
        System.out.println("实际结果是："+status);
    }
}
