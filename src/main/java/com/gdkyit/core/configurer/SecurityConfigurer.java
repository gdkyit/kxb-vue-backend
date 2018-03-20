package com.gdkyit.core.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                // All of Spring Security will ignore the requests
                .antMatchers("/resources/**")
                .antMatchers(HttpMethod.POST, "/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().cacheControl().disable().and()
                .servletApi().and()

                .authorizeRequests()

                 //身份验证api允许匿名访问
                .antMatchers(HttpMethod.POST,"/test/**").permitAll()

                // authenticate REST api
                .antMatchers("/api/**").authenticated()

                //对外公开api允许匿名访问
                .antMatchers("/pub/api/**").permitAll();
    }
}
