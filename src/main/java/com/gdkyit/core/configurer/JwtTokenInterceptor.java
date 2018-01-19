package com.gdkyit.core.configurer;

import com.gdkyit.core.dao.TokenDao;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.SignatureException;
import java.util.List;
import java.util.Map;

/**
 * 请求拦截器
 * Created by Administrator on 2018/1/19 0019.
 */
public class JwtTokenInterceptor implements HandlerInterceptor {
    //这里一直报空??
    @Resource
    TokenDao tokenDao;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //排除生成token的路径
        if (httpServletRequest.getRequestURI().equals("/api/token") || RequestMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())) {
            return true;
        }
        try {
            final String authHeader = httpServletRequest.getHeader("X-YAuth-Token");
            if(authHeader == null){
                throw new SignatureException("没有请求头");
            }
            List<Map<String,Object>> tokenList = getDAO(TokenDao.class,httpServletRequest).getAll();
            for(Map<String,Object> m : tokenList){
                for(String k : m.keySet()){
                    if(k.equals("tokenname")){
                        if(authHeader.equals(m.get(k))){
                            return true;
                        }
                    }
                }
            }
        }
        //验证异常处理
        catch (io.jsonwebtoken.SignatureException | ExpiredJwtException e)
        {
            //输出对象
            PrintWriter writer = httpServletResponse.getWriter();

            //输出error消息
            writer.write("需要请求头");
            writer.close();
            return false;
        }
        //出现异常时
        catch (final Exception e)
        {
            //输出对象
            PrintWriter writer = httpServletResponse.getWriter();
            //输出error消息
            writer.write(e.getMessage());
            writer.close();
            return false;
        }
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write("token认证失败");
        writer.close();
        return false;
    }

    /**
     * 根据传入的类型获取spring管理的对应dao
     * @param clazz 类型
     * @param request 请求对象
     * @param <T>
     * @return
     */
    private <T> T getDAO(Class<T> clazz,HttpServletRequest request)
    {
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return factory.getBean(clazz);
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
