package com.course.testng;

import org.testng.annotations.Test;

/**
 * Created by mgg on 2020/5/24
 */

@Test(enabled = false)
public class IgnoreTest {


    public static void main(String[] args) {
        enableTrue();
    }

    @Test(enabled = true)
    public static void enableTrue(){
        System.out.println("true");
    }

    @Test(enabled = false)
    public void enableFalse(){
        System.out.println("false");
    }
}
