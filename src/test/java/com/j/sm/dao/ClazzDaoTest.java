package com.j.sm.dao;

import com.j.sm.entity.Clazz;
import com.j.sm.entity.Department;
import com.j.sm.factory.DaoFactory;
import org.junit.Test;
import org.junit.internal.Throwables;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ClazzDaoTest {

    private final ClazzDao clazzDao = DaoFactory.getClazzDaoInstance();

    @Test
    public void selectByDepartmentId() {
        List<Clazz> clazzList = null;
        try {
            clazzList = clazzDao.selectByDepartmentId(5);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(clazzList);
    }

    @Test
    public void insert() {
        int n = 0;
        Clazz clazz = Clazz.builder()
                .departmentId(6)
                .className("动漫2011")
                .build();
        try {
            n = clazzDao.insertClazz(clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(1,n);
    }

    @Test
    public void selectAll() throws SQLException {
        List<Clazz> clazzList = clazzDao.selectAll();
        try {
            clazzList = clazzDao.selectAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(clazzList);
    }
}