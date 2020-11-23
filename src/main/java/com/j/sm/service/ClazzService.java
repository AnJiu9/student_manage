package com.j.sm.service;

import com.j.sm.entity.Clazz;
import com.j.sm.entity.Department;

import java.util.List;

/**
 * @ClassName ClazzService
 * @Description TODO
 * @Author orange
 * @Date 20.11.20
 **/

public interface ClazzService {
    /**
     * 查询所有班级
     *
     * @return List<Clazz>
     */
    List<Clazz> getClazzByDepId(int department);

    /**
     * 新增班级
     *
     * @param clazz 班级实体
     * @return int
     */

    int addClazz(Clazz clazz);

    /**
     * 删除班级
     * @param name 班级名称
     */
    int deleteClazz(Integer ID);
}
