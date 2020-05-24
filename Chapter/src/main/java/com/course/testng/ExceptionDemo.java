package com.course.testng;

import org.testng.annotations.Test;

/**
 * Created by mgg on 2020/5/24
 */

public class ExceptionDemo {

//    @Test(expectedExceptions = RuntimeException.class)
//    public void defult(){
//        System.out.println("没有异常");
//    }

    @Test(expectedExceptions = RuntimeException.class)
    public void success(){
        System.out.println("有异常");
        throw new RuntimeException();
    }
}
