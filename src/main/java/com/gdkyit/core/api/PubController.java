package com.gdkyit.core.api;

import com.gdkyit.core.domain.Result;
import com.gdkyit.core.exceptions.ServiceException;
import com.gdkyit.core.service.BaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
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
    @PostMapping("/user/login")
    public Result login(@RequestBody Map<String,String> body) throws Exception {
        System.out.println(body.toString());
        return Result.genSuccessResult();
    }

}
