package com.j.sm.dao;

import com.j.sm.dao.impl.clazzDaoImpl;
import com.j.sm.entity.Clazz;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ClazzDao
 * @Description 班级Dao接口
 * @Author orange
 * @Date 20.11.20
 **/

public interface ClazzDao {
    /**
     * 按照院系ID查询班级
     *
     * @param departmentId 院系id
     * @return List<CClass>院系班级集合
     * @throws SQLException 异常
     */

    List<Clazz> selectByDepartmentId(int departmentId) throws SQLException;

    /**
     * 新增班级
     * @param clazz 入参班级实体
     * @return int
     * @throws SQLException 异常
     */

    int insertClazz(Clazz clazz) throws SQLException;

    /**
     * 删除
     * @param ID 删除班级
     * @throws SQLException 异常
     */
    int removeClazz(Integer ID)throws SQLException;

    /**
     * 查询所有班级
     *
     * @return
     * @throws SQLException
     */
    List<Clazz> selectAll() throws SQLException;

}
