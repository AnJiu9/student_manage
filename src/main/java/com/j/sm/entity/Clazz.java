package com.j.sm.entity;

/**
 * @ClassName Clazz
 * @Description 班级实体类
 * @Author orange
 * @Date 2020-11-20 21:29
 **/

public class Clazz {
    private Integer id;
    private Integer departmentId;
    private String className;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    @Override
    public String toString() {
        return className;
    }
}
