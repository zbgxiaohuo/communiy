package com.nowcoder.community.config;

import com.nowcoder.community.controller.interceptor.LoginTicketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* 拦截器配置类
* */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // 注入拦截器
    @Autowired
    private LoginTicketInterceptor loginTicketInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器
        registry.addInterceptor(loginTicketInterceptor)
                // 静态资源不拦截
                .excludePathPatterns( "/css/**", "/js/**", "/png/**", "/jpeg/**", "/jpg/**");
/*                // 添加拦截路径
                .addPathPatterns("/register", "/login");*/
    }
}
