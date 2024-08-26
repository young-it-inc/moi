package com.youngit.office.api.member.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "권한 정보")
public class AuthorityInfoModel {

    String	authorityCode; //권한코드
    String	authorityName; //권한명
    String	authorityDescription; //권한설명
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp authorityCreatedAt; //권한생성일
}
