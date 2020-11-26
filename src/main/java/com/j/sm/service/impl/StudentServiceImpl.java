package com.j.sm.service.impl;

import com.j.sm.dao.DepartmentDao;
import com.j.sm.dao.StudentDao;
import com.j.sm.entity.Department;
import com.j.sm.entity.Student;
import com.j.sm.factory.DaoFactory;
import com.j.sm.service.StudentService;
import com.j.sm.vo.StudentVo;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName StudentServiceImpl
 * @Description
 * @Author orange
 * @Date 2020-11-23 18:35
 **/

public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao = DaoFactory.getStudentDaoInstance();

    @Override
    public List<StudentVo> getAll() {
        List<StudentVo> studentList = null;
        try {
            studentList = studentDao.selectAll();
        } catch (SQLException e) {
            System.err.println("查询学生信息出现异常");
        }
        return studentList;
    }

    @Override
    public List<StudentVo> getByDepId(int depId) {
        List<StudentVo> studentList = null;
        try {
            studentList = studentDao.selectByDepId(depId);
        } catch (SQLException e) {
            System.err.println("查询学生信息出现异常");
        }
        return studentList;
    }

    @Override
    public List<StudentVo> getByClassId(int classId) {
        List<StudentVo> studentList = null;
        try {
            studentList = studentDao.selectByClassId(classId);
        } catch (SQLException e) {
            System.err.println("查询学生信息出现异常");
        }
        return studentList;
    }

    @Override
    public List<StudentVo> getByKeywords(String Keywords) {
        List<StudentVo> studentList = null;
        try {
            studentList = studentDao.selectByKeywords(Keywords);
        } catch (SQLException e) {
            System.err.println("查询学生信息出现异常");
        }
        return studentList;
    }
}
