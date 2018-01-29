package com.gdkyit.core.dao;

import com.gdkyit.core.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/23 0023.
 */
public interface UserDao {
    public List<Map<String,Object>> getAll(Map<String,Object> params);

    public void deleteById(Integer id);

    public void alert(Map<String,Object> params);

    public void add(Map<String,Object> params);

    public List<Map<String,Object>> getUserInfoById(Integer id);

    public User findOneByUsername(String username);
}
