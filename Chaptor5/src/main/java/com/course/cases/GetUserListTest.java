package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.GetUserListCase;
import com.course.model.LoginCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
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

import java.util.List;

/**
 * Created by mgg on 2021/7/23
 */

public class GetUserListTest {
    @Test(dependsOnGroups = "loginTrue",description = "获取用户列表接口")
    public void getUserListCase(){
        //获取执行sql对象
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        //执行sql脚本中的sql
        GetUserListCase getUserListCase = sqlSession.selectOne("getUserListCase", 1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUri);
        //获取请求结果
        JSONArray resultJson = getJsonResult(getUserListCase);
        //验证(getUserListCase.getExpected()==》getUserList)
        List<User> users = sqlSession.selectList(getUserListCase.getExpected(),getUserListCase);

        JSONArray userListJson = new JSONArray(users);
        Assert.assertEquals(userListJson.length(),resultJson.length());

        for (int i = 0; i <resultJson.length() ; i++) {
            JSONObject expect = (JSONObject) resultJson.get(i);
            JSONObject actual = (JSONObject) userListJson.get(i);
            Assert.assertEquals(expect.toString(),actual.toString());
        }


    }

    @SneakyThrows
    private JSONArray getJsonResult(GetUserListCase getUserListCase) {
        HttpPost post = new HttpPost(TestConfig.getUserListUri);
        post.setHeader("content-type","application/json");
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();


        JSONObject param = new JSONObject();
        param.put("userName",getUserListCase.getUserName());
        param.put("age",getUserListCase.getAge());
        param.put("sex",getUserListCase.getSex());
        param.put("expected",getUserListCase.getExpected());

        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);

        result = EntityUtils.toString(response.getEntity(),"utf-8");
        JSONArray jsonArray = new JSONArray(result);
        return jsonArray;
    }
}