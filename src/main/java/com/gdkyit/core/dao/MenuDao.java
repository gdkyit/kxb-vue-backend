package com.gdkyit.core.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MenuDao extends BaseDao {

    //创建首层节点
    public Integer createRootMenu(Map<String, Object> params) {
        String name = (String) params.get("name");
        String url = (String) params.get("url");
        String path = "0";
        String sql = "insert into kxb_menu(PID,NAME,HREF,PATH) values(?,?,?,?)";
        return this.jdbcTemplate.update(sql, new Object[]{0, name, url, path});
    }

    //创建子节点
    public Integer createChildren(Map<String, Object> params) {
        String name = (String) params.get("name");
        String url = (String) params.get("url");
        Integer pid = Integer.parseInt((String) params.get("id"));
        //根据传递过来的节点id
        //查找该父节点的path路径
        String path = getPathById(pid) + "/" + pid;
        String sql = "insert into kxb_menu(PID,NAME,HREF,PATH) values(?,?,?,?)";
        return this.jdbcTemplate.update(sql, new Object[]{pid, name, url, path});
    }

    //获取path路径
    private String getPathById(Integer id) {
        String sql = "select path from kxb_menu where id=?";
        return this.jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
    }

    //输出某个节点的第一层节点
    public List<Map<String, Object>> getNodeById(Integer id) {
        String sql = "select * from kxb_menu where pid=?";
        return this.jdbcTemplate.queryForList(sql, new Object[]{id});
    }

    //删除某个节点
    public Integer delete(Map<String, Object> params) {
        Integer id = Integer.parseInt((String) params.get("id"));
        String sql = "delete from kxb_menu where id=?";
        return this.jdbcTemplate.update(sql, new Object[]{id});
    }

    //修改某个节点标题
    public Integer updateNode(Map<String, Object> params) {
        String name = (String) params.get("name");
        String sql="update kxb_menu set name=?";
        return this.jdbcTemplate.update(sql,new Object[]{name});
    }
}
