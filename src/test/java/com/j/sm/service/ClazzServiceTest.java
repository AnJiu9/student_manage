package com.j.sm.service;

import com.j.sm.entity.Clazz;
import com.j.sm.factory.ServiceFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ClazzServiceTest {

    @Test
    public void getClazzByDepId() {
        List<Clazz> list = ServiceFactory.getClazzServiceInstance().getClazzByDepId(6);
        list.forEach(System.out::println);
    }

    @Test
    public void getAll() {
        List<Clazz> list = ServiceFactory.getClazzServiceInstance().getAll();
        list.forEach(System.out::println);
    }

}