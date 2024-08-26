package com.youngit.office.api.material.controller;

import com.youngit.office.api.http.ApiResponse;
import com.youngit.office.api.material.model.MaterialModel;
import com.youngit.office.api.material.service.MaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Tag(name="자재 관리")
@RestController
@RequestMapping("/api")
public class MaterialController {

    //자재 조회, 검색, 등록, 수정, 삭제

    private static final Logger logger = Logger.getLogger(MaterialController.class.getName());

    @Autowired
    private MaterialService materialService;

    @Operation(summary = "자재 조회")
    @GetMapping("/material")
    public List<MaterialModel> getListMaterial() {
        logger.info("자재 조회");
        return materialService.getListMaterial();
    }

    @Operation(summary = "자재 등록", description = "필수입력: ")
    @PostMapping("/material")
    public ApiResponse<String> postMaterial(@RequestBody MaterialModel materialModel) {
        logger.info("자재 등록");
        int result = materialService.registerMaterial(materialModel);
        if (result == 1)
        {
            return new ApiResponse<>("자재 등록 성공");
        }
        else
        {
            return new ApiResponse<>("자재 등록 실패");
        }
    }

    @Operation(summary = "자재 수정")
    @PutMapping("/material")
    public ApiResponse<String> updateMaterial(@RequestBody MaterialModel materialModel) {
        logger.info("자재 수정");
        int result = materialService.updateMaterial((materialModel));
        if (result == 1)
        {
            return new ApiResponse<>("자재 수정 성공");
        }
        else
        {
            return new ApiResponse<>("자재 수정 실패");
        }
    }

    @Operation(summary = "자재 삭제")
    @DeleteMapping("/material")
    public void deleteMaterial(@RequestBody String materialId) {
        logger.info("자재 삭제");
        materialService.deleteMaterial(materialId);
    }
}
