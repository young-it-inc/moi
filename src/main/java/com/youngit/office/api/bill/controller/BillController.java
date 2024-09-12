package com.youngit.office.api.bill.controller;

import com.youngit.office.api.bill.dto.BillDto;
import com.youngit.office.api.bill.model.BillModel;
import com.youngit.office.api.bill.service.BillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Tag(name = "내역서 관리")
@RestController
@RequestMapping("/api")
public class BillController {
    //내역서 조회(결제월, 거래처명, 작업일자, 내역서, 상세내역서, 삭제, 작업구분, 수량, 공제금액, 하자보증금액, 지원금, 총금액, 결제날짜, 실결제금액, 미수금)
    // 등록, 삭제, 엑셀 다운로드 (내역서/상세내역서)

    private static final Logger logger = Logger.getLogger(BillController.class.getName());

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @Operation(summary = "내역서 조회")
    @GetMapping("/bill")
    public List<BillModel> getListBill() {
        logger.info("내역서 조회");
        return billService.getListBill();
    }

    @Operation(summary = "내역서 등록", description = "필수입력: ")
    @PostMapping("/bill")
    public void registerBill(@RequestBody BillDto billDto) {
        logger.info("내역서 등록");
        int result = billService.registerBill(billDto);
    }

    @Operation(summary = "내역서 수정")
    @PutMapping("/bill")
    public void updateBill(@RequestBody BillDto billDto) {
        logger.info("내역서 수정");
        billService.updateBill(billDto);
    }

    @Operation(summary = "내역서 삭제")
    @DeleteMapping("/bill")
    public void deleteBill(@RequestBody String billUniqNo) {
        logger.info("내역서 삭제");
        billService.deleteBill(billUniqNo);
    }
}
