package com.j.sm.service;

import com.j.sm.factory.ServiceFactory;
import com.j.sm.utils.ResultEntity;
import org.junit.Test;

import javax.sound.midi.Soundbank;

import static org.junit.Assert.*;

public class AdminServiceTest {

    private final AdminService adminService = ServiceFactory.getAdminServiceInstance();

    @Test
    public void adminLogin() {
        ResultEntity resultEntity = adminService.adminLogin("taoranran@qq.com","123123");
        assertEquals(0,resultEntity.getCode());
        System.out.println(resultEntity);
    }
}