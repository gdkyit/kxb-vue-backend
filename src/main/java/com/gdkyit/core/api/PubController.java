package com.gdkyit.core.api;

import com.gdkyit.core.domain.Result;
import com.gdkyit.core.exceptions.ServiceException;
import com.gdkyit.core.service.BaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PubController {
    @Value("${config.env}")
    String env;

    @Autowired
    private BaseInfoService baseService;

    @GetMapping("/api/info")
    public Result getInfo(){
        Map<String,Object> rs = new HashMap<>();
        rs.put("version", "0.1.26");
        rs.put("env", env);
        return Result.genSuccessResult(rs);
    }

    @GetMapping("/api/dblist")
    public Result getDblist(){
        return Result.genSuccessResult(baseService.getDblist());
    }

    @GetMapping("/test/ex")
    public Result test(@RequestParam(required = true, value = "q") String path) throws Exception {
        System.out.println(path);
        throw new ServiceException("running error");
    }
    @PostMapping("api/user/login")
    public Result login(@RequestBody Map<String,String> body) throws Exception {
        System.out.println(body.toString());
        return Result.genSuccessResult();
    }
    @GetMapping("api/user/info")
    public Result userInfo(@RequestParam(required = true, value = "q") String q) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        if(q.equals("admin")){
            map.put("roles","admin");
            map.put("token","admin");
            map.put("introduction","我是超级管理员");
            map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            map.put("name","Super Admin");
            map.put("authRoutes",new String[]{"01", "0101", "02", "0201", "03", "0301"});

        }else{
            map.put("roles","editor");
            map.put("token","editor");
            map.put("introduction","我是编辑");
            map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            map.put("name","Normal Editor");
            map.put("authRoutes",new String[]{"03", "0301", "0302", "0303"});
        }
        return Result.genSuccessResult(map);
    }

}
