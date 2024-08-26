package com.youngit.office.api.install.controller;

import com.youngit.office.api.http.ApiResponse;
import com.youngit.office.api.install.model.InstallModel;
import com.youngit.office.api.install.service.InstallService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="설치 관리")
@RestController
public class InstallController {

    public InstallService installService;

    @Operation(summary = "신규설치 등록")
    @PostMapping("/api/install")
    public ApiResponse<String> registerInstall(InstallModel installModel) {
        int result = installService.registerInstall(installModel);
        if(result == 1) {
            return new ApiResponse<>("설치 등록 성공");
        } else {
            return new ApiResponse<>("설치 등록 실패");
        }
    }

    @Operation(summary = "설치 수정")
    @PutMapping("/api/install")
    public ApiResponse<String> updateInstall(InstallModel installModel) {
        int result = installService.updateInstall(installModel);
        if(result == 1) {
            return new ApiResponse<>("설치 수정 성공");
        } else {
            return new ApiResponse<>("설치 수정 실패");
        }
    }


   @Operation(summary = "설치 삭제")
   @DeleteMapping("/api/install")
   public ApiResponse<String> deleteInstall(InstallModel installModel) {
        int result = installService.deleteInstall(installModel);
        if(result == 1) {
            return new ApiResponse<>("설치 삭제 성공");
        } else {
            return new ApiResponse<>("설치 삭제 실패");
        }
    }

    @Operation(summary = "설치 조회")
    @GetMapping("/api/install")
    public ApiResponse<List<InstallModel>> getListInstall() {
        List<InstallModel> result = installService.getListInstall();
        return new ApiResponse<>(result);
    }
}
