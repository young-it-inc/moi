package com.youngit.office.api.installer.controller;

import com.youngit.office.api.http.ApiResponse;
import com.youngit.office.api.installer.dto.InstallerDto;
import com.youngit.office.api.installer.service.InstallerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "설치팀 최초 계약 관리")
@RestController
public class InstallerController {

    private static final Logger logger = LoggerFactory.getLogger(InstallerController.class);

    private final InstallerService installerService;

    @Autowired
    public InstallerController(InstallerService installerService) {
        this.installerService = installerService;
    }

    @Operation(summary = "설치팀 최초 계약 리스트 조회")
    @GetMapping("/api/installer")
    public ApiResponse<List<InstallerDto>> getListInstaller() {
        int count = installerService.countGetOrSearchListInstaller();
        List<InstallerDto> result = installerService.getListInstaller();
        return new ApiResponse<>(result, 0, "설치팀 최초 계약 리스트 조회 완료", count);
    }

    @Operation(summary = "설치팀 최초 계약 등록")
    @PostMapping("/api/installer")
    public ApiResponse<String> registerInstaller(@RequestBody InstallerDto installerDto) {
        int result = installerService.registerInstaller(installerDto);
        return new ApiResponse<>("회원 생성 성공");
    }

    @Operation(summary = "설치팀 최초 계약 수정")
    @PutMapping("/api/installer")
    public ApiResponse<String> updateInstaller(InstallerDto installerDto) {
        int result = installerService.updateInstaller(installerDto);
        return new ApiResponse<>("회원 수정 성공");
    }

    @Operation(summary = "설치팀 최초 계약 삭제")
    @DeleteMapping("/api/installer")
    public ApiResponse<String> deleteInstaller(@PathVariable String installerUniqNo) {
        int result = installerService.deleteInstaller(installerUniqNo);
        return new ApiResponse<>("회원 삭제 성공");
    }
}
