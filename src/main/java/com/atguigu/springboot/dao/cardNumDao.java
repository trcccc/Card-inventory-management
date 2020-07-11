package com.atguigu.springboot.dao;


import com.atguigu.springboot.entities.cardNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class cardNumDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public cardNum findByKind(String kind) {
        String sql = "select * from cardNum where kind=?";
        return jdbcTemplate.queryForObject(sql,new Object[]{kind}, new RowMapper<cardNum>() {
            @Override
            public cardNum mapRow(ResultSet resultSet, int i) throws SQLException {
                cardNum num = new cardNum();
                num.setNum(resultSet.getString("num"));
                num.setKind(resultSet.getString("kind"));
                System.out.println("findbykind" + num);
                return num;
            }
        });
    }

    public int inCrease(Integer addnum,String kind) {
        String sql = "UPDATE cardNum SET Num =? where kind=?";
        int result = jdbcTemplate.update(sql, Integer.parseInt(findByKind(kind).getNum())+addnum,kind);
        return result;
    }
}
