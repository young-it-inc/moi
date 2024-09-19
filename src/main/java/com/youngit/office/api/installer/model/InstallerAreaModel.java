package com.youngit.office.api.installer.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "유지보수 고정 계약 지역")
public class InstallerAreaModel {
    String installerUniqNo;
    String officeId;
}
