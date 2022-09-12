package com.suo.pagetest.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private int id;
    private String sn;
    private String username;
    private String password;
    private int clazzId;
    private String sex = "男";
    private String mobile;
    private String qq;
    private String photo;


}
