package com.youngit.office.api.estimate.controller;

import com.youngit.office.api.estimate.model.EstimateModel;
import com.youngit.office.api.estimate.service.EstimateService;
import com.youngit.office.api.http.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Tag(name ="일반계약 견적서 관리" )
@RestController
@RequestMapping("/api")
public class EstimateController {

    //견적서 조회(출고자재 품목보기), 검색, 등록, 수정, 삭제, 엑셀출력 +계약등록
    // +추가요청사항: 견적서 작성 후 이메일 발송기능,
    // 택배송장번호입력시 자동문자발송기능,
    // 견적부터 종료까지 한번에 확인 가능하게(견적-세금계산서-입금확인-출고요청-출고-배송완료)

    private static final Logger logger = Logger.getLogger(EstimateController.class.getName());
    @Autowired
    EstimateService estimateService;

    @Operation(summary = "견적서 리스트 조회")
    @GetMapping("/estimate")
    public ApiResponse<List<EstimateModel>> getListEstimate()
    {
        logger.info("견적서 리스트 조회");
        int count = estimateService.getCountListEstimate();
        List<EstimateModel> result = estimateService.getListEstimate();
        return new ApiResponse<>(result, 0, "견적서 목록 조회 완료", count);
    }

    @Operation(summary = "견적서 개별 조회")
    @GetMapping("/estimate/{estimateUniqNo}")
    public ApiResponse<EstimateModel> getListEstimate(String estimateUniqNo)
    {
        logger.info("견적서 개별 조회");
        EstimateModel result = estimateService.getOneEstimate(estimateUniqNo);
        return new ApiResponse<>(result, 0, "견적서 개별 조회 완료");
    }


    @Operation(summary = "견적서 등록: 일반, 지자체")
    @PostMapping("/estimate")
    public ApiResponse<String> registerEstimate(EstimateModel estimateModel) {
        logger.info("견적서 등록");
        int result = estimateService.registerEstimate(estimateModel);
        return new ApiResponse<>("견적서 등록 성공");
    }


    @Operation(summary = "견적서 수정")
    @PutMapping("/estimate")
    public ApiResponse<String> updateEstimate(EstimateModel estimateModel) {
        logger.info("견적서 수정");
        int result = estimateService.updateEstimate(estimateModel);
        return new ApiResponse<>("견적서 수정 성공");
    }


    @Operation(summary = "견적서 삭제")
    @DeleteMapping("/estimate")
    public ApiResponse<String> deleteEstimate(EstimateModel estimateModel) {
        logger.info("견적서 삭제");
        int result = estimateService.deleteEstimate(estimateModel);
        return new ApiResponse<>("견적서 삭제 성공");
    }
}
