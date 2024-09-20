package com.youngit.office.api.estimate.controller;

import com.youngit.office.api.email.EmailService;
import com.youngit.office.api.estimate.dto.EstimateDto;
import com.youngit.office.api.estimate.dto.EstimateSearchDto;
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
    private final EstimateService estimateService;
    private final EmailService emailService;
    @Autowired
    public EstimateController(EstimateService estimateService, EmailService emailService) {
        this.estimateService = estimateService;
        this.emailService = emailService;
    }


    @Operation(summary = "견적서 리스트 조회 및 검색")
    @GetMapping("/estimate")
    public ApiResponse<List<EstimateDto>> getOrSearchListEstimate(EstimateSearchDto estimateSearchDto)
    {
        logger.info("견적서 리스트 조회 및 검색");
        int count = estimateService.countGetOrSearchListEstimate(estimateSearchDto);
        List<EstimateDto> result = estimateService.getOrSearchListEstimate(estimateSearchDto);
        return new ApiResponse<>(result, 0, "견적서 리스트 조회 및 검색 완료", count);
    }

    @Operation(summary = "견적서 개별 조회")
    @GetMapping("/estimate/{estimateUniqNo}")
    public ApiResponse<EstimateDto> getListEstimate(String estimateUniqNo)
    {
        logger.info("견적서 개별 조회");
        EstimateDto result = estimateService.getOneEstimate(estimateUniqNo);
        return new ApiResponse<>(result, 0, "견적서 개별 조회 완료");
    }


    @Operation(summary = "견적서 등록 버튼: 견적번호 자동생성") //먼저 등록'완료'버튼 누른 견적서부터 순차적으로 견적번호 부여됨
    @GetMapping("/estimate/register")
    public ApiResponse<String> getNewEstimateNo() {
        String result = estimateService.getNewEstimateUniqNo();
        return new ApiResponse<>(result);
    }

    @Operation(summary = "견적서 등록 완료: 일반, 지자체")
    @PostMapping("/estimate/register")
    public ApiResponse<String> registerEstimate(EstimateDto estimateDto) {
        logger.info("견적서 등록 완료");
        int result = estimateService.registerEstimate(estimateDto);
        if(result == 1)
            return new ApiResponse<>("견적서 등록 성공");
        else
            return new ApiResponse<>("견적서 등록 실패");
    }


    @Operation(summary = "견적서 수정")
    @PutMapping("/estimate")
    public ApiResponse<String> updateEstimate(EstimateDto estimateDto) {
        logger.info("견적서 수정");
        int result = estimateService.updateEstimate(estimateDto);
        if (result == 2) //? list가 null이라면?
            return new ApiResponse<>("견적서 수정 성공");
        else
            return new ApiResponse<>("견적서 수정 실패");

    }


    @Operation(summary = "견적서 삭제")
    @DeleteMapping("/estimate")
    public ApiResponse<String> deleteEstimate(String estimateUniqNo) {
        logger.info("견적서 삭제");
        int result = estimateService.deleteEstimate(estimateUniqNo);
        if(result == 1)
            return new ApiResponse<>("견적서 삭제 성공");
        else
            return new ApiResponse<>("견적서 삭제 실패");
    }

    @Operation(summary = "견적서 계약 진행 버튼")
    @GetMapping("/estimate/contract")
    public ApiResponse<String> registerEstimateContract(EstimateDto estimateDto) {
        logger.info("견적서 계약 진행 (계약서 미완성 상태)");
        int result = estimateService.registerEstimateContract(estimateDto); //계약번호 전달
        if(result == 1)
            return new ApiResponse<>("계약 진행 성공");
        else
            return new ApiResponse<>("계약 진행 실패");
    }

    @Operation(summary = "견적서 이메일 발송 버튼")
    @GetMapping("/estimate/email")
    public ApiResponse<String> sendEmailEstimate(EstimateDto estimateDto) {
        logger.info("견적서 이메일 발송");
        int result = emailService.sendEmailEstimate(estimateDto);
        if(result == 1)
            return new ApiResponse<>("이메일 발송 완료", 0);
        else
            return new ApiResponse<>("이메일 발송 실패", 1);

    }
}
