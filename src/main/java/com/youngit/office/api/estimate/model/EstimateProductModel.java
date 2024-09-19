package com.youngit.office.api.estimate.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "견적서 품목 정보")
public class EstimateProductModel {

    int	estimateProductUniqOrder; // 견적품목고유번호(order)
    String	estimateUniqNo; // 견적서고유번호
    int productOrder; // 품목순서
    String	materialUniqId; // 자재고유ID
    int	productQuantity; // 품목수량
    int	productUnitPrice; // 품목단가
    int productTotalPrice; // 품목별총액
}
