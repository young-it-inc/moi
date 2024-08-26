package com.youngit.office.api.photo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "사진 정보")
public class PhotoModel {
    String	operation_id; // 작업고유ID: AS_00000000000000000(17자리) / INSTL_00000000000000(14자리)
    String	operation_code; //작업고유코드
    String	is_corrected; //시정여부 Y/N
    String	file_save_path; //파일저장경로
    String	saved_file_name; //파일저장명
    String	original_file_name; //원본파일명
    String	file_extension; //파일확장자: jpg
    int	file_size; //파일크기
    String	correct_memo; //시정조치메모
    String	thumbnail_path; //썸네일경로
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp registered_at; //등록시점
    String	register_id; //등록자ID: USRCNFRM_00000000000(11자리)
}
