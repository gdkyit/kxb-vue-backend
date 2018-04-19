package com.gdkyit.core.dao;

import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/5 0005.
 */
@Repository
public class UserDao extends BaseDao {
    final static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    public List<Map<String,Object>> getAll(){
        List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from kxb_users");
        return list;
    }


    public void deleteById(Integer id){
        jdbcTemplate.update("DELETE FROM kxb_users WHERE ID=?",new Object[]{id});
    }

    public void alert(Map<String,Object> params){
        StringBuffer stringBuffer = new StringBuffer("UPDATE kxb_users ");
        stringBuffer.append("SET VERSION=?,USERNAME=?,PASSWORD=?,ACCOUNT_ENABLED=?,ACCOUNT_EXPIRED=?,ACCOUNT_LOCKED=?,CREDENTIALS_EXPIRED=?,NAMES=?,PHONE=? ");
        stringBuffer.append("WHERE ID=?");
        jdbcTemplate.update(stringBuffer.toString(),new Object[]{params.get("version"),params.get("username"),params.get("password"),params.get("accountEnable"),params.get("accountExpired"),params.get("accountLocked"),params.get("credentialsExpired"),params.get("names"),params.get("phone"),params.get("id")});
    }

    public void add(Map<String,Object> params){
        String sql = "INSERT INTO kxb_users VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{params.get("id"),params.get("version"),params.get("username"),params.get("password"),params.get("accountEnable"),params.get("accountExpired"),params.get("accountLocked"),params.get("credentialsExpired"),params.get("names"),params.get("phone"),df.format(new Date())});
    }


}
