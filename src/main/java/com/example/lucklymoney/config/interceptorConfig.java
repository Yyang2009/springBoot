package com.example.lucklymoney.config;


import com.example.lucklymoney.interceptor.AuthenticetionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
        registration.addPathPatterns("/**");  // 拦截路径
//        registration.addPathPatterns("/api/logout");  // 拦截所路径
        registration.excludePathPatterns("/api/loginBySession")  //添加不拦截路径
        .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将templates目录下的CSS、JS文件映射为静态资源，防止Spring把这些资源识别成thymeleaf模版
        registry.addResourceHandler("/templates/**.js").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/templates/**.css").addResourceLocations("classpath:/templates/");
        //其他静态资源
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //swagger增加url映射
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}
