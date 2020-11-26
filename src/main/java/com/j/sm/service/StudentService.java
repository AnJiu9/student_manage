package com.j.sm.service;

import com.j.sm.entity.Clazz;
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

    /**
     * 根据院系id查询学生信息
     *
     * @return  List<Student>
     */
    List<StudentVo> getByDepId(int depId);

    /**
     * 根据班级id查询学生信息
     *
     * @return  List<Student>
     */
    List<StudentVo> getByClassId(int classId);

    /**
     * 根据关键字查询学生信息
     *
     * @return  List<Student>
     */
    List<StudentVo> getByKeywords(String keywords);
}
