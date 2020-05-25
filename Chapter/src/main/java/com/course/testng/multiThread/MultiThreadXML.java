package com.course.testng.multiThread;

import org.testng.annotations.Test;

/**
 * Created by mgg on 2020/5/25
 */

public class MultiThreadXML {

    @Test
    public void test1(){
        System.out.println("test1");
        System.out.printf("Thread ID : %s%n",Thread.currentThread().getId());
    }
    @Test
    public void test2(){
        System.out.println("test2");
        System.out.printf("Thread ID : %s%n",Thread.currentThread().getId());
    }

}
