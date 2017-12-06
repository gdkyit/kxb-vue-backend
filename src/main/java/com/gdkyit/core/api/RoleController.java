package com.gdkyit.core.api;

import com.gdkyit.core.domain.Result;
import com.gdkyit.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("api/role")
    public Result getRoles(){
        return Result.genSuccessResult(roleService.getRoles());
    }

    //查询单个角色标志
    @GetMapping("api/role/{id}")
    public Result getRolebz(@PathVariable int id){
        return Result.genSuccessResult(roleService.getRolebz(id));
    }

    @PostMapping("api/role")
    public Result addRole(String name,String description,byte yxbz){
        return Result.genSuccessResult(roleService.addRole(name, description, yxbz));
    }

    @DeleteMapping("api/role/{id}")
    public Result deleteRole(@PathVariable int id){
        return Result.genSuccessResult(roleService.deleteRole(id));
    }

    //修改全部
//    @PutMapping("api/role/{id}")
//    public Result updateRole(@PathVariable int id,String name,String description,byte yxbz){
//        return Result.genSuccessResult(roleService.updateRole(id, name, description, yxbz));
//    }

    //修改有效标志
    @PutMapping("api/role/{id}")
    public Result updateRole(@PathVariable int id,byte yxbz){
        return Result.genSuccessResult(roleService.updateRole(id,yxbz));
    }
}
