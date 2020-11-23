package com.j.sm.dao.impl;

import com.j.sm.dao.StudentDao;
import com.j.sm.entity.Student;
import com.j.sm.utils.JdbcUtil;
import com.j.sm.vo.StudentVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StudentDaoImpl
 * @Description
 * @Author orange
 * @Date 2020-11-23 18:24
 **/

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<StudentVo> selectAll() throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.*, t2.class_name, t3.department_name\n" +
                "FROM t_student t1\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.class_id = t2.id\n" +
                "LEFT JOIN t_department t3\n" +
                "ON t2.department_id = t3.id";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<StudentVo> list = new ArrayList<>();
        while (rs.next()) {
            StudentVo student = StudentVo.builder()
                    .id(rs.getString("id"))
                    .departmentName(rs.getString("department_name"))
                    .className(rs.getString("class_name"))
                    .studentName(rs.getString("student_name"))
                    .phone(rs.getString("phone"))
                    .gender(rs.getShort("gender"))
                    .avatar(rs.getString("avatar"))
                    .birthday(rs.getDate("birthday"))
                    .address(rs.getString("address"))
                    .build();
            list.add(student);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return list;
    }
}
