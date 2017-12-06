package com.gdkyit.core.api;

import com.gdkyit.core.domain.Result;
import com.gdkyit.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("api/role")
    public Result addRole(@RequestBody String name,String description,int yxbz){
        return Result.genSuccessResult(roleService.addRole(name, description, yxbz));
    }
}
