package com.youngit.office.api.warehousing.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "입출고 정보 DTO")
public class WarehousingDto {

    String	registerDate; //등록일
    String	clientBuilderId; //시공사고유ID
    String	materialUniqId; //자재고유ID
    String	officeId; //사업소ID
    String	deliveryReturnId; //출고반납ID
    String	productStateCode; //제품상태코드
    String	installTypeCode; //설치타입코드
    int	totalQuantity; //총수량
}
