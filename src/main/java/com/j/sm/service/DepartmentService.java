package com.j.sm.service;

import com.j.sm.entity.Department;

import java.util.List;

/**
 * @ClassName DepartmentService
 * @Description TODO
 * @Author orange
 * @Date 17.11.20
 **/

public interface DepartmentService {
    /**
     * 查询所有院系
     *
     *
     * @return List<Department>
     */
    List<Department> selectAll();

    void remove(String name);

    int addDepartment(Department department);
}
