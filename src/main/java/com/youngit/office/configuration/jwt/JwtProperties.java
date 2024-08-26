package com.youngit.office.configuration.jwt;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "jwt") // OfficeApplication에서 @EnableConfigurationProperties(JwtProperties.class) 추가해주면 오류 사라짐
public class JwtProperties {


    private final String issuer;
    private final String secretkey;
    private final String tokenPrefix;
    private final long expiration;

}
