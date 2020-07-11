package com.atguigu.springboot.dao;


import com.atguigu.springboot.entities.cardNum;
import com.atguigu.springboot.entities.record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Repository
public class recordDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAll(){
        String sql = "select * from record";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
    public int save(record record){
        String sql = "INSERT INTO record (kind,num,time,name ) VALUES (?,?,?,?)";
        Object args[] = {record.getKind(),record.getNum(),record.getTime(),record.getName()};

        return jdbcTemplate.update(sql, args);
    }

    public record findByKind(String kind) {
        String sql = "select * from record where kind=?";
        return jdbcTemplate.queryForObject(sql,new Object[]{kind}, new RowMapper<record>() {
            @Override
            public record mapRow(ResultSet resultSet, int i) throws SQLException {
                record num = new record();
                num.setNum(resultSet.getString("num"));
                num.setKind(resultSet.getString("kind"));
                num.setTime(resultSet.getString("time"));
                num.setName(resultSet.getString("name"));
                System.out.println("findbykind" + num);
                return num;
            }
        });
    }
    public List<Map<String, Object>>  findByDepartment(String department){
        String sql = "select * from record where kind = ?";

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,department);
        return list;
    }
    public List<Map<String, Object>>  findByTime(String time){

        String sql = "select * from record WHERE ( datediff ( time , ? ) = 0 )";

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,time);
        return list;
    }
}
