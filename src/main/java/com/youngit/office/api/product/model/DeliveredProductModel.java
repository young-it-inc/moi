package com.youngit.office.api.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "출고 제품 정보")
public class DeliveredProductModel {
    String	deliveryUniqId; //출고고유ID : DLIVY_00000000000000(14자리)
    String	productSerialNumber; //제품일련번호 : 00-00-000000
}
