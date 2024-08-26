package com.youngit.office.api.contract.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "설치팀계약 정보")
class InstallContractModel {
    String	contractUniqNo; //고객계약고유번호
    String	clientUniqId; //거래처고유ID
    long	installContractNo; //설치계약번호
    String	clientBuilderId; //시공사고유ID
    String	officeId; //지역고유ID
    String	installContractDate; //설치계약일
    String	installCompleteDate; //설치완료일
    int	teamContractQuantity; //팀별계약수량
    String	installCompleteScheduledDate; //설치완료예정일

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp createAt; //최초등록시점
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp updateAt; //최종수정시점
    String createdByMemberUniqId; //최초등록자ID
    String updatedByMemberUniqId; //최종수정자ID
    String	is_used; //사용여부
}
