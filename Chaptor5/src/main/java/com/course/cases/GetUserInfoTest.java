package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.LoginCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import com.mongodb.util.JSON;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by mgg on 2021/7/23
 */

public class GetUserInfoTest {
    @Test(dependsOnGroups = "loginTrue",description = "获取用户信息接口")
    public void getUserInfoCase(){
        //获取执行sql对象
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        //执行sql脚本中的sql
        GetUserInfoCase getUserInfoCase = sqlSession.selectOne("getUserInfoCase", 1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getUserInfoUri);

        //获取请求结果
        JSONArray resultJson = getJsonResult(getUserInfoCase);
        //验证(getUserInfoCase.getExpected()===》getUserInfo)
        User users = sqlSession.selectOne(getUserInfoCase.getExpected(),getUserInfoCase);

        List userList = new ArrayList();
        userList.add(users);

        JSONArray jsonArray = new JSONArray(userList);

        Assert.assertEquals(jsonArray.toString(),resultJson.getString(0));


//        Map<String,String> Expected =(Map<String, String>) JSON.parse(jsonArray.get(0).toString());
//        Map<String,String> Actual =(Map<String, String>) JSON.parse(resultJson.getString(0));
//
//        Assert.assertEquals(Expected,Actual);


    }

    @SneakyThrows
    private JSONArray getJsonResult(GetUserInfoCase getUserInfoCase) {
        HttpPost post = new HttpPost(TestConfig.getUserInfoUri);
        post.setHeader("content-type","application/json");
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();


        JSONObject param = new JSONObject();
        param.put("id",getUserInfoCase.getId());

        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);

        result = EntityUtils.toString(response.getEntity(),"utf-8");
        List resultList = Arrays.asList(result);
        JSONArray jsonArray = new JSONArray(resultList);
        return jsonArray;
    }
}
