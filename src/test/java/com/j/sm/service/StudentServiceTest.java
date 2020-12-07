package com.j.sm.service;

import com.j.sm.entity.Department;
import com.j.sm.entity.Student;
import com.j.sm.factory.ServiceFactory;
import com.j.sm.utils.FormatUtil;
import com.j.sm.vo.StudentVo;
import org.junit.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentServiceTest {
    private final StudentService studentService = ServiceFactory.getStudentServiceInstance();

    @Test
    public void getAll() {
        List<StudentVo> studentVoList = studentService.getAll();
        studentVoList.forEach(System.out::println);
    }

    @Test
    public void getByDepId() {
        List<StudentVo> studentVoList = studentService.getByDepId(5);
        studentVoList.forEach(System.out::println);
    }

    @Test
    public void getByClassId() {
        List<StudentVo> studentVoList = studentService.getByClassId(1);
        studentVoList.forEach(System.out::println);
    }

    @Test
    public void getByKeywords() {
        List<StudentVo> studentVoList = studentService.getByKeywords("刘");
        studentVoList.forEach(System.out::println);
    }

    @Test
    public  void insert()throws ParseException {
        int n = 0;
        Student student = Student.builder()
                .classId(20)
                .studentName("陈云")
                .phone("18800921176")
                .avatar("https://student-management-img.oss-cn-hangzhou.aliyuncs.com/logo/20201123180501.JPG")
                .gender((short) 2)
                .birthday(FormatUtil.parseDate("2001-9-8"))
                .address("江苏省徐州市")
                .build();
        n = studentService.insertStudent(student);
        assertEquals(1,n);
    }

    @Test
    public void delStudent() {
        int n = 0;
        n = studentService.deleteById("11");
        assertEquals(1,n);
    }


    @Test
    public void updateStudent() {
        Student student = new Student();
        student.setId("18");
        student.setAddress("浙江省温州市");
        student.setPhone("13100001111");
        int n = studentService.updateStudent(student);
        assertEquals(1, n);
    }
}