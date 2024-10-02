package com.youngit.office.api.material.controller;

import com.youngit.office.api.http.ApiResponse;
import com.youngit.office.api.material.dto.MaterialCodeDto;
import com.youngit.office.api.material.dto.MaterialCodeSearchDto;
import com.youngit.office.api.material.service.MaterialCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="자재 코드 관리")
@RestController
@RequestMapping("/api")
public class MaterialCodeController {

    private MaterialCodeService materialCodeService;

    public MaterialCodeController(MaterialCodeService materialCodeService) {
        this.materialCodeService = materialCodeService;
    }

    @Operation(summary = "자재 분류 코드 리스트 조회 및 검색")
    @GetMapping("/materialcode")
    public ApiResponse<List<MaterialCodeDto>> getOrSearchListMaterialCode(MaterialCodeSearchDto materialCodeSearchDto) {
        int count = materialCodeService.countGetOrSearchListMaterialCode(materialCodeSearchDto);
        List<MaterialCodeDto> result = materialCodeService.getOrSearchListMaterialCode(materialCodeSearchDto);

        return new ApiResponse<>(result, 0, "자재 리스트 조회 성공", count);
    }
    @Operation(summary = "자재 분류 코드 등록")
    @PostMapping("/materialcode")
    public ApiResponse<String> registerMaterialCode(MaterialCodeDto materialCodeDto) {
        int result = materialCodeService.registerMaterialCode(materialCodeDto);
        if(result == 1)
            return new ApiResponse<>("자재 분류 코드 등록 성공", 0);
        else
            return new ApiResponse<>("자재 분류 코드 등록 실패", 1);
    }

    @Operation(summary = "자재 분류 서브 코드 등록")
    @PostMapping("/materialsbucode")
    public ApiResponse<String> registerMaterialSubCode(MaterialCodeDto materialCodeDto) {
        int result = materialCodeService.registerMaterialSubCode(materialCodeDto);
        if(result == 1)
            return new ApiResponse<>("자재 분류 서브 코드 등록 성공", 0);
        else
            return new ApiResponse<>("자재 분류 서브 코드 등록 실패", 1);
    }

    @Operation(summary = "자재 분류 코드 수정")
    @PutMapping("/materialcode")
    public ApiResponse<String> updateMaterialCode(MaterialCodeDto materialCodeDto) {
        int result = materialCodeService.updateMaterialCode(materialCodeDto);
        if(result == 1)
            return new ApiResponse<>("자재 분류 코드 수정 성공", 0);
        else
            return new ApiResponse<>("자재 분류 코드 수정 실패", 1);
    }

    @Operation(summary = "자재 분류 코드 삭제")
    @DeleteMapping("/materialcode")
    public ApiResponse<String> deleteMaterialCode(String materialCode) {
        int result = materialCodeService.deleteMaterialCode(materialCode);
        if(result == 1)
            return new ApiResponse<>("자재 분류 코드 삭제 성공", 0);
        else
            return new ApiResponse<>("자재 분류 코드 삭제 실패", 1);
    }

}
