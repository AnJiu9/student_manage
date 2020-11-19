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

    /**
     * 删除
     */
    void remove(String name)throws SQLException;

    /**
     * 添加
     * @param department
     * @return
     * @throws SQLException
     */

    int insertDepartment(Department department) throws SQLException;
}
