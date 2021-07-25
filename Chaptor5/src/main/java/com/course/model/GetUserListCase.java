package com.course.model;

import lombok.Data;

/**
 * Created by mgg on 2021/7/23
 */
@Data
public class GetUserListCase {
    private int id;
    private String userName;
    private int age;
    private int sex;
    private String expected;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", expected=" + expected +
                '}';
    }
}
