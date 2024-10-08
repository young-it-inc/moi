package com.youngit.office.api.office.controller;

import com.youngit.office.api.http.ApiResponse;
import com.youngit.office.api.office.model.OfficeModel;
import com.youngit.office.api.office.service.OfficeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Tag(name = "사업소 관리")
@RestController
public class OfficeController {

    private static final Logger logger = Logger.getLogger(OfficeController.class.getName());

    @Autowired
    OfficeService officeService;

    @Operation(summary = "사업소 등록")
    @PostMapping("/api/office")
    public ApiResponse<String> registerOffice(@RequestBody OfficeModel officeModel) {
        logger.info("사업소 등록");
        int result = officeService.registerOffice(officeModel);
        if(result == 1) {
            return new ApiResponse<>("사업소 등록 성공");
        } else {
            return new ApiResponse<>("사업소 등록 실패");
        }
    }

    @Operation(summary ="사업소 조회")
    @GetMapping("/api/office")
    public ApiResponse<List<OfficeModel>> getListOffice() {
        logger.info("사업소 조회");
        List<OfficeModel> result = officeService.getListOffice();
        return new ApiResponse<>(result);
    }

    @Operation(summary = "사업소 수정")
    @PutMapping("/api/office")
    public ApiResponse<String> updateOffice(@RequestBody OfficeModel officeModel) {
        logger.info("사업소 수정");
        int result = officeService.updateOffice(officeModel);
        if(result == 1) {
            return new ApiResponse<>("사업소 수정 성공");
        } else {
            return new ApiResponse<>("사업소 수정 실패");
        }
    }
    @Operation(summary = "사업소 삭제")
    @DeleteMapping("/api/office")
    public ApiResponse<String> deleteOffice(@RequestBody String officeId) {
        logger.info("사업소 삭제");
        int result = officeService.deleteOffice(officeId);
        if(result == 1) {
            return new ApiResponse<>("사업소 삭제 성공");
        } else {
            return new ApiResponse<>("사업소 삭제 실패");
        }
    }
}
