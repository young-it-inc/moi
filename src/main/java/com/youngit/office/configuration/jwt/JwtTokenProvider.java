package com.youngit.office.configuration.jwt;

import com.youngit.office.api.member.dto.MemberDto;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.logging.Logger;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private static final Logger logger = Logger.getLogger(JwtTokenProvider.class.getName());

    private final JwtProperties jwtProperties;

    public String makeJwtToken(MemberDto memberDto) {

        logger.info("makeJwtToken");
        Instant now = Instant.now();
        Instant expriyDate = now.plusMillis(jwtProperties.getExpiration()); //24시간(1000 * 60 * 60 * 24)

        String token = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expriyDate))
                .claim("id", memberDto.getMemberId())
                .claim("email", memberDto.getEmail())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretkey())
                .compact();

        return token;
    }

    public Claims parseJwtToken(String token) {

        logger.info("parseJwtToken");
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretkey())
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (JwtException e) {
            logger.info("Invalid JWT token");
            return null;
        }
    }


}