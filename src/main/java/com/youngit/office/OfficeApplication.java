package com.youngit.office;

import com.youngit.office.configuration.jwt.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableJdbcHttpSession
@EnableConfigurationProperties(JwtProperties.class)
@MapperScan(basePackages = "com.youngit.office.api.**.mapper") //Spring Boot 애플리케이션에서 sqlSessionTemplate 빈을 찾지 못해 발생하는 문제 해결
public class OfficeApplication {

    public static void main(String[] args) {
        System.out.println("여기가1일테고");
        SpringApplication.run(OfficeApplication.class, args);
        System.out.println("여기가2");
    }

}
