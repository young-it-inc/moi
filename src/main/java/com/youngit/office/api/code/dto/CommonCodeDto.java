package com.youngit.office.api.code.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "공통코드 정보 DTO")
public class CommonCodeDto {

    String	codeId; //코드ID
    String	codeIdName; //코드ID명
    String	codeIdDescription; //코드ID설명
    String	classifiedCode; //분류코드
    String createdByMemberUniqId; //최초등록자ID
    String updatedByMemberUniqId; //최종수정자ID
}
