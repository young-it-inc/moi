package com.youngit.office.configuration.servletFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<LogFilter> loginCheckFilter() {
        System.out.println("1: CorsConfig class loginCheckFilter()");
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter()); // 등록할 필터 지정
        registrationBean.setOrder(1); // 필터 순서
        registrationBean.addUrlPatterns("/api/**"); // 필터 적용될 URL설정 (모든 요청에 대해 필터 적용)
        System.out.println("1: registrationBean: " + registrationBean);
        return registrationBean;
    }



    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("3: CorsConfig class addInterceptors()");
        registry.addInterceptor(ipInterceptor());
    }

    @Bean
    public IpInterceptor ipInterceptor() {
        System.out.println("4: CorsConfig class ipInterceptor()");
        return new IpInterceptor(); }



}
