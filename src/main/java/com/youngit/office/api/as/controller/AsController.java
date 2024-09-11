package com.youngit.office.api.as.controller;

import com.youngit.office.api.as.dto.AsDto;
import com.youngit.office.api.as.service.AsService;
import com.youngit.office.api.http.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Tag(name = "AS 관리")
@RestController
@RequestMapping("/api")
public class AsController {
    //지자체별 제품사용 처리방법 다름. 설치팀 교육은 하지만 설치팀도 yit담당자도 업무혼선 많아 통화량 증가. 지자체별 스탠다드 설정 후 설치팀 PDA와 연동가능?
    //경과관리와의 관계 정리(고민중)
    //as시 제품비용 발생되는 수용가 관리 후 100%청구(현재 누락건 많음, 담당자 아니면 모름)
    //ex) as결과코드(단제교체, 검침기교체), as원인코드(분실, 파손) 수용가는 따로 조회하여 해당 사업소에 비용청구하기, 청구완료버튼 누르면 완료되게.

    //as현황: 유지보수 총 수량대비 처리/잔여수량 확인필요(현재처럼 설치팀별로 확인 필요)

    //as통계: 현재는 as원인코드로만 검색가능. >>결과코드 추가 필요, 설치팀별 일평균 as수량 자동계산되게.

    private static final Logger logger = Logger.getLogger(AsController.class.getName());
    private final AsService asService;

    @Autowired
    public AsController(AsService asService) {

        this.asService = asService;
    }

    @Operation(summary = "AS 리스트 + 상태별 조회")
    @GetMapping("/as")
    public ApiResponse<List<AsDto>> getListAs(@RequestParam(value = "asStateCode", required = false) String asStateCode) {
        logger.info("AS 리스트 + 상태별 조회");
        int count = asService.getCountListAs(asStateCode);
        List<AsDto> result = asService.getListAs(asStateCode);
        return new ApiResponse<>(result, 0, "as 리스트 조회 완료", count);
    }


    @Operation(summary = "AS 개별 조회")
    @GetMapping("/as/{asUniqId}")
    public ApiResponse<AsDto> getOneAs(String asUniqId) {
        logger.info("AS 개별 조회");
        AsDto result = asService.getOneAs(asUniqId);
        return new ApiResponse<>(result, 0, "as 개별 조회 완료");
    }


    @Operation(summary = "AS 등록")
    @PostMapping("/as")
    public ApiResponse<String> registerAs(AsDto asDto) {
        int result = asService.registerAs(asDto);
        if (result == 1)
            return new ApiResponse<>("as 등록 성공");
        else
            return new ApiResponse<>("as 등록 실패");
    }

    @Operation(summary = "AS 수정")
    @PutMapping("/as")
    public ApiResponse<String> updateAs(AsDto asDto) {
        int result = asService.updateAs(asDto);
        if (result == 1)
            return new ApiResponse<>("as 수정 성공");
        else
            return new ApiResponse<>("as 수정 실패");
    }

    @Operation(summary = "AS 삭제")
    @DeleteMapping("/as")
    public ApiResponse<String> deleteAs(String asUniqId) {
        int result = asService.deleteAs(asUniqId);
        if (result == 1)
            return new ApiResponse<>("as 삭제 성공");
        else
            return new ApiResponse<>("as 삭제 실패");
    }


}
