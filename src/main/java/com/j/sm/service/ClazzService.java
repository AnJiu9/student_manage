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
     *
     * @return List<Clazz>
     */
    List<Clazz> getClazzByDepId(int department);
}
