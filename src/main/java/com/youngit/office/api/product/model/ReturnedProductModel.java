package com.youngit.office.api.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "반납 제품 정보")
public class ReturnedProductModel {

    String	returnUniqId; //반납고유ID : RTURN_00000000000000(14자리)
    String	productSerialNumber; //제품일련번호 : 00-00-000000
    String	returnCustomerMumber; //반납수용가번호
    String	materialUniqId; //자재고유ID : MTRIL_00000000000000(14자리)
    String	retrieveDate; //회수일자 : 20240704
    String	returnReason;  //반납사유 : 1001/1002/1003/1004/10051006/1012/1016/1029......
    String	asUniqId; //AS고유ID : AS_00000000000000000(17자리)
    char	isAnalysed; //분석여부 Y/N
    String	analysisReason; //분석불량사유 : 1003/1004/1005/1007/1016/1018/2003/.....
    String	analysisResult; //분석결과
}
