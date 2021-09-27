package com.blockback.init.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins("http://j5b1021.p.ssafy.io","http://j5b1021.p.ssafy.io:8080", "http://localhost:8080") // 나중에 https로 바꾸기
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }
}
