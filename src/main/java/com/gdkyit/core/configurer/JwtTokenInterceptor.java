package com.gdkyit.core.configurer;

import com.gdkyit.core.dao.TokenDao;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求拦截器
 * Created by Administrator on 2018/1/19 0019.
 */
@Component
public class JwtTokenInterceptor implements HandlerInterceptor {
    //这里一直报空??
    @Autowired
    TokenDao tokenDao;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //关闭token 认证
        return true;

//        //排除生成token的路径
//        if (httpServletRequest.getRequestURI().equals("/api/token") || RequestMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())) {
//            return true;
//        }
//        try {
//            final String authHeader = httpServletRequest.getHeader("token");
//            if(authHeader == null){
//
//            }
//            List<Map<String,Object>> tokenList = getDAO(TokenDao.class,httpServletRequest).getAll();
//            for(Map<String,Object> m : tokenList){
//                for(String k : m.keySet()){
//                        if(k.equals("tokenname")){
//                            if(authHeader.equals(m.get(k))){
//                                return true;
//                        }
//                    }
//                }
//            }
//        }catch (Exception e){
//            PrintWriter writer = httpServletResponse.getWriter();
//            writer.write("token request error");
//            writer.close();
//            return true;
//        }
//        PrintWriter writer = httpServletResponse.getWriter();
//        writer.write("token request error");
//        writer.close();
//        return true;
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
