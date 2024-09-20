package com.youngit.office.api.install.controller;

import com.youngit.office.api.http.ApiResponse;
import com.youngit.office.api.install.dto.InstallDto;
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

    private final InstallService installService;
    @Autowired
    public InstallController(InstallService installService) {
        this.installService = installService;
    }

    @Operation(summary = "설치 리스트 + 상태별 조회")
    @GetMapping("/install")
    public ApiResponse<List<InstallDto>> getListInstall(@RequestParam(value = "installstateCode", required = false) String installStateCode) {
        logger.info("설치 리스트 + 상태별 조회");
        int count = installService.getCountListInstall(installStateCode);
        List<InstallDto> result = installService.getListInstall(installStateCode);
        return new ApiResponse<>(result, 0, "설치 리스트 조회", count);
    }


    @Operation(summary = "설치 개별 조회")
    @GetMapping("/install/{installUniqId}")
    public ApiResponse<InstallDto> getOneInstall(String installUniqId) {
        logger.info("설치 개별 조회");
        InstallDto result = installService.getOneInstall(installUniqId);
        return new ApiResponse<>(result, 0, "설치 개별 조회 완료");
    }

    @Operation(summary = "신규설치 등록")
    @PostMapping("/install")
    public ApiResponse<String> registerInstall(InstallDto installDto) {
        int result = installService.registerInstall(installDto);
        if (result == 1) {
            return new ApiResponse<>("설치 등록 성공");
        } else {
            return new ApiResponse<>("설치 등록 실패");
        }
    }

    @Operation(summary = "설치 수정")
    @PutMapping("/install")
    public ApiResponse<String> updateInstall(InstallDto installDto) {
        int result = installService.updateInstall(installDto); //설치수량, 잔여수량, 준공률 : 계약 db에도 반영돼야.
        if (result == 1) {
            return new ApiResponse<>("설치 수정 성공");
        } else {
            return new ApiResponse<>("설치 수정 실패");
        }
    }


    @Operation(summary = "설치 삭제")
    @DeleteMapping("/install")
    public ApiResponse<String> deleteInstall(String installUniqId)
    {
        int result = installService.deleteInstall(installUniqId);
        if (result == 1) {
            return new ApiResponse<>("설치 삭제 성공");
        } else {
            return new ApiResponse<>("설치 삭제 실패");
        }
    }


}