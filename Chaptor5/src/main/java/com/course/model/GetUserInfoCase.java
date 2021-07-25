package com.course.model;

import lombok.Data;

/**
 * Created by mgg on 2021/7/23
 */
@Data
public class GetUserInfoCase {
    private int id;
    private int userId;
    private String expected;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId +
                ", expected=" + expected +
                '}';
    }
}
