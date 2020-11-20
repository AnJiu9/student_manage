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

}
