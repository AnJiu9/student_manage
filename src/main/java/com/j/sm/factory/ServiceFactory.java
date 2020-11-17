package com.j.sm.factory;

import com.j.sm.service.AdminService;
import com.j.sm.service.DepartmentService;
import com.j.sm.service.impl.AdminServiceImpl;
import com.j.sm.service.impl.DepartmentServiceImpl;

/**
 * @ClassName ServiceFactory
 * @Description Service工厂类
 * @Author orange
 * @Date 2020-11-14 23:00
 **/

public class ServiceFactory {
    public static AdminService getAdminServiceInstance() {
        return new AdminServiceImpl();
    }

    public static DepartmentService getDepartmentServiceInstance() {
        return new DepartmentServiceImpl();
    }
}
