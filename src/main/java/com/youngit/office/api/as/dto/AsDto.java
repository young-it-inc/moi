package com.youngit.office.api.as.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "AS 정보 DTO")
public class AsDto {

    @Schema(description = "A/S고유ID")
    private String asUniqId;

    @Schema(description = "설치고유ID")
    private String installUniqId;

    @Schema(description = "시공사고유ID")
    private String clientBuilderId;

    @Schema(description = "배정고유번호")
    private int assignNumber;

    @Schema(description = "지역고유ID")
    private String officeId;

    @Schema(description = "수용가번호")
    private String customerNumber;

    @Schema(description = "수용가성명")
    private String customerName;

    @Schema(description = "수용가주소")
    private String customerAddress;

    @Schema(description = "수용가연락처")
    private String customerPhoneNumber;

    @Schema(description = "A/S년월")
    private String asYearMonth;

    @Schema(description = "A/S주차")
    private int asWeek;

    @Schema(description = "A/S접수일")
    private String asReceiptDate;

    @Schema(description = "A/S처리요청일")
    private String asRequestDate;

    @Schema(description = "A/S처리일")
    private String asProcessDate;

    @Schema(description = "A/S완료일")
    private String asCompleteDate;

    @Schema(description = "A/S접수내용")
    private String asContent;

    @Schema(description = "A/S처리상태코드")
    private String asStateCode;

    @Schema(description = "A/S원인코드")
    private String asCauseCode;

    @Schema(description = "A/S원인세부코드")
    private String asCauseSubCode;

    @Schema(description = "A/S직원고유ID")
    private String asMemberId;

    @Schema(description = "A/S결과")
    private String asResult;

    @Schema(description = "A/S메모")
    private String asMemo;

    @Schema(description = "대체등록여부")
    private String isReplaced;

    @Schema(description = "계량기번호")
    private String meterNumber;

    @Schema(description = "계량기구경")
    private String meterCaliber;

    @Schema(description = "검침기일련번호")
    private String meterReadingSerialNumber;

    @Schema(description = "검침기버전")
    private String meterReadingVersion;

    @Schema(description = "검침기위치")
    private String meterReadingLocation;

    @Schema(description = "단자함일련번호")
    private String terminalBoxSerialNumber;

    @Schema(description = "단자함위치")
    private String terminalBoxLocation;

    @Schema(description = "외부표시기 일련번호")
    private String externalIndicatorSerialNumber;

    @Schema(description = "구수용가번호")
    private String oldCustomerNumber;

    @Schema(description = "설치환경코드")
    private String installEnvironmentCode;

    @Schema(description = "통신선상태코드")
    private String cableStatusCode;

    @Schema(description = "이전검침기 일련번호")
    private String beforeMeterReadingSerialNumber;

    @Schema(description = "이전단자함 일련번호")
    private String beforeTerminalBoxSerialNumber;

    @Schema(description = "이전외부표시기 일련번호")
    private String beforeExternalIndicatorSerialNumber;

    @Schema(description = "통신선설치길이")
    private String cableLength;

    @Schema(description = "A/S비고")
    private String asEtc;

    @Schema(description = "외부표시기설치여부")
    private Boolean isExternalIndicatorInstalled;

    @Schema(description = "외함설치여부")
    private Boolean isEnclosureInstalled;

    @Schema(description = "A/S서명이미지경로")
    private String asSignaturePath;

    @Schema(description = "A/S전_지침사진경로")
    private String asBeforePath;

    @Schema(description = "A/S후_지침사진경로")
    private String asAfterPath;

    @Schema(description = "A/S환경사진경로")
    private String asEnvironmentPath;

    @Schema(description = "A/S전_계량기사진경로")
    private String asBeforeMeterPath;

    @Schema(description = "A/S후_계량기사진경로")
    private String asAfterMeterPath;

    @Schema(description = "A/S전_검침기사진경로")
    private String asBeforeMeterReadingPath;

    @Schema(description = "A/S후_검침기사진경로")
    private String asAfterMeterReadingPath;

    @Schema(description = "A/S전_단자함사진경로")
    private String asBeforeTerminalBoxPath;

    @Schema(description = "A/S후_단자함사진경로")
    private String asAfterTerminalBoxPath;

    @Schema(description = "A/S전_통신선사진경로")
    private String asBeforeCablePath;

    @Schema(description = "A/S후_통신선사진경로")
    private String asAfterCablePath;

    @Schema(description = "시정횟수")
    private int correctCount;

    @Schema(description = "검침원성명")
    private String metermanName;

    @Schema(description = "검침원전화번호")
    private String metermanPhoneNumber;

    @Schema(description = "공제여부")
    private Boolean isDeducted;

    @Schema(description = "공제대상자ID")
    private String deductionId;

    @Schema(description = "공제사유")
    private String deductionReason;

    @Schema(description = "결제반영일")
    private String date;

    @Schema(description = "접수번호")
    private String receiptNumber;

    @Schema(description = "임시제품생산일")
    private String tempProductionDate;

    @Schema(description = "유지관리구분코드")
    private String asCode;

    @Schema(description = "수용가연락처2")
    private String customerPhoneNumber2;

    @Schema(description = "공제년월")
    private String deductionYearMonth;

    @Schema(description = "공제금액")
    private int deductionAmount;

    @Schema(description = "이전설치고유ID")
    private String beforeInstallUniqId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    @Schema(description = "검수일")
    private String inspectionDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    @Schema(description = "최초등록시점")
    private String createAt;



    @Schema(description = "최초등록자ID")
    private String createdByMemberUniqId;

    @Schema(description = "최종수정자ID")
    private String updatedByMemberUniqId;


}
