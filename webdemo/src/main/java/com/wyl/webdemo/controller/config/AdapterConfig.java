package com.wyl.webdemo.controller.config;

import com.wyl.webdemo.controller.filter.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
public class AdapterConfig extends WebMvcConfigurerAdapter {

    private final String[] excludes = new String[]{
            "/redis/**",
            "/index/**",
            "/studentInfo/**",
            "/db/**",
            "/opLog/**",
    };

    @Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(loginInterceptor()).excludePathPatterns(excludes).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
