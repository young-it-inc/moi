package com.youngit.office.api.material.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "자재 분류 코드 정보")
class MaterialCodeModel {
    String	assetSortCode; //자산분류코드
    String	productSortCode; //품목분류코드
    String	materialName; //자재분류명
    int	sortNumber; //정렬번호
    String	isUsed; //사용여부 (Y/N)
}
