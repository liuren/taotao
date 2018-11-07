package com.louis.kitty.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //允许跨域访问的路径
        .allowedOrigins("*") //允许跨域访问的源
        .allowedMethods("PUT","GET","POST","DELETE","OPTIONS") //允许跨域的请求方法,OPTIONS
        .maxAge(168000) //预检间隔时间
        .allowedHeaders("*") //头部允许设置
        .allowCredentials(true);//是否允许发送cookie
    }
}
