package com.youngit.office.api.office.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "사업소 정보")
public class OfficeModel {
    String officeId; //지역고유ID
    String officeMajorCode; //지역대분류코드
    String officeMinorCode; //지역소분류코드
    String officeName; //지역명
    int sortNo; //정렬번호
    String isUsed; //사용여부 Y/N

    //추가해야할 것 (20240826)
    String meterReadDate; //검침일 (매월 1- 최대 4일까지 선택)
    List<String> yitManager; //yit담당자 (최대5명까지 선택)
    String asListReceptionDate; // as명단접수일 (매월 1- 최대 4일까지 선택)
}
