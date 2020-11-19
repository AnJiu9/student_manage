package com.j.sm.dao.impl;

import com.j.sm.dao.DepartmentDao;
import com.j.sm.entity.Department;
import com.j.sm.utils.JdbcUtil;
import lombok.Builder;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName DepartmentDaoImpl
 * @Description 院系DAO实现类
 * @Author orange
 * @Date 2020-11-17 22:27
 **/

public class DepartmentDaoImpl implements DepartmentDao {
    @Override
    public List<Department> getAll() throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_department";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Department> departmentList = new ArrayList<>();
        while (rs.next()) {
            Department department = new Department();
            department.setId(rs.getInt("id"));
            department.setDepartmentName(rs.getString("department_name"));
            department.setLogo(rs.getString("logo"));
            departmentList.add(department);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return departmentList;
    }

    @Override
    public void remove(String name) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE FROM t_department where department_name = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.executeUpdate();
        pstmt.close();
        jdbcUtil.closeConnection();
        JOptionPane.showMessageDialog(null,"删除成功");
    }

    @Override
    public int insertDepartment(Department department) throws SQLException {
        JdbcUtil jdbcUtil =JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_department (department_name,logo) VALUES (?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, department.getDepartmentName());
        pstmt.setString(2, department.getLogo());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }
}
