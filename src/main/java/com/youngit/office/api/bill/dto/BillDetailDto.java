package com.youngit.office.api.bill.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "내역서 상세 정보 DTO")
public class BillDetailDto {
    String	clientUniqId;
    String	workMonth; //
    String	asBillId; //
    String	officeId;
    String	workCode; // 00/10/AS
    String	workSubCode; //
    String	workStartDate; //20240703 (8자리)
    String	workEndDate; //20240703 (8자리)
    int	efosPrice;
    int	efosCount;
    int	efosAmount;
    int	omrPrice;
    int	omrCount;
    int	omrAmount;
    int	efosDeductionAmount;
    int	omrDeductionAmount;
}
