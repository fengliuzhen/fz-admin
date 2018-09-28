package com.fz.admin.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 注册 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(beforeAddInterceptor()).addPathPatterns("/**").excludePathPatterns(Arrays.asList("/login","/postlogin","/validcode","/js/**", "/images/**","/element-ui/**"));
    }

    /**
     * 先注入自定义的拦截器
     * flz 2018-09-28
     */
    @Bean
    public Interceptor beforeAddInterceptor() {
        return new Interceptor();
    }
}