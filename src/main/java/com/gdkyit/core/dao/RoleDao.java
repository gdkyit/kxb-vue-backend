package com.gdkyit.core.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RoleDao extends BaseDao {
    public List<Map<String,Object>> getRoles(){
        String sql = "SELECT * FROM kxb_role";
        return this.jdbcTemplate.queryForList(sql);
    }

    public Integer addRole(String name,String description,int yxbz){
        String sql = "INSERT INTO kxb_role(name,description,yxbz) VALUES(?,?,?)";
        return this.jdbcTemplate.update(sql,new Object[]{name,description,yxbz});
    }
}
