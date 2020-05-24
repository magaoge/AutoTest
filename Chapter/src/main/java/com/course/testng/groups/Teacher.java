package com.course.testng.groups;

import org.testng.annotations.Test;

/**
 * Created by mgg on 2020/5/24
 */


@Test(groups = "teacher")
public class Teacher {

    public void teacher(){
        System.out.println("我是老师");
    }
}
