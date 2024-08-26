package com.youngit.office.api.install.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "설치 로그 정보")
class InstallLogModel {

    String	installUniqId; //설치고유ID
    String	historyDate; //이력시점
    String	historyCode; //이력구분
    String	contractUniqNo; //고객계약고유번호
    String	clientUniqId; //거래처고유ID
    String	subClientUniqId; //부거래처ID
    int	installContractNo; //설치계약번호
    String	clientBuilderId; //시공사고유ID
    String	officeId; //지역고유ID
    String	customerNumber; //수용가번호
    String	customerName; //수용가명
    String	customerAddress; //수용가주소
    String	customerPhoneNumber; //수용가연락처
    String	meterNumber; //계량기 번호
    String	meterCaliber; //계량기 구경
    String	installReceiptDate; //설치접수일
    String	installDate; //설치일
    String	installCompleteDate; //설치완료일
    String	installStateCode; //설치상태코드
    String	isImpossible; //설치불가여부
    String	installImpossibleDate; //설치불가일
    String	installImpossibleCode; //설치불가구분코드
    String	installImpossibleReason; //설치불가사유
    String	meterReadingSerialNumber; //검침기일련번호
    String	meterReadingVersion; //검침기버전
    String	meterReadingLocation; //검침기위치
    String	terminalBoxSerialNumber; //단자함 일련번호
    String	terminalBoxLocation; //단자함 위치
    String	closeFieldDate; //폐전일자
    String	oldCustomerNumber; //구수용가번호
    String	installEnvironmentCode; //설치환경코드
    String	cableStatusCode; //통신선상태코드
    String	cableLength; //통신선설치길이
    String	externalIndicatorSerialNumber; //외부표시기일련번호
    String	isExternalIndicatorInstalled; //외부표시기설치여부
    String	isEnclosureInstalled; //외함 설치여부
    String	etc; //비고내용
    String	worker_memberId; //작업자고유ID
    String	workerName; //작업자명
    double	latitude; //위도
    double	longitude; //경도
    String	installSignaturePath; //서명이미지경로
    String	installGuildelinePath;
    String	installEnvironmentPath;
    String	installMeterPath;
    String	installMeterReadingPath;
    String	installTerminalBoxPath;
    String	installCablePath;
    String	installImpossiblePath;
    String	installPolePath;
    int	numberOfCorrection; //시정횟수
    String	metermanName; //검침원명
    String	metermanPhoneNumber; //검침원연락처
    int	asCount; //AS횟수
    String	installAgreeDate; //설치동의일

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp createAt; //최초등록시점
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp updateAt; //최종수정시점
    String createdByMemberUniqId; //최초등록자ID
    String updatedByMemberUniqId; //최종수정자ID
}
