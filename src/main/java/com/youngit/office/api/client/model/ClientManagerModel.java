package com.youngit.office.api.client.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "거래처 담당자 정보")
public class ClientManagerModel {

    String	clientUniqId; //거래처고유ID
    int	managerNo; //담당자순번 >>자동부여
    String	managerName; //담당자명
    String	managerDepartment; //담당자부서
    String	managerPosition; //담당자직책
    String	managerPhoneNumber; //담당자연락처
    String	managerEmail; //담당자 이메일

}
