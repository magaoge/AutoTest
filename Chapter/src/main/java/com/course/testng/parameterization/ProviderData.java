package com.course.testng.parameterization;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * Created by mgg on 2020/5/25
 */

public class ProviderData {

    @Test(dataProvider = "printString")
    public void printString(String name,int age){
        System.out.println("name = "+name+"-----------"+"age = "+age);
    }

    @DataProvider(name = "printString")
    public Object[][] dataProvider(){
        Object[][] value = new Object[][]{
                {"zhangsan",10},
                {"lisi",20}
        };
        return value;
    }

    @Test(dataProvider = "test")
    public void test1(String name,int age){
        System.out.println("name = "+name+"-----------"+"age = "+age);
    }

    @Test(dataProvider = "test")
    public void test2(String name,int age){
        System.out.println("name = "+name+"-----------"+"age = "+age);
    }

    @DataProvider(name = "test")
    public Object[][] dataProvider2(Method method) {
        Object[][] value = null;
        if (method.getName().equals("test1")) {
            value = new Object[][]{
                    {"zhangsan", 10},
                    {"lisi", 20}
            };
        }else if (method.getName().equals("test2")){
            value = new Object[][]{
                    {"张三", 10},
                    {"李四", 20}
            };
        }
        return value;
    }
}
