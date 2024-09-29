package com.youngit.office.api.installer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "")
public class InstallerSearchDto {
    String searchKey;
    String searchValue;
}
