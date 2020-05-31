package com.example.bean;

import lombok.Data;

/**
 * Created by mgg on 2020/5/31
 */

//省去写get、set、toString方法
@Data
public class User {
    private String userName;
    private String passWord;

    private String name;
    private String age;
    private String sex;

}
