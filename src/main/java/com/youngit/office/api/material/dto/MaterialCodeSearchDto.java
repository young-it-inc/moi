package com.youngit.office.api.material.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "")
public class MaterialCodeSearchDto {
    String searchValue;
    String searchKey;
}
