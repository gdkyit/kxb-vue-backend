package com.gdkyit.core.controller;

import com.gdkyit.core.domain.Result;
import com.gdkyit.core.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/5 0005.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    UserService userService;

    /**
     * 获取所有用户
     */
    @GetMapping("")
    public Result getAll(){
       List<Map<String,Object>> list = userService.getAll();
        return Result.genSuccessResult(list);
    }

    /**
     * 增加用户
     */
    @PostMapping("/add")
    public Result add(@RequestBody Map<String,Object> params){
        userService.add((Integer) params.get("id"),(Integer) params.get("version"),(String) params.get("username"),(String)params.get("password"),(Integer)params.get("accountEnable"),(Integer) params.get("accountExpired"),(Integer) params.get("accountLocked"),(Integer)params.get("credentialsExpired"),(String)params.get("names"),(String) params.get("phone"));
        return Result.genSuccessResult();
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id ){
        userService.deleteById(id);
        return Result.genSuccessResult();
    }

    /**
     *修改用户
     */
    @PutMapping("/alert/{id}")
    public Result alert(@PathVariable Integer id ,@RequestBody Map<String,Object> params){
        userService.alert((Integer) params.get("id"),(Integer) params.get("version"),(String) params.get("username"),(String)params.get("password"),(Integer)params.get("accountEnable"),(Integer) params.get("accountExpired"),(Integer) params.get("accountLocked"),(Integer)params.get("credentialsExpired"),(String)params.get("names"),(String) params.get("phone"));
        return Result.genSuccessResult();
    }

}
