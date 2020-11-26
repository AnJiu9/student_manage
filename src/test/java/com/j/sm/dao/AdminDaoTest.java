package com.j.sm.dao;

import com.j.sm.entity.Admin;
import com.j.sm.factory.DaoFactory;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class AdminDaoTest {

    private final AdminDao adminDao = DaoFactory.getAdminDaoInstance();

    @Test
    public void findAdminByAccount() {
        Admin admin;
        try {
            admin = adminDao.findAdminByAccount("orange99@qq.com");
            assertEquals("一只橘子", admin.getAdminName());
            System.out.println(admin);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}