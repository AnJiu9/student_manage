package com.j.sm.service.impl;

import com.j.sm.dao.ClazzDao;
import com.j.sm.entity.Clazz;
import com.j.sm.factory.DaoFactory;
import com.j.sm.service.ClazzService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ClazzServiceImpl
 * @Description
 * @Author orange
 * @Date 2020-11-20 22:20
 **/

public class ClazzServiceImpl implements ClazzService {

    private final ClazzDao clazzDao = DaoFactory.getClazzDaoInstance();

    @Override
    public List<Clazz> getClazzByDepId(int departmentId) {
        List<Clazz> clazzList = null;
        try {
            clazzList = clazzDao.selectByDepartmentId(departmentId);
        } catch (SQLException throwables) {
            System.out.println("根据学院ID查询班级信息出错");
        }
        return clazzList;
    }

    @Override
    public int addClazz(Clazz clazz) {
        int n = 0;
        try {
            n = DaoFactory.getClazzDaoInstance().insertClazz(clazz);
        } catch (SQLException throwables) {
            System.err.println("新增班级出现异常");
        }
        return n;
    }

    @Override
    public int deleteClazz(Integer ID) {
        int n = 0;
        try {
            n = clazzDao.removeClazz(ID);
        } catch (SQLException e) {
            System.err.println("删除错误");
        }
        return n;
    }

    @Override
    public List<Clazz> getAll() {
        List<Clazz> clazzList = null;
        try {
            clazzList = clazzDao.selectAll();
        } catch (SQLException throwables) {
            System.out.println("查询所有班级错误");
        }
        return clazzList;
    }



}
