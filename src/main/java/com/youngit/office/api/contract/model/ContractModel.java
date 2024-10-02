package com.youngit.office.api.contract.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youngit.office.api.contract.ContractStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Schema(description = "계약 정보")
public class ContractModel {

    ContractStatus contractStatus; //진행상태 : 공란/설치중/준공임박/설치완료/검수요청/최종완료


    //조회시 보여져야하는 것들: 진행상태 / 사업팀 / 거래처명 / 계약분류 / 계약방법/ 계약번호/계약명/모델분류/계약수량/설치수량/잔여수량/계약일/완료예정일/검수요청일/계약금액/수정/삭제/청구서/청구여부/미청구금액/합계수량?
    //검색 필터: 팀별로 계약, 제품 조회, YIT관리자로 조회(본인관리지역만)


    String	contractUniqNo; //고객계약고유번호(자동생성) 202407020001(12자리)
    String	clientUniqId; //거래처고유ID BCNC_000000000000000 (15자리)
    String	contractMain; //계약주체 : YOUNG / WICAP / MIR / ETC >> YOUNG-IT / YIT임시계약 (체크박스: 선처리/무상/기타-기타선택시 사유작성)
    String	orderingContract_no; //발주처계약번호
    String	contractName; //계약명
    String	contractMethod; //계약방법 : GY001/002/003/004/005/006/007/008/009 (수요기관전자수의 / 수요기관서면수의 / 수요기관서면지출 / 수요기관일반경쟁 / 조달청수의 / 조달청일반경쟁 / 일반청구 / 수요기관벤처나라 / 제3자 단가계약(종합쇼핑몰))
    String	contractCode; //계약분류코드 : CCT 01/02/03/04/05/06 (물품(검침기)/용역/공사/무상/물품(PDA)/소모품) >> 물품(옥외/원격/PDA/소모품)/용역/공사/시범/무상
    int	contractQuantity; //계약수량

    String	contractDate; //계약일 *1
    String	openingDate; //착수일 *3
    String	approximateDate; //완료예정일 *4
    String	dueDate; //완료일 *5

    int	contractAmount; //계약금액
    String	contractMainEtc; //계약주체(ETC)_비고란
    String	incomingDate; //입고일
    String	deliveryDate; //출고일
    String	deliveryRequestDate; //출고요청일
    String	trackingNo; //송장번호


    String	billingDate; //청구일 *7
    String	isBilled; //청구여부

    String	firstBillingDate; //1차 청구일
    int	firstBillingAmount; //1차 청구금액
    String	secondBillingDate; //2차 청구일
    int	secondBillingAmount; //2차 청구금액
    String	thirdBillingDate; //3차 청구일
    int	thirdBillingAmount; //3차 청구금액
    String	fourthBillingDate; //4차 청구일
    int	fourthBillingAmount; //4차 청구금액

    String	isGuaranted; //계약보증유무 Y/N
    String	guarantyDate; //계약보증일 *2
    Boolean	isWarranted; //하자보증유무
    String	warrantyDate; //하자보증일 *6

    String	taxInvoiceDate1; //1차 세금계산서발행일
    String	taxInvoiceDate2; //2차 세금계산서발행일
    String	taxInvoiceDate3; //3차 세금계산서발행일
    String	taxInvoiceDate4; //4차 세금계산서발행일
    String	bondIssueDate1; //1차 공채발행일
    String	bondIssueDate2; //2차 공채발행일
    String	bondIssueDate3; //3차 공채발행일
    String	bondIssueDate4; //4차 공채발행일
    String	etc; //비고

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp createAt; //최초등록시점
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp updateAt; //최종수정시점
    String createdByMemberUniqId; //최초등록자ID
    String updatedByMemberUniqId; //최종수정자ID
    String isUsed; //사용여부

    List<ContractDetailModel> contractDetailList; //계약상세정보

    List<ContractProductModel> contractProductList; //계약제품정보

    //추가
    String contractManager; //계약별 관리자 (진척상황 관리 필요)

    String team; //사업팀: 사업1팀, 사업2팀, 사업3팀...

}

