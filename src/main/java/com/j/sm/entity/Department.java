package com.j.sm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Department
 * @Description 院系实体类
 * @Author orange
 * @Date 2020-11-17 21:51
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Department {
    private Integer id;
    private String departmentName;
    private String logo;
}
