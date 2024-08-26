package com.youngit.office.api.token.mapper;

import com.youngit.office.api.token.model.TokenModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface TokenMapper {
    int insertToken (TokenModel tokenModel);
    int updateToken(TokenModel tokenModel);
    int deleteToken(String token);

    Boolean isTokenExist(String token);

    Date getExpireToken(String token);

    TokenModel tokenValidation(String token);
}
