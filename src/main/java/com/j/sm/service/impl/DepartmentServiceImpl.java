package com.j.sm.service.impl;

import com.j.sm.dao.DepartmentDao;
import com.j.sm.entity.Department;
import com.j.sm.factory.DaoFactory;
import com.j.sm.service.DepartmentService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName DepartmentServiceImpl
 * @Description
 * @Author orange
 * @Date 2020-11-17 22:53
 **/

public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentDao departmentDao = DaoFactory.getDepartmentDaoInstance();


    @Override
    public List<Department> selectAll() {
        List<Department> departmentList = null;
        try {
            departmentList = departmentDao.getAll();
        } catch (SQLException e) {
            System.err.println("查询院系信息出现异常");
        }
        return departmentList;
    }

    @Override
    public void remove(String name) {
        try {
            departmentDao.remove(name);
        } catch (SQLException e) {
            System.err.println("删除错误");
        }
    }

    @Override
    public int addDepartment(Department department) {
        int n = 0;
        try {
            n = departmentDao.insertDepartment(department);
        } catch (SQLException e) {
            System.err.println("新增院系信息出现异常");
        }
        return n;
    }
}
