package com.gdkyit.core.api;

import com.gdkyit.core.domain.Result;
import com.gdkyit.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PubController {
    @Value("${config.env}")
    String env;

    @Autowired
    private BaseService baseService;

    /**
     * 
     * @return
     */
    @GetMapping("/api/info")
    public Result getInfo(){
        Map<String,Object> rs = new HashMap<>();
        rs.put("version", "0.1.26");
        rs.put("env", env);
        return Result.genSuccessResult(rs);
    }

    @GetMapping("/dblist")
    public Result getDblist(){
        return Result.genSuccessResult(baseService.getDblist());
    }



}
