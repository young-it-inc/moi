package com.youngit.office.api.board.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "게시판 정보")
public class BoardModel {

    int	post_id; //게시물ID
    String	board_id; //게시판ID : BBSMSTR_000000000000(12자리)
    String	category_id; //카테고리ID
    int	post_number; //게시물번호
    String	post_subject; //게시물제목
    String	post_content; //게시물내용
    String	is_notice; //공지여부
    String	is_secret; //비밀글여부
    String	is_reply; //답글여부
    int	parent_post_number; //부모게시물번호
    int	reply_location; //답글위치
    int	sort_order; //정렬순서
    int	hit_count; //조회수
    String	post_start_date; //게시시작일 : 10000101 (8자리)
    String	post_end_date; //게시종료일 : 999991231 (8자리)
    String	poster_id; //작성자ID
    String	poster_name; //작성자명
    String	poster_email; //작성자이메일
    String	poster_phone_number; //작성자전화번호
    String	password; //비밀번호
    char	attaced_file_id; //첨부파일ID : FILE_000000000000(15자리)
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
