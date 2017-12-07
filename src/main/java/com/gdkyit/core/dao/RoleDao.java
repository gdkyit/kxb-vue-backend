package com.gdkyit.core.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.sun.tools.doclets.formats.html.markup.HtmlStyle.description;

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

    public Integer addRole(Map<String,Object> role){
        String sql = "INSERT INTO kxb_role(name,description,yxbz) VALUES(?,?,?)";
        String name = (String) role.get("name");
        String description = (String) role.get("description");
        Byte yxbz = Byte.parseByte((String) role.get("yxbz"));
        return this.jdbcTemplate.update(sql,new Object[]{name,description,yxbz});
    }

    public int deleteRole(int id){
        String sql = "delete from kxb_role where id =?";
        return this.jdbcTemplate.update(sql,id);
    }

    //修改单个全部信息
    public int updateRole(int id,Map<String,Object> role){
        String sql = "update kxb_role set name=?,description=?,yxbz=? where id=?";
        String name = (String) role.get("name");
        String description = (String) role.get("description");
        Byte yxbz = Byte.parseByte((String) role.get("yxbz"));
        return this.jdbcTemplate.update(sql,new Object[]{name,description,yxbz,id});
    }

    //修改单个标志
    public int updateYxbz(int id, Map<String, Object> params){
        String sql = "update kxb_role set yxbz=? where id=?";
        Byte yxbz = Byte.parseByte((String) params.get("yxbz"));
        return this.jdbcTemplate.update(sql,new Object[]{yxbz,id});
    }
}
