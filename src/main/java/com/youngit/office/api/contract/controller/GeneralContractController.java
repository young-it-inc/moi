package com.youngit.office.api.contract.controller;

import com.youngit.office.api.contract.model.ContractModel;
import com.youngit.office.api.contract.service.ContractService;
import com.youngit.office.api.http.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Tag(name = "일반 계약 관리")
@RestController
@RequestMapping("/api")
public class GeneralContractController {

    private static final Logger logger = Logger.getLogger(GeneralContractController.class.getName());

    @Autowired
    ContractService contractService;

    @Operation(summary = "일반 계약서 작성: 계약번호 자동생성")
    @GetMapping("/contract/general/contractno")
    public ApiResponse<String> getNewContractNo()
    {
        String result = contractService.getNewContractUniqNo();
        return new ApiResponse<>(result);
    }

    @Operation(summary = "일반 계약 등록 완료", description = "필수입력: 계약주체, 계약번호, 계약명, 계약분류, 거래처")
    @PostMapping("/contract/general")
    public ApiResponse<String> registerGeneralContract(@RequestBody ContractModel contractModel) {
        logger.info("일반 계약 등록");
        int result = contractService.registerContract(contractModel);
        return new ApiResponse<>("일반 계약 등록 성공");
    }

    @Operation(summary = "일반 계약 조회")
    @GetMapping("/contract/general")
    public ApiResponse<List<ContractModel>> getListGeneralContract() {
        logger.info("일반 계약 조회");
        return new ApiResponse<>(contractService.getListGeneralContract());
    }

    @Operation(summary = "일반 계약 수정")
    @PutMapping("/contract/general")
    public ApiResponse<String> updateGeneralContract(ContractModel contractModel)
    {
        logger.info("일반 계약 수정");
        int result = contractService.updateContract(contractModel);
        if(result == 3) //? list가 null이라면?
            return new ApiResponse<>("일반 계약 수정 성공");
        else
            return new ApiResponse<>("일반 계약 수정 실패");

    }

    @Operation(summary = "일반 계약 삭제")
    @DeleteMapping("/contract/general")
    public ApiResponse<String> deleteGeneralContract(String contractId) {
        logger.info("계약 삭제");
        int result = contractService.deleteContract(contractId);
        if(result == 1) {
            return new ApiResponse<>("일반 계약 삭제 성공");
        }
        else {
            return new ApiResponse<>("일반 계약 삭제 실패");
        }
    }
}
