package com.gdkyit.core.api;

import com.gdkyit.core.domain.Result;

import com.gdkyit.core.service.UserService;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by Administrator on 2017/12/5 0005.
 */
@RestController
@RequestMapping("api/users")
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
        userService.add(params);
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
        userService.alert(params);
        return Result.genSuccessResult();

    }

     @GetMapping("/user/info")
    public ResponseEntity<?> getInfo(String token) {
        Map<String, Object> result = new HashMap<>();
        List<String> roles = new ArrayList<>();
        roles.add(token);
        List<String> role = new ArrayList<>();
        role.add(token);
        result.put("roles", roles);
        result.put("role", role);
        result.put("name", token);
        result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return new ResponseEntity<Object>(Result.genSuccessResult(result), HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody Map<String, Object> params) {
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        if (username.equals("admin") && password.equals("admin")) {
            Map<String, Object> result = new HashMap<>();
            result.put("token", username);
            return new ResponseEntity<Object>(Result.genSuccessResult(result), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(Result.genFailResult("登录帐号不对"), HttpStatus.OK);

    }

    @PostMapping("/user/logout")
    public ResponseEntity<?> logout() {
        return new ResponseEntity<Object>(Result.genSuccessResult("success"), HttpStatus.OK);
    }
}
