package com.j.sm.service;

import com.j.sm.entity.Department;
import com.j.sm.factory.ServiceFactory;
import com.j.sm.vo.StudentVo;
import org.junit.Test;

import java.util.List;

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
}