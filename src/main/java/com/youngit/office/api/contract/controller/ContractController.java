package com.youngit.office.api.contract.controller;

import com.youngit.office.api.contract.dto.ContractDto;
import com.youngit.office.api.contract.dto.ContractSearchDto;
import com.youngit.office.api.contract.service.ContractService;
import com.youngit.office.api.http.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "계약 관리")
@RestController
@RequestMapping("/api")
public class ContractController {

    //매출 관리 메뉴 생성: 영업팀별 매출 목표/실적/달성률 사업매출 관리 기능
    //계약완료일(납품기한일, 준공일) 60일 전부터 빨간불 표시


    private static final Logger logger = LoggerFactory.getLogger(ContractController.class);
    private final ContractService contractService;
    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }


    @Operation(summary = "계약 리스트 조회 및 검색")
    @GetMapping("/contract")
    public ApiResponse<List<ContractDto>> getOrSearchListGeneralContract(ContractSearchDto contractSearchDto) {
        logger.info("계약 리스트 조회 및 검색");
        int count = contractService.countGetOrSearchListContract(contractSearchDto);
        List<ContractDto> result = contractService.getOrSearchListContract(contractSearchDto);
        return new ApiResponse<>(result, 0, "계약 리스트 조회 및 검색 완료", count);
    }

    @Operation(summary = "계약 개별 조회")
    @GetMapping("/contract/{contractUniqNo}")
    public ApiResponse<ContractDto> getOneGeneralContract(@PathVariable String contractUniqNo) {
        logger.info("계약 개별 조회");
        ContractDto result = contractService.getOneContract(contractUniqNo);
        return new ApiResponse<>(result, 0, "계약 개별 조회 완료");
    }


    @Operation(summary = "계약서 등록 버튼: 계약번호 자동생성")
    @GetMapping("/contract/contractno")
    public ApiResponse<String> getNewContractNo() {
        String result = contractService.getNewContractUniqNo();
        return new ApiResponse<>(result);
    }

    @Operation(summary = "계약 등록 완료 버튼", description = "필수입력: 계약주체, 계약번호(자동입력), 계약명, 계약분류, 거래처")
    @PostMapping("/contract")
    public ApiResponse<String> registerGeneralContract(@RequestBody ContractDto contractDto) {
        logger.info("계약 등록 완료");
        int result = contractService.registerContract(contractDto);
        if (result == 3)
            return new ApiResponse<>("계약 등록 성공");
        else
            return new ApiResponse<>("계약 등록 실패");
    }


    @Operation(summary = "계약 수정")
    @PutMapping("/contract")
    public ApiResponse<String> updateGeneralContract(ContractDto contractDto) {
        logger.info("계약 수정");
        int result = contractService.updateContract(contractDto);
        if (result == 3) //? list가 null이라면?
            return new ApiResponse<>("계약 수정 성공");
        else
            return new ApiResponse<>("계약 수정 실패");
    }

    @Operation(summary = "계약 삭제")
    @DeleteMapping("/contract")
    public ApiResponse<String> deleteGeneralContract(String contractUniqNo) {
        logger.info("계약 삭제");
        int result = contractService.deleteContract(contractUniqNo);
        if (result == 1) {
            return new ApiResponse<>("계약 삭제 성공");
        } else {
            return new ApiResponse<>("계약 삭제 실패");
        }
    }

    @Operation(summary = "계약 리스트 엑셀 다운로드")
    @GetMapping("/contract/excel/download")
    public ResponseEntity<Object> downloadExcelContractList(@RequestBody(required = false) List<ContractDto> contractDtoList) throws IOException {
        logger.info("계약서 리스트 엑셀 다운로드");
        //엑셀 파일 생성
        byte[] excelFile = contractService.generateExcelEstimateList(contractDtoList);
        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "estimate_list.xlsx");

        return new  ResponseEntity<>(excelFile, headers, HttpStatus.OK);
    }
}
