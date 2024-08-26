package com.youngit.office.configuration.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        OpenAPI openAPI = new OpenAPI();
        openAPI.components(new Components()).info(apiInfo());
        System.out.println("SwaggerConfig class: openAPI : " + openAPI);
        return openAPI;
    }

    private Info apiInfo() {
        Info info = new Info();
        info.title("Spring Boot REST API Specifications").description("Specification").version("1.0.0");
        System.out.println("SwaggerConfig class: info : " + info);
        return info;

    }
}
