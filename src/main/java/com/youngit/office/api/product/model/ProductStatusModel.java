package com.youngit.office.api.product.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "제품상태이력 정보")
public class ProductStatusModel {
    int	productHistoryNumber; //제품이력번호
    String	productSerialNumber; //제품일련번호
    String	assetSortCode; //자산분류코드
    String	productSortCode; //품목분류코드
    String	materialUniqId; //자재고유ID
    String	clientUniqId; //거래처고유ID
    String	customerNumber; //수용가번호
    String	productStateCode; //제품상태코드
    String	asCauseCode; //AS원인코드
    String	asCauseSubCode; //AS원인부코드

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp registeredAt; //등록시점
}
