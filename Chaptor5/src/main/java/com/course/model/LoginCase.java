package com.course.model;

import lombok.Data;

/**
 * Created by mgg on 2021/7/23
 */
@Data
public class LoginCase {
    private int id;
    private String userName;
    private String password;
    private String expected;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", expected=" + expected +
                '}';
    }
}
