package com.gdkyit.core.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RoleDao extends BaseDao {

    //查询全部
    public List<Map<String,Object>> getRoles(){
        String sql = "SELECT * FROM kxb_role";
        return this.jdbcTemplate.queryForList(sql);
    }

    //查询单个角色标志
    public List<Map<String,Object>> getRolebz(int id){
        String sql = "select yxbz from kxb_role where id = ?";
        return this.jdbcTemplate.queryForList(sql,id);
    }

    public Integer addRole(String name,String description,byte yxbz){
        String sql = "INSERT INTO kxb_role(name,description,yxbz) VALUES(?,?,?)";
        return this.jdbcTemplate.update(sql,new Object[]{name,description,yxbz});
    }

    public int deleteRole(int id){
        String sql = "delete from kxb_role where id =?";
        return this.jdbcTemplate.update(sql,id);
    }

    //修改全部
//    public int updateRole(int id,String name,String description,byte yxbz){
//        String sql = "update kxb_role set name=?,description=?,yxbz=? where id=?";
//        return this.jdbcTemplate.update(sql,new Object[]{name,description,yxbz,id});
//    }

    //修改标志
    public int updateRole(int id,byte yxbz){
        String sql = "update kxb_role set yxbz=? where id=?";
        return this.jdbcTemplate.update(sql,new Object[]{yxbz,id});
    }
}
