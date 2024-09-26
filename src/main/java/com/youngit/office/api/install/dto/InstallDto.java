package com.youngit.office.api.install.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "설치 정보 DTO")
public class InstallDto {

    String	installUniqId; //설치고유ID : INSTL_00000000000000 14자리
    String	contractUniqNo; //고객계약고유번호 : 202408130001 12자리
    String	clientUniqId; //거래처고유ID : BCNC_000000000000000 15자리
    String	subClientUniqId; //부거래처고유ID : 보통 null
    int	installContractNo; //설치계약번호
    String	clientBuilderId; //시공사고유ID : BCNC_000000000000000 15자리
    String	officeId; //지역고유ID :office코드
    String	customerNumber; //수용가번호
    String	customerName; //수용가명
    String	customerAddress; //수용가주소
    String	customerPhoneNumber; //수용가연락처
    String	meterNumber; //계량기 번호
    String	meterCaliber; //계량기 구경
    String	installReceiptDate; //설치접수일 : 20240813(8자리)
    String	installDate; //설치일: 20240813(8자리)
    String	installCompleteDate; //설치완료일
    String	installStateCode; //설치상태코드 STT 01/02/03/04/05/06 (접수/배정/검수대기/시정조치/시정완료/최종완료) 99?
    String	isImpossible; //설치불가여부 Y/N
    String	installImpossibleDate; //설치불가일: 20240813(8자리)
    String	installImpossibleCode; //설치불가구분코드 IT01/02/03
    String	installImpossibleReason; //설치불가사유
    String	meterReadingSerialNumber; //검침기 일련번호 : 24-07-400584
    String	meterReadingVersion; //검침기 버전
    String	meterReadingLocation; //검침기 위치
    String	terminalBoxSerialNumber; //단자함 일련번호 : 37-20-400584
    String	terminalBoxLocation; //단자함 위치
    String	closeFieldDate; //폐전일자
    String	oldCustomerNumber; //구수용가번호
    String	installEnvironmentCode; //설치환경코드: IST : 01/02/03/04/05/06/07/08/09/10/11/12/13 (L자맨홀/상시침수/심도깊음/상시부재/상시주차/상시적재/오물/무거운철판/영업장내부/가정집내부/접근곤란/사생활보호요청수용가/취약계층수용가)
    String	cableStatusCode; //통신선상태코드: CST01/02 (매립 / 미매립)
    String	cableLength; //통신선 설치길이
    String	externalIndicatorSerialNumber; //외부표시기 일련번호
    String	isExternalIndicatorInstalled; //외부표시기 설치여부 Y/N
    String	isEnclosureInstalled; //외함 설치여부 Y/N
    String	etc; //비고내용
    String	workerMemberId; //작업자고유ID : memberId
    String	workerName; //작업자명
    double	latitude; //위도
    double	longitude; //경도
    String	installSignaturePath; //서명이미지 경로
    String	installGuildelinePath; //설치지침사진경로
    String	installEnvironmentPath; //설치환경사진경로
    String	installMeterPath; //계량기사진경로
    String	installMeterReadingPath; //검침기설치사진경로
    String	installTerminalBoxPath; //단자함설치사진경로
    String	installCablePath; //통신선설치사진경로
    String	installImpossiblePath; //설치불가사진경로
    String	installPolePath; //전주사진경로
    int	numberOfCorrection; //시정횟수
    String	metermanName; //검침원명
    String	metermanPhoneNumber; //검침원전화번호
    int	asCount; //A/S횟수
    String	installAgreeDate; //설치동의일

    String	createdById; //최초등록자ID : USRCNFRM_00000000000 (11자리)
    String	updatedById; //최종수정자ID : USRCNFRM_00000000000 (11자리)

    String	productProductionDate; //제품생산일(임시이관용)
    String	installDivisionCode; //설치구분코드
    String	isDeducted; //공제여부 Y/N
    String	deductionYearMonth; //공제년월
    int	deductionAmount; //공제금액
    String	dedctionReason; //공제사유

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp	inspectionDate; //검수일
}