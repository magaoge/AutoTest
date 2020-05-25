package com.course.testng.parameterization;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @Author: magg
 * @Date: 2020/5/25 12:25
 */
public class PramaDemo {
    @Test
    @Parameters({"name","age"})
    public void printString(String name,int age){
        System.out.println("name = "+name+"-----------"+"age = "+age);
    }
}
