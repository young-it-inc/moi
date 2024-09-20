package com.youngit.office.api.estimate.dto;

public class EstimateSearchDto {

    String	clientName; //거래처명
    String	estimateUniqNo; //견적서고유번호 202408130001(12자리)
    String	estimateDate; //견적일
    String	estimateName; //견적명 : 상용구로 저장해놓고 선택 가능하도록

    String createdByUserId; //최초등록자ID
    String updatedByUserId; //최종수정자ID

    //추가
    String email; //수신측이메일
    //
    //계약여부
    String isContracted; //계약여부

}
