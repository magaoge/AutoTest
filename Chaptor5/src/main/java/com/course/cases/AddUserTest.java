package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.model.LoginCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by mgg on 2021/7/23
 */

public class AddUserTest {
    @SneakyThrows
    @Test(dependsOnGroups = "loginTrue",description = "用户添加接口")
    public void addUserCase(){
        //获取执行sql对象
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        //执行sql脚本中的sql
        AddUserCase addUserCase = sqlSession.selectOne("addUserCase", 1);
        System.out.println(addUserCase.toString());
        System.out.println(TestConfig.addUserUri);

        Thread.sleep(3000);
        //发请求
        String result = getResult(addUserCase);
        System.out.println(result);
        /*这里因为getResult(addUserCase);是真正调用接口插入
        数据的方法，但是在另一个程序中，而sqlSession.selectOne("addUser", addUserCase);会按照代码顺序
        查询getResult(addUserCase);插入的数据
        所以为了避免sqlSession.selectOne("addUser", addUserCase);查询不到结果，这里等待一下
        // */
        Thread.sleep(3000);
        //验证返回结果
        User user = sqlSession.selectOne("addUser", addUserCase);
        System.out.println(user.toString());
        Assert.assertEquals(addUserCase.getExpected(),result);

    }

    @SneakyThrows
    private String getResult(AddUserCase addUserCase) {
        HttpPost post = new HttpPost(TestConfig.addUserUri);

        post.setHeader("content-type","application/json");

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        JSONObject param = new JSONObject();
        param.put("userName",addUserCase.getUserName());
        param.put("password",addUserCase.getPassword());
        param.put("age",addUserCase.getAge());
        param.put("sex",addUserCase.getSex());
        param.put("permission",addUserCase.getPermission());
        param.put("isDelete",addUserCase.getIsDelete());
        param.put("expected",addUserCase.getExpected());

        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);


        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);

        result = EntityUtils.toString(response.getEntity(),"utf-8");

        return result;
    }
}
