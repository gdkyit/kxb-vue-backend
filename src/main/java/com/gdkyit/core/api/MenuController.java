package com.gdkyit.core.api;

import com.gdkyit.core.domain.Result;
import com.gdkyit.core.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping("/api/menu")
    public Result createRootMenu(@RequestBody Map<String, Object> params) {
        return Result.genSuccessResult(menuService.createRootMenu(params));
    }

    @PostMapping("/api/child/menu")
    public Result createChildren(@RequestBody Map<String, Object> params) {
        return Result.genSuccessResult(menuService.createChildren(params));
    }

    @GetMapping("/api/menu")
    public Result getNodes(Integer id) {
        return Result.genSuccessResult(menuService.getNodeById(id));
    }

    @DeleteMapping("/api/menu")
    public Result deleteNode(@RequestBody Map<String, Object> params) {
        return Result.genSuccessResult(menuService.delete(params));
    }

    @PutMapping("/api/menu")
    public Result updateNode(@RequestBody Map<String,Object> params){
        return Result.genSuccessResult(menuService.updateNode(params));
    }
}
