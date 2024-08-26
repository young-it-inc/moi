package com.youngit.office.api.code.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "공통코드 상세 정보")
public class CommonDetailCodeModel {

    String	codeId; //코드ID
    String	code; //코드
    String	codeName; //코드명
    String	codeDescription; //코드설명
    String	isUsed; //사용여부 Y/N

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp createAt; //최초등록시점
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp updateAt; //최종수정시점
    String createdByMemberUniqId; //최초등록자ID
    String updatedByMemberUniqId; //최종수정자ID
}
