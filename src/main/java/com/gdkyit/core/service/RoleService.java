package com.gdkyit.core.service;

import com.gdkyit.core.dao.RoleDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoleService {
    @Resource
    private RoleDao roleDao;

    public List<Map<String,Object>> getRoles(){
        return roleDao.getRoles();
    }

    public Integer addRole(String name,String description,int yxbz){
        return roleDao.addRole(name,description,yxbz);
    }
}

