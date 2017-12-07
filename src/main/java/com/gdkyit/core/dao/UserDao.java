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

    public void alert(Integer id, Integer version, String username, String password, Integer accountEnable, Integer accountExpired, Integer accountLocked, Integer credentialsExpired, String names, String phone){
        jdbcTemplate.update("UPDATE kxb_users " +
                                " SET VERSION=?,USERNAME=?,PASSWORD=?,ACCOUNT_ENABLED=?,ACCOUNT_EXPIRED=?,ACCOUNT_LOCKED=?,CREDENTIALS_EXPIRED=?,NAMES=?,PHONE=? " +
                                " WHERE ID=?",
                                new Object[]{version,username,password,accountEnable,accountExpired,accountLocked,credentialsExpired,names,phone,id});
    }

    public void add(Integer id,Integer version, String username, String password, Integer accountEnable, Integer accountExpired, Integer accountLocked, Integer credentialsExpired, String names, String phone){
        jdbcTemplate.update("INSERT INTO kxb_users VALUES (?,?,?,?,?,?,?,?,?,?,?)",new Object[]{id,version,username,password,accountEnable,accountExpired,accountLocked,credentialsExpired,names,phone,df.format(new Date())});
    }


}
