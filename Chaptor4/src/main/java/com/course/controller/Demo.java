package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mgg on 2020/6/1
 */

@Log4j

@Api(value = "v1",description = "这是我的第一个版本的demo")
@RequestMapping("v1")
public class Demo {

    /*首先获取一个执行sql语句的对象
    @Autowired作用是指该变量在类运行的同时就加载
    我理解应该类似于静态
    * */
    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/addUserCount",method = RequestMethod.POST)
    @ApiOperation(value = "新增用户数查询",httpMethod = "POST")
    //@RequestBody作用是将注解对应的类装配到目标类
    public int addUserCount(@RequestBody User user){
        return template.insert("addUserCount",user);
    }

    @RequestMapping(value = "/delUserCount",method = RequestMethod.GET)
    @ApiOperation(value = "新增用户数查询",httpMethod = "GET")
    public int delUserCount(@RequestParam int id){
        return template.delete("delUserCount",id);
    }

    @RequestMapping(value = "/delUserCount2",method = RequestMethod.POST)
    @ApiOperation(value = "新增用户数查询",httpMethod = "POST")
    public int delUserCount2(@RequestBody User user){
        return template.delete("delUserCount2",user);
    }

    @RequestMapping(value = "/updateUserCount",method = RequestMethod.POST)
    @ApiOperation(value = "新增用户数查询",httpMethod = "POST")
    public int updateUserCount(@RequestBody User user){
        return template.update("updateUserCount",user);
    }

    @RequestMapping(value = "/getUserCount",method = RequestMethod.GET)
    @ApiOperation(value = "可以获取到用户数",httpMethod = "GET")
    public int getUserCountList(){
        return template.selectOne("getUserCount");
    }
}
