package com.youngit.office.api.member.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "")
public class OfficeAssignModel {

    String	memberId; //회원ID
    String	officeId; //사업소ID
}
