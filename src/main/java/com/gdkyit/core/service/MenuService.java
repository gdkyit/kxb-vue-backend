package com.gdkyit.core.service;

import com.gdkyit.core.dao.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MenuService {
    @Autowired
    private MenuDao menuDao;
    public Integer createRootMenu(Map<String,Object> params){
        return menuDao.createRootMenu(params);
    }

    public Integer createChildren(Map<String,Object> params){
        return menuDao.createChildren(params);
    }

    public List<Map<String,Object>> getNodeById(Integer id){
        return menuDao.getNodeById(id);
    }

    public Integer delete(Map<String,Object> params){
        return menuDao.delete(params);
    }

    public Integer updateNode(Map<String,Object> params){
        return menuDao.updateNode(params);
    }


}
