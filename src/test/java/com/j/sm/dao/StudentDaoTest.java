package com.j.sm.dao;

import com.j.sm.factory.DaoFactory;
import com.j.sm.vo.StudentVo;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class StudentDaoTest {

    @Test
    public void selectAll() throws SQLException {
        List<StudentVo> list = DaoFactory.getStudentDaoInstance().selectAll();
        list.forEach(System.out::println);
    }
}