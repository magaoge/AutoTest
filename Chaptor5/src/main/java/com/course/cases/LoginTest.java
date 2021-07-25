package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.model.User;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by mgg on 2021/7/23
 */

public class LoginTest {

    @BeforeTest(groups = "loginTrue",description = "测试准备工作，获取HttpClien对象")
    //初始化获得所有接口路径及http请求器
    public void beforeTest(){
        TestConfig.getUserInfoUri = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.addUserUri = ConfigFile.getUrl(InterfaceName.ADDUSER);
        TestConfig.getUserListUri = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.loginUri = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.updateUserInfoUri = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);

        TestConfig.defaultHttpClient = new DefaultHttpClient();
    }

    @SneakyThrows
    @Test(groups = "loginTrue",description = "用户登录成功接口")
    public void loginTrue(){
        //获取执行sql对象
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        //执行sql脚本中的sql
        LoginCase loginCase = sqlSession.selectOne("loginCase", 1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUri);

        //发请求
        String result = getResult(loginCase);
        //验证请求结果
        Assert.assertEquals(loginCase.getExpected(),result);
    }

    @SneakyThrows
    private String getResult(LoginCase loginCase) {
        HttpPost post = new HttpPost(TestConfig.loginUri);
        post.setHeader("content-type","application/json");
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();

        JSONObject param = new JSONObject();
        param.put("userName",loginCase.getUserName());
        param.put("password",loginCase.getPassword());

        System.out.println(param);

        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

        String result ;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);

        result = EntityUtils.toString(response.getEntity(),"utf-8");

        return result;
    }


//    @Test(groups = "loginFalse",description = "用户登录失败接口")
//    public void loginFalse(){
//        SqlSession sqlSession = DatabaseUtil.getSqlSession();
//        LoginCase loginCase = sqlSession.selectOne("loginCase", 2);
//        System.out.println(loginCase.toString());
//        System.out.println(TestConfig.loginUri);
//
//        //发请求
//        String result = getResult(loginCase);
//        //验证请求结果
//        Assert.assertEquals(loginCase.getExpected(),result);
//    }

}
