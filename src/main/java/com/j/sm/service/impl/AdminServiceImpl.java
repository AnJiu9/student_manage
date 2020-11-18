package com.j.sm.service.impl;

import com.j.sm.dao.AdminDao;
import com.j.sm.entity.Admin;
import com.j.sm.factory.DaoFactory;
import com.j.sm.service.AdminService;
import com.j.sm.utils.ResultEntity;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;

/**
 * @ClassName AdminServiceImpl
 * @Description
 * @Author orange
 * @Date 2020-11-14 22:54
 **/

public class AdminServiceImpl implements AdminService {

    private final AdminDao adminDao = DaoFactory.getAdminDaoInstance();

    @Override
    public ResultEntity adminLogin(String account, String password) {
        ResultEntity resultEntity;
        Admin admin = null;
        try {
            //根据账号查找管理员信息，捕获SQL异常
            admin = adminDao.findAdminByAccount(account);
        } catch (SQLException e) {
            System.err.println("根据账号查找管理员信息出现SQL异常");
        }

        if (admin != null) {
            //比较密码，此时需要将客户端查传过来的密码进行MD5加密后才能对比
            if (DigestUtils.md5Hex(password).equals(admin.getPassword())) {
                resultEntity = ResultEntity.builder().code(0).message("登录成功").data(admin).build();
            } else {    //账号存在，密码输入错误
                resultEntity = ResultEntity.builder().code(1).message("密码错误").build();
            }
        } else {    //账号不存在

            resultEntity = ResultEntity.builder().code(2).message("账号不存在").build();
        }
        return resultEntity;
    }

}
