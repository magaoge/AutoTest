package com.course.testng.suite;

import org.testng.annotations.Test;

/**
 * Created by mgg on 2020/5/23
 */

public class PayTest {
    @Test
    public void pay(){
        System.out.println("支付成功");
    }

    public void pay2(){
        System.out.println("支付失败");
    }
}
