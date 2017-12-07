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

    //查询单个角色标志
    public List<Map<String,Object>> getRolebz(int id){
        return roleDao.getRolebz(id);
    }

    public Integer addRole(String name,String description,byte yxbz){
        return roleDao.addRole(name,description,yxbz);
    }

    public int deleteRole(int id){
        return roleDao.deleteRole(id);
    }

    //修改全部
//    public Integer updateRole(int id,String name,String description,byte yxbz){
//        return roleDao.updateRole(id, name, description, yxbz);
//    }

    //修改标志
    public Integer updateRole(int id,byte yxbz){
        return roleDao.updateRole(id,yxbz);
    }
}

