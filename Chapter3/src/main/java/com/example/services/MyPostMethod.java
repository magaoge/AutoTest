package com.example.services;

import com.example.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mgg on 2020/5/31
 */

//一个方法返回值以指定格式写入响应内容的控制器注解
@RestController
//@Api用在类上，描述接口组的说明
@Api(value = "/v1",description = "这是一个post请求分组")
//为该组的所有方法访问路径前都加上一个路径值
@RequestMapping("/v1")
public class MyPostMethod {

    //使用这个包下的import javax.servlet.http.Cookie;声明一个cookie
    public static Cookie cookie;

    //声明方法访问路径和请求方式
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    //@ApiOperation用于方法上，描述接口的作用和请求方式
    @ApiOperation(value = "登陆成功后，返回cookie信息",httpMethod ="POST" )

    /**
     * 1.HttpServletResponse声明接口返回参数
     * 2.@RequestParam，声明接口访问所需的参数
     * 注：①value值是与参数字段做映射的
     *      例：如果两个value值都对应userName，那么最终都指向参数字段userName
     *     ②required注明该参数是否必填，默认值为true
     * */
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName",required = true) String userName,
                        @RequestParam(value = "password",required = true) String password){

        //判断传参与正确的参数值是否相等，相等的话返回cookie信息
        if (userName.equals("zhangsan") && password.equals("123456")){
            //将cookie信息添加至返回信息，响应头中
            cookie =new Cookie("login","true");
            response.addCookie(cookie);

            //响应体内容
            return "登陆成功";
        }
        return "用户名或密码错误";
    }

    //声明方法访问路径和请求方式
    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    //@ApiOperation用于方法上，描述接口的作用和请求方式
    @ApiOperation(value = "通过获得cookie信息，请求接口",httpMethod = "POST")

    /**
     * 1.HttpServletRequest声明接口请求参数
     * 2.@RequestBody，声明接口访问所需的参数
     * 注：@RequestBody默认请求参数为json格式，请求时请求头要写"content-type: application/json"
     * */
    public String userList(HttpServletRequest request,
                           @RequestBody User user){

        //封装cookie信息
        Cookie[] cookies = request.getCookies();
        //声明返回的信息变量
        User userList;

        for (Cookie cookie : cookies){
            //对cookie和传参进行判断，参数符合要求则返回信息
            if(cookie.getName().equals("login")
                    && cookie.getValue().equals("true")
                    && user.getUserName().equals("zhangsan")
                    && user.getPassWord().equals("123456")){

                userList = new User();
                userList.setName("张三");
                userList.setAge("18");
                userList.setSex("男");

                return userList.toString();
            }
        }
        return "参数错误，无法访问！";
    }

}
