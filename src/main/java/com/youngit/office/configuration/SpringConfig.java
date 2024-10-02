package com.youngit.office.configuration;

import com.youngit.office.aop.Aop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public Aop aop() {
        return new Aop();
    }
}
