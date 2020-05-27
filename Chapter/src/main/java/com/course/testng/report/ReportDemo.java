package com.course.testng.report;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * @Author: magg
 * @Date: 2020/5/26 15:53
 */
public class ReportDemo {

    @Test
    public void test1(){
        Assert.assertEquals(1,1);
    }

    @Test
    public void test2(){
        Assert.assertEquals(1,2);
    }

    @Test
    public void logDemo(){
        Reporter.log("这是我的日志");
        throw new RuntimeException("这是我们自己的运行时异常");
    }
}
