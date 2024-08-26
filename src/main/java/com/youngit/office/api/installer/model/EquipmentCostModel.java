package com.youngit.office.api.installer.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "장비 단가")
public class EquipmentCostModel {

    int equipmentCost;
    String equipmentName;
    String skillLevel;

}
