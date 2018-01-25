package com.gdkyit.core.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class RoleDao extends BaseDao {

    //查询全部
    public List<Map<String, Object>> getRoles(Integer page, Integer size) {
        String sql = "SELECT * FROM kxb_role limit ?,?";
        return this.jdbcTemplate.queryForList(sql, new Object[]{(page - 1) * size, size});
    }

    public Map<String, Object> count() {
        return this.jdbcTemplate.queryForMap("select count(*) from kxb_role");
    }

    //查询单个角色
    public List<Map<String, Object>> getRole(int id) {
        String sql = "select * from kxb_role where id = ?";
        return this.jdbcTemplate.queryForList(sql, id);
    }

    public Integer addRole(Map<String, Object> role) {
        String sql = "INSERT INTO kxb_role(name,description,yxbz) VALUES(?,?,?)";
        String name = (String) role.get("name");
        String description = (String) role.get("description");
        Byte yxbz = Byte.parseByte((String) role.get("yxbz"));
        return this.jdbcTemplate.update(sql, new Object[]{name, description, yxbz});
    }

    public int deleteRole(int id) {
        String sql = "delete from kxb_role where id =?";
        return this.jdbcTemplate.update(sql, id);
    }

    //修改单个全部信息
    public int updateRole(int id, Map<String, Object> role) {
        String sql = "update kxb_role set name=?,description=?,yxbz=? where id=?";
        String name = (String) role.get("name");
        String description = (String) role.get("description");
        Byte yxbz = Byte.parseByte((String) role.get("yxbz"));
        return this.jdbcTemplate.update(sql, new Object[]{name, description, yxbz, id});
    }

    //修改单个标志
//    public int updateYxbz(int id, Map<String, Object> params){
//        String sql = "update kxb_role set yxbz=? where id=?";
//        Byte yxbz = Byte.parseByte((String) params.get("yxbz"));
//        return this.jdbcTemplate.update(sql,new Object[]{yxbz,id});
//    }

    //查询条件
    public List<Map<String, Object>> searchRole(Map<String, Object> searchrole) {
        StringBuilder sql = new StringBuilder("select * from kxb_role where 1=1 ");
        List<Object> args = new ArrayList<>();
        if (searchrole.get("name") != null) {
            sql.append("and name like ? ");
            args.add("%" + searchrole.get("name") + "%");
        }
        if (searchrole.get("description") != null) {
            sql.append("and description like ? ");
            args.add("%" + searchrole.get("description") + "%");
        }
        if (searchrole.get("yxbz") != null) {
            sql.append("and yxbz = ?");
            args.add(searchrole.get("yxbz"));
        }
        return this.jdbcTemplate.queryForList(new String(sql), args.toArray());
    }
}
