package com.youngit.office.api.complaint.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "민원 정보")
public class ComplaintModel {

    String	complaintUniqId; //민원고유ID
    String	complaintCode; //민원구분코드
    String	complaintMajorCode; //민원대분류코드
    String	complaintMinorCode; //민원소분류코드
    String	clientUniqId; //거래처고유ID
    String	receiptDate; //접수일
    String	officeId; //지역코드
    String	petitionerName; //민원인명
    String	phoneNumber1; //연락처1
    String	phonNumber2; //연락처2
    String	complaintAcceptContent; //민원접수내용
    String	complaintHandleContent; //민원처리내용
    String	processCode; //처리상황코드
    String	receiptId; //접수자ID
    String  chargerId;//담당자고유ID
    String	complaintHandleDate; //민원처리일
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp createAt; //최초등록시점
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp updateAt; //최종수정시점
    String	createdById; //최초등록자ID
    String	updatedById; //최종수정자ID
    String	is_used; //사용여부 Y/N
}
