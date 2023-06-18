package com.app.global.config;

import com.app.global.resolver.memberinfo.MemberInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    // Swagger의 핵심 빈
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select() // ApiSelectorBuilder 생성
                .apis(RequestHandlerSelectors.basePackage("com.app.api")) // API 패키지 경로 todo 패키지 경로 수정
                .paths(PathSelectors.ant("/api/**")) // path 조건에 따라서 API 문서화 todo API 경로 수정
                .build()
                .apiInfo(apiInfo()) // API 문서에 대한 정보 추가
                .useDefaultResponseMessages(false) // swagger에서 제공하는 기본 응답 코드 설명 제거
                .securityContexts(Arrays.asList(securityContext())) // Authorization Header 입력 가능하게
                .securitySchemes(Arrays.asList(apiKey())) // apiKey 반환
                .ignoredParameterTypes(MemberInfo.class)
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API 문서")
                .description("API에 대해서 설명해주는 문서입니다.")
                .version("1.0")
                .build();
    }

    // Authorization Header
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

}