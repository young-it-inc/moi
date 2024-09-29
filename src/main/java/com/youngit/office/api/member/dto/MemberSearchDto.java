package com.youngit.office.api.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "회원 검색 DTO")
public class MemberSearchDto {
    String department; //부서구분
    String searchKey; //분류선택: ID, 이름, 전화번호
    String searchValue; //검색어
}
