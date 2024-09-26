package com.youngit.office.api.bill.controller;

import com.youngit.office.api.bill.dto.BillDto;
import com.youngit.office.api.bill.dto.BillSearchDto;
import com.youngit.office.api.bill.service.BillService;
import com.youngit.office.api.estimate.dto.EstimateDto;
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

@Tag(name = "설치팀 내역서 관리")
@RestController
@RequestMapping("/api")
public class BillController {
    //내역서 조회(결제월, 거래처명, 작업일자, 내역서, 상세내역서, 삭제, 작업구분, 수량, 공제금액, 하자보증금액, 지원금, 총금액, 결제날짜, 실결제금액, 미수금)
    // 등록, 삭제, 엑셀 다운로드 (내역서/상세내역서)
    private static final Logger logger = LoggerFactory.getLogger(BillController.class);

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @Operation(summary = "설치팀 내역서 목록 조회")
    @GetMapping("/bill")
    public ApiResponse<List<BillDto>> getOrSearchListBill(BillSearchDto billSearchDto) {
        logger.info("설치팀 내역서 조회");
        int count = billService.countGetOrSearchListBill(billSearchDto);
        List<BillDto> result = billService.getOrSearchListBill(billSearchDto);
        return new ApiResponse<> (result, 0, "설치팀 내역서 목록 조회 완료", count);
    }

    @Operation(summary = "설치팀 내역서 등록", description = "필수입력: ")
    @PostMapping("/bill")
    public ApiResponse<String> registerBill(@RequestBody BillDto billDto) {
        logger.info("설치팀 내역서 등록");
        int result = billService.registerBill(billDto);
        if(result == 0)
            return new ApiResponse<>("내역서 등록 완료", 0);
        else
            return new ApiResponse<>("내역서 등록 실패", 1);
    }

    @Operation(summary = "설치팀 내역서 수정")
    @PutMapping("/bill")
    public ApiResponse<String> updateBill(@RequestBody BillDto billDto) {
        logger.info("설치팀 내역서 수정");
        int result = billService.updateBill(billDto);
        if(result == 1)
            return new ApiResponse<>("내역서 수정 완료", 0);
        else
            return new ApiResponse<>("내역서 수정 실패", 1);
    }

    @Operation(summary = "설치팀 내역서 삭제")
    @DeleteMapping("/bill")
    public ApiResponse<String> deleteBill(@RequestBody String billUniqNo) {
        logger.info("설치팀 내역서 삭제");
        int result = billService.deleteBill(billUniqNo);
        if(result == 1)
            return new ApiResponse<>("내역서 삭제 완료", 0);
        else
            return new ApiResponse<>("내역서 삭제 실패", 1);
    }

    @Operation(summary = "설치팀 내역서 엑셀 파일 다운로드")
    @GetMapping("/bill/download/excel")
    public ResponseEntity<Object> downloadBill(@RequestParam("estimateUniqNo") String billUniqNo) throws IOException {

        //엑셀파일 생성
        byte[] excelFile = billService.generateBillExcel(billUniqNo);

        //HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "estimate.xlsx");

        //ResponseEntity로 엑셀 파일 반환
        return new ResponseEntity<>(excelFile, headers, HttpStatus.OK);
    }

    @Operation(summary = "설치팀 내역서 엑셀 파일 첨부 이메일 발송 버튼") //업로드 된 내역서 있다면 업로드 파일 우선 발송, 없다면 등록된 내역서 발송
    @GetMapping("/bill/email")
    public ApiResponse<String> sendEmailBill(BillDto billDto) {
        logger.info("설치팀 내역서 이메일 발송");
        int result = billService.sendEmailBill(billDto);
        if(result == 1)
            return new ApiResponse<>("이메일 발송 완료", 0);
        else
            return new ApiResponse<>("이메일 발송 실패", 1);
    }
}
