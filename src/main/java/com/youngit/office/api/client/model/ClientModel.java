package com.youngit.office.api.client.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Schema(description = "거래처 정보. 삭제해도 db에는 남아 있을 것.")
public class ClientModel {
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


    /* 사용x
    기존 db에서도 모두 null인 것으로 보아 담당자를 추가해도 해당 컬럼에 저장되는게 아니라 o_client_manager(clientManagerModel)에 저장됨.
    String managerName; //담당자명
    String managerDepartment; //담당자부서
    String managerPosition; //담당자직책
    String managerPhoneNumber; //담당자연락처
    String managerEmail; //담당자이메일
    */
    List<ClientManagerModel> clientManagerModelList; //거래처 담당자 리스트

    String bankName; //은행명
    String accountHolder; //예금주
    String accountNumber; //계좌번호

    String note; //비고내용

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp createAt; //최초등록시점
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp updateAt; //최종수정시점
    String createdByMemberUniqId; //최초등록자ID >>웹상에 출력되게끔.
    String updatedByMemberUniqId; //최종수정자ID

    String isUsed; //사용여부 Y/N

}
