package com.youngit.office.api.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "거래처 정보 DTO")
public class ClientDto {

    String searchOption;
    String searchData;
    String sqlQuery;

    public ClientDto() {
        this.searchOption = "";
        this.searchData = "";
        this.sqlQuery = "";
    }


    String clientUniqId; //auto 거래처고유ID: BCNC_000000000000000 (15자리) >>자동생성

    String clientTypeCode; //* 거래처구분코드(필수): BSE01(지자체)/02(일반수용가)/03(설치협력업체)/04(생산관련업체)/05(개발관련업체)/06(금융기관)/07(총무관련업체)/08(일반사업자)/09(개인)
    String businessRegistrationNumber; //사업자등록번호 (중복검사 필요)
    String clientName; //* 거래처명(필수)
    String representativeName; //* 대표자명(필수)
    String address; //주소
    String businessType; //업태
    String businessCategory; //종목
    String phoneNumber; //전화번호
    String faxNumber; //팩스번호
    String email; //대표이메일
    String item; //거래품목
    String waterSupplyCode; //수도구분코드: WTR01/02 (상수/지하수)

    List<ClientManagerDto> clientManagerDtoList; //거래처 담당자 리스트

    String bankName; //은행명
    String accountHolder; //예금주
    String accountNumber; //계좌번호

    String note; //비고내용

    String createdByMemberUniqId; //최초등록자ID >>웹상에 출력되게끔.
    String updatedByMemberUniqId; //최종수정자ID




}
