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
    public Result addRole(@RequestBody Map<String,Object> role){
        return Result.genSuccessResult(roleService.addRole(role));
    }

    @DeleteMapping("api/role/{id}")
    public Result deleteRole(@PathVariable int id){
        return Result.genSuccessResult(roleService.deleteRole(id));
    }

    //修改单个全部信息
    @PutMapping("api/role/{id}")
    public Result updateRole(@PathVariable int id,@RequestBody Map<String,Object> role){
        return Result.genSuccessResult(roleService.updateRole(id,role));
    }

    //修改单个有效标志
    @PutMapping("api/roleyxbz/{id}")
    public Result updateYxbz(@PathVariable int id,@RequestBody Map<String,Object> yxbz){
        return Result.genSuccessResult(roleService.updateYxbz(id,yxbz));
    }
}
