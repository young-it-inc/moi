package com.youngit.office.api.installer.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "설치팀 최초 계약 정보")
public class InstallerModel {

    String clientUniqId; //거래처아이디 - 거래처명, 사업자번호, 대표자, 주소
    String installerUniqNo; //설치팀최초계약번호
    int contractDate; //계약날짜 20240826
    int contractPeriodStart; //계약시작날짜 20240827
    int contractPeriodEnd; //계약종료날짜 20250826
    int contractAmount; //계약금액(월단위)
   // int installerCost; //설치단가 (숙련에 따라 상이)
    boolean isAsFixedTeam; //고정팀유무
    List<InstallerAreaModel> installerAreaModelList; //고정지역 (복수개 가능)





}
