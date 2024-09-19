package com.youngit.office.api.as.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "AS 정보")
public class AsModel {
    String	asUniqId; //A/S고유ID : AS_00000000000000000(17자리)
    String	installUniqId; //설치고유ID : INSTL_00000000000000(14자리)
    String	clientBuilderId; //시공사고유ID : BCNC_000000000000000(15자리)
    int	assignNumber; //배정고유번호 : counting되는 숫자
    String	officeId; //지역고유ID (필수)
    String	customerNumber; //수용가번호(필수, 조회)
    String	customerName; //수용가성명(필수)
    String	customerAddress; //수용가주소 (필수)
    String	customerPhoneNumber; //수용가연락처
    String	asYearMonth; //A/S년월 202407
    int	asWeek; //A/S주차
    String	asReceiptDate; //A/S접수일 : 20240703(8자리)
    String	asRequestDate; //A/S처리요청일 : 20240703(8자리)
    String	asProcessDate; //A/S처리일 : 20240703(8자리)
    String	asCompleteDate; //A/S완료일 : 20240703(8자리)
    String	asContent; //A/S접수내용
    String	asProcessStatusCode; //A/S처리상태코드 : STT01(접수)/02(배정)/03(검수대기)/04(시정조치)/05(시정완료)/06(최종완료)
    String	asCauseCode; //A/S원인코드(48개) : 1001/1002/1003/1004/1005/1006/1007/1008/1009/1010 // 1011/1012/1013/1014/1015/1016/1017/1018/1019/1020 // 1021/1022/1023/1024/1025/1026/1027/1028/1029/1030
    // 1031/1032/1033/1034/1035/1036/1037/1038/1039/1040 // 2001/2002/2003/2004/2005/2006 / 7777/ 9999
    String	asCuaseSubCode; //A/S원인세부코드
    String	asMemberId; //A/S직원고유ID (주로 설치팀ID)
    String	asResult; //A/S결과
    String	asMemo; //A/S메모
    String	isReplaced; //대체등록여부 (Y/N)
    String	meterNumber; //계량기번호
    String	meterCaliber; //계량기구경
    String	meterReadingSerialNumber; //검침기일련번호
    String	meterReadingVersion; //검침기버전
    String	meterReadingLocation; //검침기위치
    String	terminalBoxSerialNumber; //단자함일련번호
    String	terminalBoxLocation; //단자함위치
    String	externalIndicatorSerialNumber; //외부표시기 일련번호
    String	oldCustomerNumber; //구수용가번호
    String	installEnvironmentCode; //설치환경코드 : IST01/02/03/04/05/06/07/08/09/10/11/12/13 (L자맨홀/상시침수/심도깊음/상시부재/상시주차/상시적재/오물/무거운철판/영업장내부/가정집내부/접근곤란/사생활보호요청수용가/취약계층수용가)
    String	cableStatusCode; //통신선상태코드 : CST01/02 (매립 / 미매립)
    String	beforeMeterReadingSerialNumber; //이전검침기 일련번호
    String	beforeTerminalBoxSerialNumber; //이전단자함 일련번호
    String	beforeExternalIndicatorSerialNumber; //이전외부표시기 일련번호
    String	cableLength; //통신선설치길이
    String	asEtc; //A/S비고
    Boolean	isExternalIndicatorInstalled; //외부표시기설치여부 (Y/N)
    Boolean	isEnclosureInstalled; //외함설치여부 (Y/N)
    String	asSignaturePath; //A/S서명이미지경로
    String	asBeforePath; //A/S전_지침사진경로
    String	asAfterPath; //A/S후_지침사진경로
    String	asEnvironmentPath; //A/S환경사진경로
    String	asBeforeMeterPath; //A/S전_계량기사진경로
    String	asAfterMeterPath; //A/S후_계량기사진경로
    String	asBeforeMeterReadingPath; //A/S전_검침기사진경로
    String	asAfterMeterReadingPath; //A/S후_검침기사진경로
    String	asBeforeTerminalBoxPath; //A/S전_단자함사진경로
    String	asAfterTerminalBoxPath; //A/S후_단자함사진경로
    String	asBeforeCablePath; //A/S전_통신선사진경로
    String	asAfterCablePath; //A/S후_통신선사진경로
    int	correctCount; //시정횟수
    String	metermanName; //검침원성명
    String	metermanPhoneNumber; //검침원전화번호
    Boolean	isDeducted; //공제여부 (Y/N)
    String	deductionId; //공제대상자ID (주로 설치팀ID, 단, 위의 a/s직원고유id와 같을수도,다를수도 있음.)  //???
    String	deductionReason; //공제사유
    String payApplyDate; //결제반영일? //???
    String	receiptNumber; //접수번호
    String	tempProductionDate; //임시제품생산일
    String	asCode; //유지관리구분코드
    String	customerPhoneNumber2; //수용가연락처2
    String	deductionYearMonth; //공제년월
    int	deductionAmount; //공제금액
    String	beforeInstallUniqId; //이전설치고유ID
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp inspectionDate; //검수일

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp createAt; //최초등록시점
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp updateAt; //최종수정시점
    String createdByMemberUniqId; //최초등록자ID
    String updatedByMemberUniqId; //최종수정자ID
    String isUsed; //사용여부
}
