package com.youngit.office.api.warehousing.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "출고 정보")
class DeliveryModel {
    String	deliveryUniqId; //출고고유ID
    String	contractUniqNo; //고객계약고유번호
    String	clientUniqId; //거래처고유ID
    String	clientBuilderId; //시공사고유ID
    String	deliveryCode; //출고구분코드 : INSTL(설치)/ DVYFG(납품) /MNTNCE(유지)
    String	deliveryDate; //출고일
    String	deliveryMemo; //출고메모
    String	deliveryRequestDate; //출고요청일
    String	deliveryRequestUserUniqId; //출고요청자ID
    String	deliveryUserUniqId; //출고자ID

    //추가
    String deliveryNumber; //운송장번호

}
