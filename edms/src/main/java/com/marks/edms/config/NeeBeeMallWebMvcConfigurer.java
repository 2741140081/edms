package com.marks.edms.config;

import com.marks.edms.common.Constants;
import com.marks.edms.interceptor.AdminLoginInterceptor;
import com.marks.edms.interceptor.NewBeeMallLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class NeeBeeMallWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;

    @Autowired
    private NewBeeMallLoginInterceptor mallLoginInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminLoginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/coupling-test")
                .excludePathPatterns("/admin/categories/listForSelect")
                .excludePathPatterns("/admin/dist/**")
                .excludePathPatterns("/admin/plugins/**");
        registry.addInterceptor(mallLoginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/register")
                .excludePathPatterns("/login")
                .excludePathPatterns("/logout")
                .excludePathPatterns("/goods/detail/**")
                .excludePathPatterns("/shop-cart")
                .excludePathPatterns("/shop-cart/**")
                .excludePathPatterns("/saveOrder")
                .excludePathPatterns("/orders")
                .excludePathPatterns("/orders/**")
                .excludePathPatterns("/personal")
                .excludePathPatterns("/personal/updateInfo")
                .excludePathPatterns("/selectPayType")
                .addPathPatterns("/payPage");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
        registry.addResourceHandler("/goods-img/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
    }
}
