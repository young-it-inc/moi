package com.youngit.office;

import com.youngit.office.configuration.jwt.JwtProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
@EnableJdbcHttpSession
@EnableConfigurationProperties(JwtProperties.class)
@EnableAspectJAutoProxy
@MapperScan(basePackages = "com.youngit.office.api.**.mapper") //Spring Boot 애플리케이션에서 sqlSessionTemplate 빈을 찾지 못해 발생하는 문제 해결
public class OfficeApplication {

    private static Logger logger = LoggerFactory.getLogger(OfficeApplication.class);
    public static void main(String[] args) {
        logger.info("OfficeApplication main method");
        SpringApplication.run(OfficeApplication.class, args);
    }

}
