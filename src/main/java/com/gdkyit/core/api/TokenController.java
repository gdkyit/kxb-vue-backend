package com.gdkyit.core.api;

import com.gdkyit.core.dao.TokenDao;
import com.gdkyit.core.dao.UserDao;
import com.gdkyit.core.domain.TokenResult;
import com.gdkyit.core.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/19 0019.
 */
@RestController
@RequestMapping("/api")
public class TokenController {
    @Resource
    UserDao userDao;

    @Resource
    TokenDao tokenDao;

    @PostMapping("/token")
    public TokenResult token(@RequestBody Map<String,Object> params){
        final String username = (String)params.get("username");
        final String password = (String)params.get("password");
        final TokenResult tokenResult = new TokenResult();

        if (username == null || username.equals("")) {
            tokenResult.setFlag(false);
            tokenResult.setMsg("登录名不能为空");
        }
        else if (password == null || password.equals("")) {
            tokenResult.setFlag(false);
            tokenResult.setMsg("密码不能为空");
        }else {
            try{
                User user = userDao.findOneByUsername(username);
                if(!password.equals(user.getPassword())){
                    tokenResult.setFlag(false);
                    tokenResult.setMsg("用户名或密码出错");
                }else {
                    tokenResult.setToken(tokenDao.findOneByUsername(username).getTokenname());
                }
            }catch (Exception e){
                tokenResult.setMsg(e.getMessage());
                tokenResult.setFlag(false);
                tokenResult.setMsg("用户名或密码出错");
            }

        }
        return tokenResult;
    }

    /**
     * 创建新token
     *
     * @param appId
     * @return
     */
    private String createNewToken(String appId) {
        //获取当前时间
        Date now = new Date(System.currentTimeMillis());
        //过期时间
        Date expiration = new Date(now.getTime() + 7200000);
        return Jwts
                .builder()
                .setSubject(appId)
                //.claim(YAuthConstants.Y_AUTH_ROLES, userDbInfo.getRoles())
                .setIssuedAt(now)
                .setIssuer("Online YAuth Builder")
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, "HengYuAuthv1.0.0")
                .compact();
    }
}
