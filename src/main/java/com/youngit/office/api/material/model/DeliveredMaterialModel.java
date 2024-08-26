package com.youngit.office.api.material.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "출고 자재 정보")
public class DeliveredMaterialModel {
    String	deliveryUniqId; //출고고유ID : DLIVY_00000000000000(14자리)
    String	materialUniqId; //자재고유ID : MTRIL_00000000000000(14자리)
    int	materialQuantity; //자재수량
    int	deliveryUnitPrice; //출고단가
}
