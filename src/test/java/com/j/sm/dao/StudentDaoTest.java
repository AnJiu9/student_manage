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
    public void insert() throws ParseException {
        int n = 0;
        Student student = Student.builder()
                .classId(1)
                .studentName("朱曼玉")
                .phone("18800921176")
                .avatar("https://student-management-img.oss-cn-hangzhou.aliyuncs.com/logo/20201123180501.JPG")
                .gender((short) 2)
                .birthday(FormatUtil.parseDate("2001-9-8"))
                .address("江苏省徐州市")
                .build();
        try {
            n = studentDao.insertStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(1,n);
    }

}