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

    @Override
    public int updateStudent(Student student) {
        int n = 0;
        try {
            n = studentDao.updateStudent(student);
        } catch (SQLException e) {
            System.err.println("修改学生出现异常");
        }
        return n;
    }

    @Override
    public int deleteById(String id) {
        int n = 0;
        try {
            n = studentDao.deleteById(id);
        } catch (SQLException e) {
            System.err.println("删除学生出现异常");
        }
        return n;
    }

    @Override
    public int insertStudent(Student student) {
        int n = 0;
        try {
            n = studentDao.insertStudent(student);
        } catch (SQLException e) {
            System.err.println("新增学生出现异常");
        }
        return n;
    }

    @Override
    public int countByDepartmentId(int departmentId) {
        int n = 0;
        try {
            n = studentDao.countByDepartmentId(departmentId);
        } catch (SQLException e) {
            System.err.println("根据院系统计学生数量出现异常");
        }
        return n;
    }

    @Override
    public int countStudentByClassId(int classId) {
        int n = 0;
        try {
            n = studentDao.countByClassId(classId);
        } catch (SQLException e) {
            System.err.println("根据班级统计学生数量出现异常");
        }
        return n;
    }

}
