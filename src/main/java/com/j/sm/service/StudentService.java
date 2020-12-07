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

    /**
     * 更新学生信息
     *
     * @param student 学生对象
     * @return int
     */
    int updateStudent(Student student);

    /**
     * 删除学生
     *
     * @param id id
     * @return int
     */
    int deleteById(String id);

    /**
     * 新增学生
     *
     * @param student 学生对象
     * @return int
     */
    int insertStudent(Student student);

    /**
     * 根据院系统计学生数
     *
     * @param departmentId 院系id
     * @return 学生数量
     */
    int countByDepartmentId(int departmentId);

    /**
     * 根据班级统计学生数量
     *
     * @param classId 班级id
     * @return 学生数量
     */
    int countStudentByClassId(int classId);

}
