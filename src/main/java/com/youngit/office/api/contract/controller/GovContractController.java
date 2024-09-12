package com.youngit.office.api.contract.controller;

import com.youngit.office.api.contract.dto.ContractDto;
import com.youngit.office.api.contract.service.ContractService;
import com.youngit.office.api.http.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Tag(name = "지자체 계약 관리")
@RestController
@RequestMapping("/api")
public class GovContractController {

    private static final Logger logger = Logger.getLogger(GovContractController.class.getName());
    private final ContractService contractService;
    @Autowired
    public GovContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @Operation(summary = "지자체 계약 리스트 조회")
    @GetMapping("/contract/gov")
    public ApiResponse<List<ContractDto>> getListGovContract() {
        logger.info("지자체 계약 리스트 조회");
        int count = contractService.getCountListContract("gov");
        List<ContractDto> result = contractService.getListContract("gov");
        return new ApiResponse<>(result, 0, "조회성공", count);
    }

    @Operation(summary = "지자체 계약 개별 조회")
    @GetMapping("/contract/gov/{contractUniqNo}")
    public ApiResponse<ContractDto> getOneGovContract(String contractUniqNo) {
        logger.info("지자체 계약 개별 조회");
        ContractDto result = contractService.getOneContract(contractUniqNo);
        return new ApiResponse<>(result, 0, "조회성공");
    }

    @Operation(summary = "지자체 계약서 작성: 계약번호 자동생성")
    @GetMapping("/contract/gov/contractno")
    public ApiResponse<String> getNewContractNo() {

        String result = contractService.getNewContractUniqNo();
        return new ApiResponse<>(result);

    }

    @Operation(summary = "지자체 계약 등록 완료", description = "필수입력: 계약주체, 계약번호(자동입력), 계약명, 계약분류, 거래처 ")
    @PostMapping("/contract/gov")
    public ApiResponse<String> registerContract(@RequestBody ContractDto contractDto) {
        logger.info("지자체 계약 등록");
        int result = contractService.registerContract(contractDto);
        if (result == 3)
            return new ApiResponse<>("지자체 계약 등록 성공");
        else
            return new ApiResponse<>("지자체 계약 등록 실패");
    }


    @Operation(summary = "지자체 계약 수정")
    @PutMapping("/contract/gov")
    public ApiResponse<String> updateContract(ContractDto contractDto) {
        logger.info("지자체 계약 수정");
        int result = contractService.updateContract(contractDto);
        if (result == 3) //? list가 null이라면?
            return new ApiResponse<>("지자체 계약 수정 성공");
        else
            return new ApiResponse<>("지자체 계약 수정 실패");

    }

    @Operation(summary = "지자체 계약 삭제")
    @DeleteMapping("/contract/gov")
    public ApiResponse<String> deleteContract(String contractId) {
        logger.info("지자체 계약 삭제");
        int result = contractService.deleteContract(contractId);
        if (result == 3) {
            return new ApiResponse<>("지자체 계약 삭제 성공");
        } else {
            return new ApiResponse<>("지자체 계약 삭제 실패");
        }
    }

}
