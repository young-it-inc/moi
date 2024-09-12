package com.youngit.office;

import com.youngit.office.configuration.jwt.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
@ComponentScan(basePackages = {"com.youngit.office", "com.youngit.office.configuration"})
@EnableJdbcHttpSession
@EnableConfigurationProperties(JwtProperties.class)
//@MapperScan("com.youngit.office.api.**.mapper")
public class OfficeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfficeApplication.class, args);
    }

}
