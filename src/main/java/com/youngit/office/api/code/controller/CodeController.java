package com.youngit.office.api.code.controller;

import com.youngit.office.api.code.service.CodeService;
import com.youngit.office.api.http.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "코드 관리")
@RestController
@RequestMapping("/api")
public class CodeController {

    private static final Logger logger = LoggerFactory.getLogger(CodeController.class);


    private final CodeService codeService;

    @Autowired
    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @Operation(summary = "코드 전체 리스트 조회")
    @GetMapping("/contract")
    public ApiResponse<String> getAllCodeList() {
        return new ApiResponse<>("코드 전체 리스트 조회 성공");
    }

    @Operation(summary = "코드 개별 리스트 조회")
    @GetMapping("/contract")
    public ApiResponse<String> getDetailCodeList(String codeId) {
        return new ApiResponse<>("코드 개별 리스트 조회 성공");
    }

    @Operation(summary = "코드 개별 등록")
    @PostMapping("/contract")
    public ApiResponse<String> registerDetailCode(String codeId) {
        return new ApiResponse<>("코드 등록 성공");
    }

    @Operation(summary = "코드 개별 수정")
    @PutMapping("/contract")
    public ApiResponse<String> updateDetailCode(String codeId) {
        return new ApiResponse<>("코드 수정 성공");
    }

    @Operation(summary = "코드 개별 삭제")
    @DeleteMapping("/contract")
    public ApiResponse<String> deleteDetailCode(String codeId) {
        return new ApiResponse<>("코드 삭제 성공");
    }

}
