package com.j.sm.dao;

import com.j.sm.entity.Department;
import com.j.sm.factory.DaoFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.*;

public class DepartmentDaoTest {

    private final DepartmentDao departmentDao = DaoFactory.getDepartmentDaoInstance();

    @Test
    public void getAll() {
        List<Department> departmentList = null;
        try {
            departmentList = departmentDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert departmentList != null;
        departmentList.forEach(System.out::println);
    }

    @Test
    public void insert() {
        int n = 0;
        Department department = Department.builder()
                .departmentName("新增测试院系")
                .logo("t.jpg")
                .build();
        try {
            n = departmentDao.insertDepartment(department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(1,n);
    }

    @Test
    public void delete() {
        int n = 0;
        try {
            n = departmentDao.remove(9);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(1, n);
    }

}