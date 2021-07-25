package com.course.model;

import lombok.Data;

/**
 * Created by mgg on 2021/7/23
 */
@Data
public class UpdateUserInfoCase {
    private int id;
    private int userId;
    private String userName;
    private String password;
    private int age;
    private int sex;
    private int permission;
    private int isDelete;
    private String expected;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", permission=" + permission +
                ", isDelete=" + isDelete +
                ", expected=" + expected +
                '}';
    }
}
