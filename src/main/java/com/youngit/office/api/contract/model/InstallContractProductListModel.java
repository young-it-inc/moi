package com.youngit.office.api.contract.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "설치팀 계약 품목 정보")
class InstallContractProductListModel {

    long	installProductUniqNo; //설치팀계약품목고유번호
    String	contractUniqNo; //고객계약고유번호
    String	clientUniqId; //거래처고유ID
    long	installContractNo; //설치계약번호
    String	clientBuilderId; //시공사고유ID
    String	materialUniqId; //자재고유ID
    int	productQuantity; //품목수량
    long	productUnitPrice; //품목단가
}
