package com.j.sm.dao;

import com.j.sm.entity.Student;
import com.j.sm.vo.StudentVo;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName StudentDao
 * @Description TODO
 * @Author orange
 * @Date 23.11.20
 **/

public interface StudentDao {
    /**
     * 查询所有学生（视图对象）
     *
     * @return 学生视图列表数据
     * @throws SQLException 异常
     */
    List<StudentVo> selectAll() throws SQLException;

    /**
     * 根据院系id查询学生
     *
     * @param depId
     * @return
     * @throws SQLException
     */
    List<StudentVo> selectByDepId(int depId) throws SQLException;

    /**
     * 根据班级id查询学生
     *
     * @param classId
     * @return
     * @throws SQLException
     */
    List<StudentVo> selectByClassId(int classId) throws SQLException;

    /**
     * 根据关键字查询学生
     *
     * @param keywords
     * @return
     * @throws SQLException
     */
    List<StudentVo> selectByKeywords(String keywords) throws SQLException;

    /**
     * 新增学生
     *
     * @param student 入参
     * @return int
     * @throws SQLException 异常
     */
    int insertStudent(Student student)throws SQLException;

    /**
     * 根据id删除学生
     *
     * @param id 学生id
     * @return int
     * @throws SQLException 异常
     */
    int deleteById(String id) throws SQLException;


    /**
     * 更新学生信息
     *
     * @param student 入参
     * @return int
     * @throws SQLException 异常
     */
    int updateStudent(Student student) throws SQLException;

    /**
     * 根据院系id统计学生人数
     *
     * @param departmentId 院系id
     * @return int
     * @throws SQLException 异常
     */
    int countByDepartmentId(int departmentId) throws SQLException;

    /**
     * 根据班级id统计生人数
     *
     * @param classId 班级id
     * @return int
     * @throws SQLException 异常
     */
    int countByClassId(int classId) throws SQLException;
}
