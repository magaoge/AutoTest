package com.course.config;


import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by mgg on 2021/7/23
 */

public class TestConfig {
    public static String loginUri;
    public static String updateUserInfoUri;
    public static String getUserListUri;
    public static String getUserInfoUri;
    public static String addUserUri;

    public static DefaultHttpClient defaultHttpClient;
    public static CookieStore store;
}
