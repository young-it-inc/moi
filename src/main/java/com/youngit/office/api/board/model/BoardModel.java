package com.youngit.office.api.board.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "게시판 정보")
public class BoardModel {

    int	postId; //게시물ID
    String	boardId; //게시판ID : BBSMSTR_000000000000(12자리)
    String	categoryId; //카테고리ID
    int	postNumber; //게시물번호
    String	postSubject; //게시물제목
    String	postContent; //게시물내용
    String	isNotice; //공지여부
    String	isSecret; //비밀글여부
    String	isReply; //답글여부
    int	parentPostNumber; //부모게시물번호
    int	replyLocation; //답글위치
    int	sortOrder; //정렬순서
    int	hitCount; //조회수
    String	postStartDate; //게시시작일 : 10000101 (8자리)
    String	postEndDate; //게시종료일 : 999991231 (8자리)
    String	posterId; //작성자ID
    String	posterName; //작성자명
    String	posterEmail; //작성자이메일
    String	posterPhoneNumber; //작성자전화번호
    String	password; //비밀번호
    char	attacedFileDd; //첨부파일ID : FILE_000000000000(15자리)
    String	etc1; //기타1
    String	etc2; //기타2
    String	etc3; //기타3
    String	etc4; //기타4
    String	etc5; //기타5
    String	etc6; //기타6

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp createAt; //최초등록시점
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp updateAt; //최종수정시점
    String createdByMemberUniqId; //최초등록자ID
    String updatedByMemberUniqId; //최종수정자ID
    String isUsed; //사용여부
}
