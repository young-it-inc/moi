package com.youngit.office.api.contract.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "계약 상세 정보")
public class ContractDetailModel {
    String	contractUniqNo; //고객계약고유번호
    String	clientUniqId; //거래처고유id
    int	contractDetailCodeNo; //코드 순번
    String	contractDetailCode; //고객 상세계약 코드
    int	contractDetailQuantity; //고객 상세계약 수량
}
