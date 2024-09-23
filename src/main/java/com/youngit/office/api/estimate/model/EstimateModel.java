package com.youngit.office.api.estimate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Schema(description = "견적서 정보: 기록이라기보다 발송,출력, 계약으로 넘어가는 기능?")
public class EstimateModel {


    String	estimateUniqNo; //견적서고유번호 202408130001(12자리)
    String	clientName; //거래처명
    String	estimateMain; //견적서주체(YOUNG)-계약서에는 견적서주체+견적서고유번호로 들어감.
    String	estimateDate; //견적일
    String	estimateNameCode; //견적명: 상용구로 저장해놓고 선택 가능하도록

    String	estimateMainEtc; //계약주체(ETC)_비고란
    String	estimateMethod; //견적방법
    String	estimateCode; //견적분류코드 CCT01,03,05,06(물품(검침기), 용역, 공사, 무상, 물품(PDA), 소모품, 시범))
    int	estimateQuantity; //총견적수량
    long	estimateAmount; //총견적금액
    String	estimateNote; //견적서특이사항


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp createAt; //최초등록시점
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp updateAt; //최종수정시점
    String createdByUserId; //최초등록자ID
    String updatedByUserId; //최종수정자ID
    String isUsed; //사용여부

    List<EstimateProductModel> estimateProductModelList; //견적서 품목리스트


    //추가
    String email; //수신측이메일
    //
    //계약여부
    String isContracted; //계약여부

    String	clientCode; //거래처구분코드 - 견적서 받고 구입 원할시
    String	clientUniqId; //거래처고유ID - 견적서 받고 구입 원할시
    String	contractUniqNo; //고객계약고유번호 - 견적서 받고 구입 원할시
}
