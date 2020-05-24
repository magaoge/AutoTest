package com.course.testng;

import org.testng.annotations.Test;

/**
 * Created by mgg on 2020/5/24
 */

public class TimeOutDemo {

    @Test(timeOut = 3000)
    public void success() throws InterruptedException {
            Thread.sleep(2000);
    }

    @Test(timeOut = 2000)
    public void defult() throws InterruptedException {
        Thread.sleep(3000);
    }



}
