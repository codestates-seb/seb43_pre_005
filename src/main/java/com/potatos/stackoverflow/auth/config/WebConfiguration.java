package com.potatos.stackoverflow.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://ec2-3-34-134-67.ap-northeast-2.compute.amazonaws.com:8080")
                .allowedMethods("GET","POST")
                .allowCredentials(true)
                .maxAge(3000);
    }
}
