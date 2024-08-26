package com.youngit.office.api.member.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "롤 정보")
public class RoleInfoModel {

    String	roleCode; //롤코드
    String	roleName; //롤명
    String	rolePattern; //롤패턴
    String	roleDescription; //롤설명
    String	roleType; //롤타입
    String	roleSort; //롤정렬
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp createdAt; //롤생성일
}
