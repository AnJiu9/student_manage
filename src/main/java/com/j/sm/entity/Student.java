package com.j.sm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.PrintWriter;
import java.util.Date;

/**
 * @ClassName Student
 * @Description 学生实体类
 * @Author orange
 * @Date 2020-11-23 18:09
 **/


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private String id;
    private Integer classId;
    private String studentName;
    private String phone;
    private Short gender;
    private Date birthday;
    private String avatar;
    private String address;
}
