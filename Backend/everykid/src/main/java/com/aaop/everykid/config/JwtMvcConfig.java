/*
package com.aaop.everykid.config;

import com.aaop.everykid.Jwt.JwtTokenInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class JwtMvcConfig implements WebMvcConfigurer {
    private final JwtTokenInterceptor jwtTokenInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("인터셉터 등록");
        registry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/signUp");
    }
}*/
