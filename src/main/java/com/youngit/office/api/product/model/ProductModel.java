package com.youngit.office.api.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "제품 정보")
public class ProductModel {

    String	productSerialNumber; //제품일련번호(고유) : 00-00-000000
    String	productDate; //제품생산일 : 20240704(8자리)
    String	materialUniqId; //자재고유ID : MTRIL_00000000000000(14자리)
    String	productStateCode; //제품상태코드 : PST01/02/03/04/05/99 (재고/출고/설치/회수/반납/폐기)
    int	deliveryUnitPrice; //출고단가
    String	disuseDate; //폐기일
    String	clientBuilderId; //시공사ID : BCNC_000000000000000(15자리)
    String	chagedStatusDate; //상태변경일 : 20240704(8자리)
    String	officeId; //관리사업소_지역_ID
    String	isStateUsed; //사용상태여부 : Y/N
    String	asUniqId; //A/S고유ID : AS_00000000000000000(17자리)
}
