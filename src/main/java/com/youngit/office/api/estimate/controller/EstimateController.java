package com.youngit.office.api.estimate.controller;

import com.youngit.office.api.estimate.dto.EstimateDto;
import com.youngit.office.api.estimate.dto.EstimateSearchDto;
import com.youngit.office.api.estimate.service.EstimateService;
import com.youngit.office.api.file.service.FileService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name ="견적서 관리")
@RestController
@RequestMapping("/api")
public class EstimateController {

    //견적서 조회(출고자재 품목보기), 검색, 등록, 수정, 삭제, 엑셀출력 +계약등록
    // +추가요청사항: 견적서 작성 후 이메일 발송기능,
    // 택배송장번호입력시 자동문자발송기능,
    // 견적부터 종료까지 한번에 확인 가능하게(견적-세금계산서-입금확인-출고요청-출고-배송완료)
    private static final Logger logger = LoggerFactory.getLogger(EstimateController.class);
    private final EstimateService estimateService;
    private final FileService fileService;
    @Autowired
    public EstimateController(EstimateService estimateService, FileService fileService) {
        this.estimateService = estimateService;
        this.fileService = fileService;
    }

    @Operation(summary = "견적서 리스트 조회 및 검색")
    @GetMapping("/estimate")
    public ApiResponse<List<EstimateDto>> getOrSearchListEstimate(EstimateSearchDto estimateSearchDto) {
        long start = System.currentTimeMillis();
        try {
            int count = estimateService.countGetOrSearchListEstimate(estimateSearchDto);
            List<EstimateDto> result = estimateService.getOrSearchListEstimate(estimateSearchDto);
            return new ApiResponse<>(result, 0, "견적서 리스트 조회 및 검색 완료", count);
        } finally {
            long end = System.currentTimeMillis();
            logger.info("견적서 리스트 조회 및 검색 소요시간: " + (end - start) + "ms");
        }
    }

    @Operation(summary = "견적서 개별 조회")
    @GetMapping("/estimate/{estimateUniqNo}")
    public ApiResponse<EstimateDto> getListEstimate(String estimateUniqNo) {
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
        int result = estimateService.registerEstimate(estimateDto);
        if(result == 1)
            return new ApiResponse<>("견적서 등록 성공");
        else
            return new ApiResponse<>("견적서 등록 실패");
    }

    @Operation(summary = "견적서 수정")
    @PutMapping("/estimate")
    public ApiResponse<String> updateEstimate(EstimateDto estimateDto) {
        int result = estimateService.updateEstimate(estimateDto);
        if (result == 2) //? list가 null이라면?
            return new ApiResponse<>("견적서 수정 성공");
        else
            return new ApiResponse<>("견적서 수정 실패");
    }

    @Operation(summary = "견적서 삭제")
    @DeleteMapping("/estimate")
    public ApiResponse<String> deleteEstimate(String estimateUniqNo) {
        int result = estimateService.deleteEstimate(estimateUniqNo);
        if(result == 1)
            return new ApiResponse<>("견적서 삭제 성공");
        else
            return new ApiResponse<>("견적서 삭제 실패");
    }

    @Operation(summary = "견적서 계약 진행 버튼(계약서 미완성 상태)")
    @GetMapping("/estimate/contract")
    public ApiResponse<String> registerEstimateContract(EstimateDto estimateDto) {
        int result = estimateService.registerEstimateContract(estimateDto); //계약번호 전달
        if(result == 1)
            return new ApiResponse<>("계약 진행 성공");
        else
            return new ApiResponse<>("계약 진행 실패");
    }

    @Operation(summary = "등록한 견적서 엑셀 파일 다운로드")
    @GetMapping("/estimate/download/excel")
    public ResponseEntity<Object> downloadExcelEstimate(@RequestParam("estimateUniqNo") String estimateUniqNo) throws IOException {
        //엑셀파일 생성
        byte[] excelFile = estimateService.generateEstimateExcel(estimateUniqNo);

        //HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "estimate.xlsx");

        //ResponseEntity로 엑셀 파일 반환
        return new ResponseEntity<>(excelFile, headers, HttpStatus.OK);
    }

    @Operation(summary = "수정한 견적서 엑셀 파일 업로드")
    @PutMapping("/estimate/upload/excel")
    public ResponseEntity<String> uploadExcelEstimate(@RequestParam("file") MultipartFile file, @RequestParam("estimateUniqNo") String estimateUniqNo) throws IOException {
        try {
            // 파일 데이터를 데이터베이스에 저장
            int result = fileService.uploadFile(file);
            return ResponseEntity.ok("파일 업로드 성공!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 실패");
        }
    }

    @Operation(summary = "수정 후 업로드한 견적서 엑셀 파일 삭제")
    @DeleteMapping("/estimate/upload/excel")
    public ApiResponse<String> deleteExcelEstimate(@RequestParam("estimateUniqNo") String estimateUniqNo) throws IOException {
        int result = fileService.deleteFile(estimateUniqNo);
        if( result ==1)
            return new ApiResponse<>("파일 삭제 성공");
        else
            return new ApiResponse<>("파일 삭제 실패");
    }



    @Operation(summary = "견적서 이메일 발송 버튼: 엑셀파일 첨부") //업로드 된 견적서 있다면 업로드 파일 우선 발송, 없다면 등록된 견적서 발송
    @PostMapping("/estimate/email")
    public ApiResponse<String> sendEmailEstimate(EstimateDto estimateDto) {
        int result = estimateService.sendEmailEstimate(estimateDto);
        if(result == 1)
            return new ApiResponse<>("이메일 발송 완료", 0);
        else
            return new ApiResponse<>("이메일 발송 실패", 1);
    }


    @Operation(summary = "견적서 리스트 엑셀 다운로드")
    @GetMapping("/estimate/excel")
    public ResponseEntity<Object> downloadExcelEstimateList(@RequestBody(required = false) List<EstimateDto> estimateDtoList) throws IOException {
        //엑셀 파일 생성
        byte[] excelFile = estimateService.generateExcelEstimateList(estimateDtoList);
        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "estimate_list.xlsx");

        return new  ResponseEntity<>(excelFile, headers, HttpStatus.OK);
    }
}
