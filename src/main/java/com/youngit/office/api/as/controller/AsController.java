package com.youngit.office.api.as.controller;

import com.youngit.office.api.as.model.AsModel;
import com.youngit.office.api.http.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation
    @GetMapping ("/as")
    public ApiResponse<List<AsModel>> getListAs() {
        return null;
    }

    @Operation()
    @PostMapping("/as")
    ApiResponse<String> registerAs(AsModel asModel) {
        return null;
    }

    @Operation()
    @PutMapping("/as")
    ApiResponse<String> updateAs(AsModel asModel) {
        return null;
    }

    @Operation
    @DeleteMapping("/as")
    ApiResponse<String> deleteAs(AsModel asModel) {
        return null;
    }


}
