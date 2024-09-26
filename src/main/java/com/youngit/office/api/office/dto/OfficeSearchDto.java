package com.youngit.office.api.office.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "사업소 검색 DTO")
public class OfficeSearchDto {

    String searchCode;
    String keyword;
}
