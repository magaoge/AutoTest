package com.course.testng.groups;

import org.testng.annotations.Test;

/**
 * Created by mgg on 2020/5/24
 */

@Test(groups = "students")
public class Students1 {

    public void students1(){
        System.out.println("我是学生1");
    }
}
