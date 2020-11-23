package com.j.sm.dao;

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
}
