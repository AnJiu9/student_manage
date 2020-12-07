package com.j.sm.dao;

import com.j.sm.entity.Clazz;
import com.j.sm.entity.Student;
import com.j.sm.factory.DaoFactory;
import com.j.sm.utils.FormatUtil;
import com.j.sm.vo.StudentVo;
import org.junit.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class StudentDaoTest {

    private final StudentDao studentDao = DaoFactory.getStudentDaoInstance();

    @Test
    public void selectAll() throws SQLException {
        List<StudentVo> list = DaoFactory.getStudentDaoInstance().selectAll();
        list.forEach(System.out::println);
    }

    @Test
    public void selectByDepId() throws SQLException {
        List<StudentVo> list = DaoFactory.getStudentDaoInstance().selectByDepId(5);
        list.forEach(System.out::println);
    }

    @Test
    public void selectByClassId() throws SQLException {
        List<StudentVo> list = DaoFactory.getStudentDaoInstance().selectByClassId(1);
        list.forEach(System.out::println);
    }

    @Test
    public void selectByKeywords() throws SQLException {
        List<StudentVo> list = DaoFactory.getStudentDaoInstance().selectByKeywords("刘");
        list.forEach(System.out::println);
    }

    @Test
    public void insert() throws SQLException {
        Student student = new Student();
        student.setId("12");
        student.setClassId(18);
        student.setStudentName("嘉弈");
        student.setAvatar("https://student-management-img.oss-cn-hangzhou.aliyuncs.com/logo/20201123180459.JPG");
        student.setBirthday(new Date());
        student.setGender((short) 1);
        student.setPhone("13899991111");
        student.setAddress("四川省成都市");
        int n = studentDao.insertStudent(student);
        assertEquals(1, n);
    }

    @Test
    public void updateStudent() throws SQLException {
        Student student = new Student();
        student.setId("12");
        student.setAddress("浙江省温州市");
        student.setPhone("13100001111");
        int n = studentDao.updateStudent(student);
        assertEquals(1, n);
    }

    @Test
    public void deleteById() throws SQLException {
        int n = studentDao.deleteById("12");
        assertEquals(1, n);
    }
}