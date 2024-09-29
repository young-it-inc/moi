package com.youngit.office.api.member.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Schema(description = "회원 정보. 삭제해도 데이터 보존>> 고유id 중복방지")
public class MemberModel {
    String	memberId; //* 회원ID
    String	password; //* 비밀번호

    //사용x
    //String password_question; //비밀번호질문
    //String password_answer; //비밀번호답변

    String	memberName; //* 회원명
    String	memberEmail; //회원이메일주소
    String	memberPhoneNumber; //회원전화번호

    String	memberStatus; //회원상태 : A(회원신청)/D(탈퇴)/P(승인)
    String	authorityCode; //권한코드 : ROLE_USER/ROLE_EMPLOYEE/ROLE_ADMIN +installer
    String	positionCode; //직책코드 : POS01/02/03/04/05/06/07/08/09 (대표이사/부사장/이사/팀장/과장/대리/주임/사원/수습)
    String	departmentCode; //부서코드 : PAT01/02/03/04(마케팅/연구개발/재무관리/P&QC팀)
    String	taskCode; //업무코드: TSK01/02/03/04/05/06/07/08(as설치/sw개발/hw개발/영업관리/생산관리/경영기획/생산조립/영업지원)
    String	typeCode; //회원유형코드: USR01(일반회원)/02(기업회원)/03(업무담당자)/99(관리자)

    //사용x
    //String group_id; //그룹ID : GROUP_00000000000000(14자리)

    String	memberUniqId; //auto 사용자고유ID : USRCNFRM_00000000000(11자리) (자동부여)

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp createdAt; //가입일시
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp updatedAt;

    String	cooperativeId; //협력업체ID: BCNC_000000000000000(15자리) ? 아니면 사업소코드? officeId


    String token; //토큰
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Date expriredAtToken; //토큰기한



}
