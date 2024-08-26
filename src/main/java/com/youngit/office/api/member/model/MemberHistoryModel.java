package com.youngit.office.api.member.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "회원 변경 이력 정보")
public class MemberHistoryModel {

    String	memberId; //회원ID
    int	modificationNumber; //변경 이력 번호
    String	modificationType; //변경 유형: CHG 01/02/03/04 (정보변경/비밀번호변경/회원탈퇴/회원승인)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp modificatedAt; //변경시점
}
