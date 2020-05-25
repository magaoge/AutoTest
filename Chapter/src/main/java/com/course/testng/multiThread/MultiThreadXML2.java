package com.course.testng.multiThread;

import org.testng.annotations.Test;

/**
 * Created by mgg on 2020/5/25
 */

public class MultiThreadXML2 {

    @Test
    public void test3(){
        System.out.println("test3");
        System.out.printf("Thread ID : %s%n",Thread.currentThread().getId());
    }


}
