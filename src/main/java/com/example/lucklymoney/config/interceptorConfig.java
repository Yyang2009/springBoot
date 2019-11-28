package com.example.lucklymoney.config;


import com.example.lucklymoney.interceptor.AuthenticetionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class interceptorConfig implements WebMvcConfigurer {

    @Bean
    public AuthenticetionInterceptor getAuthenticetionInterceptor(){
        return new AuthenticetionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(getAuthenticetionInterceptor());
        registration.addPathPatterns("/**");  // 拦截所有请求
        //registration.excludePathPatterns("\"/\",\"/error\",\"/static/**\"");  //添加不拦截路径
    }
}
