package com.gdkyit.core.service;

import com.gdkyit.core.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
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

    public void alert(Map<String,Object> params){
        userDao.alert(params);
    }

    public void add(Map<String,Object> params){
        userDao.add(params);
    }
}
