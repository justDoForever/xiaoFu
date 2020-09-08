package com.xiaofu.config;

import com.xiaofu.interceptors.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author jinchengll
 * @Date 2020/9/6 8:04 下午
 * 配置该应用的拦截器。
 * 如果需要单独测试你们的方法，可以先将@Configuration去掉
 */
// @Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //除了登陆接口，拦截其他所有接口
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login/**");
    }
}
