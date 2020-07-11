package com.atguigu.springboot.dao;


import com.atguigu.springboot.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AdminDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Admin findById(String id) {
        String sql = "select * from admin where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Admin>() {
            @Override
            public Admin mapRow(ResultSet resultSet, int i) throws SQLException {
                Admin admin = new Admin();
                admin.setId(resultSet.getString("id"));
                admin.setPsw(resultSet.getString("psw"));
                System.out.println("账户信息："+admin);
                return admin;
            }
        });
    }
    public int changepsw(Admin admin,String psw){
        String sql = "UPDATE admin SET psw=? WHERE ID = ?";
        int result = jdbcTemplate.update(sql, psw,admin.getId());
        return result;
    }
}
