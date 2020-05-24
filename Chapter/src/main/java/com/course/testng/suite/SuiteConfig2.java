package com.course.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by mgg on 2020/5/23
 */

public class SuiteConfig2 {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite2");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite2");
    }

}
