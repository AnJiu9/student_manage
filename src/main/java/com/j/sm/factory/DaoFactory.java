package com.j.sm.factory;

import com.j.sm.dao.AdminDao;
import com.j.sm.dao.ClazzDao;
import com.j.sm.dao.DepartmentDao;
import com.j.sm.dao.StudentDao;
import com.j.sm.dao.impl.AdminDaoImpl;
import com.j.sm.dao.impl.DepartmentDaoImpl;
import com.j.sm.dao.impl.StudentDaoImpl;
import com.j.sm.dao.impl.clazzDaoImpl;
import com.j.sm.vo.StudentVo;

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

    /**
     * 获得DepartmentDao实例
     * @return AdminDao实例
     */
    public static DepartmentDao getDepartmentDaoInstance() {
        return new DepartmentDaoImpl();
    }

    /**
     * 获得ClazzDao实例
     *
     * @return ClazzDao实例
     */

    public static ClazzDao getClazzDaoInstance() {
        return new clazzDaoImpl();
    }

    /**
     * 获得StudentDao实例
     *
     * @return StudentDao实例
     */
    public static StudentDao getStudentDaoInstance() {
        return new StudentDaoImpl();
    }
}
