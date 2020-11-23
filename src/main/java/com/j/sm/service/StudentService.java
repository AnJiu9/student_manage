package com.j.sm.service;

import com.j.sm.entity.Department;
import com.j.sm.entity.Student;
import com.j.sm.vo.StudentVo;

import java.util.List;

/**
 * @ClassName StudentService
 * @Description TODO
 * @Author orange
 * @Date 23.11.20
 **/

public interface StudentService {
    /**
     * 查询所有学生信息
     *
     *
     * @return List<Student>
     */
    List<StudentVo> getAll();
}
