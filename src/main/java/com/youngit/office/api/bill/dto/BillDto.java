package com.youngit.office.api.bill.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "설치팀 내역서 정보 DTO")
public class BillDto {

    String	clientUniqId; //거래처고유ID :팀명: 가월설비
    char workMonth; //결재월 (처리월) : 2024년 7월
    String memberUniqId; //작성자 : 박지혜

    int installAmount; //설치합계금액
    int asAmount; //AS합계금액
    int carryoverAmount; //이월금액
    int supportAmounts; //지원금액
    int payTotalAmount; //지급합계

    int deliveryFee; //택배비
    int pdaCommunicationFee; //PDA통신비
    int pdaDeviceFee;  //PDA기기비
    int deductionTotalFee; //공제합계



    int	workOrder; //결제순번
    String workStartDate; //결제시작일
    String workEndDate; //결제종료일
    String workCode; //작업구분코드 : 00/20/AS
    String workName; //작업구분명 : 신규설치/경과재설치/AS
    String workSubCode; //세부작업구분코드 : AS001- AS033?
    String workSubName; //세부작업구분명
    int	efosPrice; //EFOS가격
    int	efosCount; //EFOS건수
    int	efosAmount; //EFOS 금액
    int	omrPrice; //OMR가격
    int	omrCount; //OMR건수
    int	omrAmount; //OMR금액
    int	efosDeductionAmount; //EFOS공제금액
    int	omrDeductionAmount; //OMR공제금액
    int	deductionAmount; //공제금액
    int	warrantyAmount; //하자보증금액
    int	supportAmount; //지원금액
    int	totalAmount; //총금액
    String	paymentDate; //결제일
    int	paymentAmount; //결제금액

}
