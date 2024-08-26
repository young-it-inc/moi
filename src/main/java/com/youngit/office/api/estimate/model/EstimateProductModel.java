package com.youngit.office.api.estimate.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "견적서 품목 정보")
public class EstimateProductModel {

    int	estimateProductUniqOrder; // 견적품목고유번호(order)
    String	estimateUniqNo; // 견적서고유번호
    String	deliveryCode; // 출고구분코드
    String	officeId; // 지역고유ID
    String	clientUniqId; // 거래처고유ID
    String	materialUniqId; // 자재고유ID
    int	productQuantity; // 품목수량
    int	productUnitPrice; // 품목단가
}
