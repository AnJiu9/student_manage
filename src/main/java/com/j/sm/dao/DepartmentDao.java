package com.j.sm.dao;

import com.j.sm.entity.Department;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName DepartmentDao
 * @Description 院系DAO接口
 * @Author orange
 * @Date 2020-11-17 22:05
 **/

public interface DepartmentDao {
    /**
     * 查询所有院系
     *
     * @return List<Department>
     * @throws SQLException 异常
     */
    List<Department> getAll() throws SQLException;
}
