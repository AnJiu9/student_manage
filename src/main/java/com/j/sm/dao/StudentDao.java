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
     * @param student
     * @return
     * @throws SQLException
     */
    int insertStudent(Student student)throws SQLException;

    /**
     * 删除
     * @param ID 删除学生
     * @throws SQLException 异常
     */
    int removeStudent(Integer ID)throws SQLException;
}
