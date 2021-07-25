package com.example.services;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by mgg on 2020/5/30
 */

@RestController
//关联swagger配置文件，因为目前并未声明该类访问路径，写"/"，为该类中的所有接口组命名
@Api(value = "/",description = "这是我全部的get请求")
    public class MyGetMethod {

    @RequestMapping(value = "/getNoParamDemo/returnCookie",method = RequestMethod.GET)
    //对该接口单独描述，描述功能及请求方式
    @ApiOperation(value = "这是一个获取cookie信息的请求",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){

        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "获取到cookie信息";
    }

    @RequestMapping(value = "/getNoParamDemo/useCookie",method = RequestMethod.GET)
    @ApiOperation(value = "这是一个带有cookie信息的请求",httpMethod = "GET")
    public String useCookies(HttpServletRequest request){
        //获取cookies
        Cookie[] cookies = request.getCookies();
        //判断cookies集合是否为空,这里可以写cookies==null进行判断，但是Objects增加了判断的扩展性
        if(Objects.isNull(cookies)){
            //如果为空，返回缺少cookies信息
            return "访问失败，缺少cookie认证信息";
        }
        //不为空，遍历cookie集合中的cookies信息
        for (Cookie cookie: cookies){
            //判断cookie信息是否符合接口的要求
            if (cookie.getName().equals("login") &&
                    cookie.getValue().equals("true")){
                return "访问成功";
            }
        }
        return "无论如何必须携带cookie信息";
    }

    /**
     * 开发一个需要携带参数才能访问的get请求。
     * 第一种实现方式 url: key=value&key=value
     * 我们来模拟获取商品列表
     */

    //声明访问路径和请求方式
    @RequestMapping(value = "/getParamDemo/cookie",method = RequestMethod.GET)
    /**@RequestParam：将请求参数绑定到你控制器的方法参数上，且声明参数类型
     * @RequestParam注解默认的属性值required为true，代表必须加参数
     * 不加@RequestParam，代表可加参数或不加参数都能访问
    */
    @ApiOperation(value = "这是一个带有参数信息的请求",httpMethod = "GET")
    public Map<String,Integer> paramGet1(@RequestParam Integer start,
                                         @RequestParam Integer end){

        Map<String,Integer> map = new HashMap<>();
        map.put("盖伦",10);
        map.put("刀妹",20);
        map.put("皇子",30);

        return map;
    }

    /**
     * 第二种需要携带参数访问的get请求
     * url:ip:port/get/with/param/10/20
     */

    //声明访问路径和请求方式,注：方法中传参有多少参数，后面写多少
    @RequestMapping(value = "/getParamDemo2/cookie/{start}/{end}")
    @ApiOperation(value = "这是一个带有参数信息的请求2",httpMethod = "GET")
    //@PathVariable与@RequestParam作用相同，变的只是用户访问传参的形式
    public Map<String,Integer> paramGet2(@PathVariable Integer start,
                                         @PathVariable Integer end){

        Map<String,Integer> map = new HashMap<>();
        map.put("盖伦",10);
        map.put("刀妹",20);
        map.put("皇子",30);

        return map;
    }
}
