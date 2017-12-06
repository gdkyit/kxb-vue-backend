package com.gdkyit.core.service;

import com.gdkyit.core.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/5 0005.
 */
@Service
@Transactional
public class UserService {
    @Resource
    UserDao userDao;

    public List<Map<String,Object>> getAll(){
        return userDao.getAll();
    }


    public void deleteById(Integer id){
        userDao.deleteById(id);
    }

    public void alert(Integer id,Integer version, String username, String password, Integer accountEnable, Integer accountExpired, Integer accountLocked, Integer credentialsExpired, String names, String phone, Timestamp createTime){
        userDao.alert(id,version,username,password,accountEnable,accountExpired,accountLocked,credentialsExpired,names,phone,createTime);
    }

    public void add(Integer id,Integer version, String username, String password, Integer accountEnable, Integer accountExpired, Integer accountLocked, Integer credentialsExpired, String names, String phone, Timestamp createTime){
        userDao.add(id,version,username,password,accountEnable,accountExpired,accountLocked,credentialsExpired,names,phone,createTime);
    }
}
