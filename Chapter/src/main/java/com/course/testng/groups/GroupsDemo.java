package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * Created by mgg on 2020/5/24
 */

public class GroupsDemo {

    @Test(groups = "group1")
    public void group0(){
        System.out.println("这是第一组+group0");
    }

    @Test(groups = "group1")
    public void group1(){
        System.out.println("这是第一组+group1");
    }

    @Test(groups = "group2")
    public void group3(){
        System.out.println("这是第二组+group3");
    }

    @Test(groups = "group2")
    public void group4(){
        System.out.println("这是第二组+group4");
    }

    @BeforeGroups("group1")
    public void beforeGroups(){
        System.out.println("beforeGroups");
    }

    @AfterGroups("group1")
    public void afterGroups(){
        System.out.println("afterGroups");
    }

}
