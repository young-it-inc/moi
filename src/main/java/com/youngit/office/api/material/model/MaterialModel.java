package com.youngit.office.api.material.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "자재 정보")
public class MaterialModel {

    String	materialUniqId; //자재고유ID : MTRIL_00000000000000(14자리)
    String	assetSortCode; //자산분류코드 : 10(상품)/20(완제품)/30(반제품)/40(원재료)/50(부재료)/60(제품수리)
    String	productSortCode; //품목분류코드
    String	materialName; //자재명
    String	materialStandard; //자재규격
    String	materialUnit; //자재단위 : EA, m, 팩, mah
    int	materialUnitPrice; //자재단가

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp createAt; //최초등록시점
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp updateAt; //최종수정시점
    String createdByMemberUniqId; //최초등록자ID : USRCNFRM_00000000000(11자리)
    String updatedByMemberUniqId; //최종수정자ID : USRCNFRM_00000000000(11자리)
    String isUsed; //사용여부
}
