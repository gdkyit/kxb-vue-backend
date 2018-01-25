package com.gdkyit.core.api;

import com.gdkyit.core.domain.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
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
