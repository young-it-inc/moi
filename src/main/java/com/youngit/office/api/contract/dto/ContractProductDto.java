package com.youngit.office.api.contract.dto;

public class ContractProductDto {

    long	productUniqNo; //품목고유번호 : db에서 자동으로 순번 생성시킬것
    String	contractUniqNo; //고객계약고유번호
    String	deliveryCode; //출고구분코드 : INSTL(설치), DVYFG(납품)만 있음 (MNTNCD(유지보수)는 x)
    String	officeId; //지역고유ID
    String	clientUniqId; //거래처고유ID : BCNC_000000000000000(15자리)
    String	materialUniqId; //자재고유ID  : MTRIL_00000000000000(14자리)
    int	productQuantity; //품목수량
    long	productUnitPrice; //품목단가

}
