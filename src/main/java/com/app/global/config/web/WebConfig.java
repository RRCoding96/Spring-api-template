package com.app.global.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // 어떤 api 경로에 매핑할지
//                .allowedOrigins("http://localhost:8082") // 어떤 경로에서 오는걸 cors 허용할지 , 콤마 써서 여러개 쓸 수 있음
                .allowedOrigins("*")
                .allowedMethods( // 허용할 http 메소드
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.OPTIONS.name()
                )
                .maxAge(3600); // preflight 시간 설정
    }
}
