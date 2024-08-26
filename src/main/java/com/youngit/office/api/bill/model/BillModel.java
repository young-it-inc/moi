package com.youngit.office.api.bill.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "내역서")
public class BillModel {

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




    /*
    입금금액: 6,656,500원
    이월금액: 2,000,000원
    위캡으로 세금계산서 발행(2024-08-31) 이전: 6,656,500원(부가세 포함)

    1.합계표
    설치합계:
    a/s합계:
    전월미지급금:
    지원금:
    -----------지급합계
    택배비:
    pda통신비:
    pda기기비:
    ------------공제합계

    1-1 설치,유지보수 계약금액
    1-2 택배비 내역
    1-3 지원금 내역

    2. 시정조치(pda 확인필수) - 설치 시정은 익월 15일까지 완료 (미처리시 차월 공제)
    없음

    3.설치내역
   구분                  스타2.2           스타2.0, 맥, H         계
                     지역/수량/금액        지역/수량/금액        수량/금액
   신규설치,
   경과재설치,
   이전설치,
   하자보증, 
   하자보수환급(22년 7월),
   기타공제
   설치합계

    4. AS내역
   구분:                스타2.2         스타2.0, 맥, H       계
                   단가/수량/금액         단가/수량/금액      수량/금액
   [고정지역],
   검침기/단자재부착,
   정상/기타처리 외,
   이전설치시 제품철거(광주),
   기타공제
    AS합계

    총계

     */
}
