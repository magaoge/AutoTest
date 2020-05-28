package com.course.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @Author: magg
 * @Date: 2020/5/28 12:34
 */
public class HttpClientDemo {
    
    @Test
    public void test() throws IOException {
        String result;
        String url ="https://www.baidu.com";
        HttpGet httpGet = new HttpGet(url);
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse execute = client.execute(httpGet);
        HttpEntity entity = execute.getEntity();
        result = EntityUtils.toString(entity,"utf-8");
        System.out.println(result);
    }
}
