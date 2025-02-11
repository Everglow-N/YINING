package com.hyn.config;

import com.hyn.interceptor.LoginInterceptor;
import com.hyn.interceptor.ManageLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class ManageWebConfig implements WebMvcConfigurer {
        @Autowired
        ManageLoginInterceptor manageLoginInterceptor;
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(manageLoginInterceptor).addPathPatterns("/manage/**").excludePathPatterns("/manage/login");
        }
    }

