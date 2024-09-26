package com.youngit.office.api.install.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "설치 검색 DTO")
public class InstallSearchDto {


    String installStateCode;

    String dateType; //날짜검색타입 (접수일/설치일/완료일)
    String startDate; //검색시작일
    String endDate; //검색종료일


    String contractNo; //계약번호
    String keyword; //검색구분 검색어
}
