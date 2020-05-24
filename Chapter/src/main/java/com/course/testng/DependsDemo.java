package com.course.testng;

import org.testng.annotations.Test;

/**
 * Created by mgg on 2020/5/24
 */

public class DependsDemo {

    @Test(expectedExceptions = RuntimeException.class)
    public void test1(){
        System.out.println("test1");
        throw new RuntimeException();
    }

    @Test(dependsOnMethods = "test1")
    public void test2(){
        System.out.println("test2");
    }

}
