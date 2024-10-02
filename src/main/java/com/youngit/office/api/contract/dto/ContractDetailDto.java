package com.youngit.office.api.contract.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "계약 상세 정보 DTO")
public class ContractDetailDto {
    String	contractDetailCode; //고객 상세계약 코드
    int	contractDetailQuantity; //고객 상세계약 수량
}
