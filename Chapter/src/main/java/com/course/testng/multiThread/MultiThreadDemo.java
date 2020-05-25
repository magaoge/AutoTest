package com.course.testng.multiThread;

import org.testng.annotations.Test;

/**
 * Created by mgg on 2020/5/25
 */

public class MultiThreadDemo {

    @Test
    public void test(){
        System.out.println("wo");
        System.out.printf("Thread ID : %s%n",Thread.currentThread().getId());
    }
}
