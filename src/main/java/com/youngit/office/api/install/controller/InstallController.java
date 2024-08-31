package com.youngit.office.api.install.controller;

import com.youngit.office.api.http.ApiResponse;
import com.youngit.office.api.install.model.InstallModel;
import com.youngit.office.api.install.service.InstallService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Tag(name = "설치 관리")
@RestController
@RequestMapping("/api")
public class InstallController {

    private static final Logger logger = Logger.getLogger(InstallController.class.getName());

    @Autowired
    public InstallService installService;


    @Operation(summary = "설치 리스트 + 상태별 조회")
    @GetMapping("/install")
    public ApiResponse<List<InstallModel>> getListInstall(@RequestParam(value = "installstateCode", required = false) String installStateCode) {
        logger.info("설치 리스트 + 상태별 조회");
        int count = installService.getCountListInstall(installStateCode);
        List<InstallModel> result = installService.getListInstall(installStateCode);
        return new ApiResponse<>(result, 0, "목록 조회", count);
    }


    @Operation(summary = "설치 개별 조회")
    @GetMapping("/install/{installUniqId}")
    public ApiResponse<InstallModel> getOneInstall(String installUniqId) {
        logger.info("설치 개별 조회");
        InstallModel result = installService.getOneInstall(installUniqId);
        return new ApiResponse<>(result);
    }

    @Operation(summary = "신규설치 등록")
    @PostMapping("/install")
    public ApiResponse<String> registerInstall(InstallModel installModel) {
        int result = installService.registerInstall(installModel);
        if (result == 1) {
            return new ApiResponse<>("설치 등록 성공");
        } else {
            return new ApiResponse<>("설치 등록 실패");
        }
    }

    @Operation(summary = "설치 수정")
    @PutMapping("/install")
    public ApiResponse<String> updateInstall(InstallModel installModel) {
        int result = installService.updateInstall(installModel);
        if (result == 1) {
            return new ApiResponse<>("설치 수정 성공");
        } else {
            return new ApiResponse<>("설치 수정 실패");
        }
    }


    @Operation(summary = "설치 삭제")
    @DeleteMapping("/install")
    public ApiResponse<String> deleteInstall(InstallModel installModel) {
        int result = installService.deleteInstall(installModel);
        if (result == 1) {
            return new ApiResponse<>("설치 삭제 성공");
        } else {
            return new ApiResponse<>("설치 삭제 실패");
        }
    }


}
