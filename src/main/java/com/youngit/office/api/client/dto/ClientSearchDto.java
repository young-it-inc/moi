package com.youngit.office.api.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "거래처 검색 DTO")
public class ClientSearchDto {
    String searchCode; //거래처명 or 대표자명
    String searchKeyword; //검색어
}
