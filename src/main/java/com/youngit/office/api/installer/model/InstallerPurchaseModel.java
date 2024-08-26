package com.youngit.office.api.installer.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "설치팀 장비구입 계약서 정보")
public class InstallerPurchaseModel {

    /*
    String clientUniqId; //거래처아이디 - 거래처명, 사업자번호, 대표자, 주소
    String installerUniqNo //설치팀최초계약번호
    int contractDate; //계약날짜 20240826
    String installerPurchaseUniqNo //설치팀장비구입계약번호

    String deductionAmount; // 공제 금액
    String deductionMonths; //공제 개월

    기기명+수량+금액(변동)+월공제액+공제개월+분실여부 (분실시 금액공제)
     */

}
