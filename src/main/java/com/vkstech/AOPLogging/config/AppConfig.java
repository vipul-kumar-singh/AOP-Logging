package com.vkstech.AOPLogging.config;

import com.vkstech.AOPLogging.dto.DemoContext;
import com.vkstech.AOPLogging.service.ServiceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private ServiceInterceptor serviceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(serviceInterceptor);
        // TODO addPath
    }

    @Bean
    @RequestScope
    public DemoContext demoContext() {
        return new DemoContext();
    }
}
