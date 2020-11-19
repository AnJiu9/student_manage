package com.j.sm.dao;

import com.j.sm.entity.Department;

import java.sql.SQLException;

/**
 * @ClassName InsertDepartmentDao
 * @Description TODO
 * @Author orange
 * @Date 19.11.20
 **/

public interface InsertDepartmentDao {
    /**
     * 新增院系
     *
     * @param department 入参
     * @return int
     * @throws SQLException 异常
     */
    int insertDepartment(Department department) throws SQLException;
}
