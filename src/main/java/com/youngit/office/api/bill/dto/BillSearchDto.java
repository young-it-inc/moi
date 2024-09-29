package com.youngit.office.api.bill.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "설치팀 내역서 검색 DTO")
public class BillSearchDto {
    String searchKey;
    String searchValue;
}
