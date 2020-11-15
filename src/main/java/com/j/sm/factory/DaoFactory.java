package com.j.sm.factory;

import com.j.sm.dao.AdminDao;
import com.j.sm.dao.impl.AdminDaoImpl;

/**
 * @ClassName DaoFactory
 * @Description 工厂类
 * @Author orange
 * @Date 2020-11-14 22:42
 **/

public class DaoFactory {
    /**
     * 获得AdminDao实例
     *
     * @return AdminDao实例
     */
    public static AdminDao getAdminDaoInstance() {
        return new AdminDaoImpl();
    }
}