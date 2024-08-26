package com.youngit.office.api.contract.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "설치팀 계약 상세 정보")
public class InstallContractDetailModel {

    String	contractUniqNo; //고객계약고유번호
    String	clientUniqId; //거래처고유id
    long	installContractNo; //설치계약번호
    int	    installContractOrderNo; //설치계약순번
    String	contractDetailCode; //고객상세계약코드
    int	    contractDetailQuantity; //고객상세계약수량
}
