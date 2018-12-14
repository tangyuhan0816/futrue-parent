package com.futrue.asset.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  @Author: Yuhan.Tang
 *  @ClassName: WebAppConfig
 *  @package: com.futrue.asset.config
 *  @Date: Created in 2018/7/19 下午6:33
 *  @email yuhan.tang@magicwindow.cn
 *  @Description:
 */    
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Autowired
    private CorsInterceptor corsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor);
    }

}