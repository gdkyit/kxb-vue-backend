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

    //查询单个角色
    public List<Map<String,Object>> getRole(int id){
        return roleDao.getRole(id);
    }

    public Integer addRole(Map<String,Object> role){
        return roleDao.addRole(role);
    }

    public int deleteRole(int id){
        return roleDao.deleteRole(id);
    }

    //修改单个全部信息
    public Integer updateRole(int id,Map<String,Object> role){
        return roleDao.updateRole(id, role);
    }

    //修改单个标志
    public Integer updateYxbz(int id, Map<String, Object> yxbz){
        return roleDao.updateYxbz(id,yxbz);
    }
}

