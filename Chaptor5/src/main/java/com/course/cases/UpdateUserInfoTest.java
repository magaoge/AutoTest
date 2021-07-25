package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.LoginCase;
import com.course.model.UpdateUserInfoCase;
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

/**
 * Created by mgg on 2021/7/23
 */

public class UpdateUserInfoTest {
    @SneakyThrows
    @Test(dependsOnGroups = "loginTrue",description = "更新用户信息接口")
    public void updateUserInfoCase(){
        //获取执行sql对象
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        //执行sql脚本中的sql
        UpdateUserInfoCase updateUserInfoCase = sqlSession.selectOne("updateUserInfoCase", 1);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUri);

        //获取结果
        int result = getResult(updateUserInfoCase);
        Thread.sleep(3000);
        //比对结果（updateUserInfoCase.getExpected()==》updateUserInfo）
        User user = sqlSession.selectOne(updateUserInfoCase.getExpected(), updateUserInfoCase);
        Assert.assertNotNull(result);
        Assert.assertNotNull(user);
    }

    @SneakyThrows
    private int getResult(UpdateUserInfoCase updateUserInfoCase) {
        System.out.println("有没有进行接口的调用");

        HttpPost httpPost = new HttpPost(TestConfig.updateUserInfoUri);
        httpPost.setHeader("content-type","application/json");
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();

        JSONObject param = new JSONObject();
        param.put("id", updateUserInfoCase.getUserId());
        param.put("userName", updateUserInfoCase.getUserName());
        param.put("age", updateUserInfoCase.getAge());
        param.put("sex", updateUserInfoCase.getSex());
        param.put("permission", updateUserInfoCase.getPermission());
        param.put("isDelete", updateUserInfoCase.getIsDelete());

        System.out.println(param);
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);

        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(httpPost);
        result = EntityUtils.toString(response.getEntity(),"utf-8");

        System.out.println("result的值等于========="+result);
        return Integer.parseInt(result);
    }

    @SneakyThrows
    @Test(dependsOnGroups = "loginTrue",description = "删除用户接口")
    public void deleteUserCase(){
        //获取执行sql对象
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        //执行sql脚本中的sql
        UpdateUserInfoCase deleteUserCase = sqlSession.selectOne("updateUserInfoCase", 1);
        System.out.println(deleteUserCase.toString());
        System.out.println(TestConfig.updateUserInfoUri);

        int result = getResult(deleteUserCase);

        Thread.sleep(3000);
        //比对结果
        User user = sqlSession.selectOne(deleteUserCase.getExpected(), deleteUserCase);

        Assert.assertNotNull(result);
        Assert.assertNotNull(user);
    }
}
