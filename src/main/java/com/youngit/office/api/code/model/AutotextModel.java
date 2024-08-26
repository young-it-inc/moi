package com.youngit.office.api.code.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "상용구 텍스트 정보")
class AutotextModel {
    int	textUniqNumber; //상용구고유번호
    String	textType; //텍스트구분
    int	sortNumber; //정렬번호
    String	textName; //텍스트명
    String	textContent; //텍스트내용
}
