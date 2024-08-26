package com.youngit.office.api.member.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "권한 롤 관계")
public class AuthorityRoleRelationshipModel {

    String	authorityCode; //권한코드
    String	roleCode; //롤코드
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp createdAt; //생성일시
}
