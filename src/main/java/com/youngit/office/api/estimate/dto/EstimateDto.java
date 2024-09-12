package com.youngit.office.api.estimate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "견적서 DTO")
public class EstimateDto {

    String	clientName; //거래처명
    String	estimateUniqNo; //견적서고유번호 202408130001(12자리)
    String	estimateMain; //견적서주체 - 계약서에는 견적서주체+견적서고유번호로 들어감.
    String	estimateDate; //견적일
    String	estimateName; //견적명 : 상용구로 저장해놓고 선택 가능하도록

    String	estimateMainEtc; //계약주체(ETC)_비고란
    String	estimateMethod; //견적방법
    String	estimateCode; //견적분류코드
    int	estimateQuantity; //견적수량
    int	estimateAmount; //견적금액
    String	estimateNote; //견적서특이사항

    String	clientCode; //거래처구분코드 - 견적서 받고 구입 원할시
    String	clientUniqId; //거래처고유ID - 견적서 받고 구입 원할시
    String	contractUniqNo; //고객계약고유번호 - 견적서 받고 구입 원할시

    String createdByUserId; //최초등록자ID
    String updatedByUserId; //최종수정자ID

    List<EstimateProductDto> estimateProductDtoList; //견적서 품목리스트

    //추가
    String   estimateStatus; //견적서상태 (견적-계약-입금확인-출고요청-출고-세금계산서-배송완료?)
}
