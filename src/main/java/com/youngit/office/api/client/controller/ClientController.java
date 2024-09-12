package com.youngit.office.api.client.controller;

import com.youngit.office.api.client.dto.ClientDto;
import com.youngit.office.api.client.service.ClientService;
import com.youngit.office.api.http.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Tag(name="거래처 관리")
@RestController
@RequestMapping("/api")
public class ClientController {
//거래처 조회, 검색, 등록, 수정, 삭제, 엑셀출력

    private static final Logger logger = Logger.getLogger(ClientController.class.getName());
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        System.out.println(" 여기는 controller");
        this.clientService = clientService;
    }


    @Operation(summary = "거래처 리스트 조회")
    @GetMapping("/client")
    public ApiResponse<List<ClientDto>> getListClient() {
        logger.info("거래처 리스트 조회");
        List<ClientDto> result = clientService.getListClient();
        return new ApiResponse<>(result, 0, "거래처 리스트 조회 성공");
    }

    @Operation(summary = "거래처 개별 조회")
    @GetMapping("/client/{clientUniqId}")
    public ApiResponse<ClientDto> getOneClient(@PathVariable String clientUniqId) {
        logger.info("거래처 개별 조회");
        ClientDto result = clientService.getOneClient(clientUniqId);
        return new ApiResponse<>(result, 0, "거래처 개별 조회 성공");
    }
    @Operation(summary = "거래처 등록", description = "필수입력: 거래처명, 구분코드, 대표자명,")
    @PostMapping("/client")
    public ApiResponse<String> registerClient(@RequestBody ClientDto clientDto) {
        logger.info("거래처 등록");
        int result = clientService.registerClient(clientDto);
        if(result == 1)
            return new ApiResponse<>("거래처 등록 성공");
        else
            return new ApiResponse<>("거래처 등록 실패");
    }

    @Operation(summary = "사업자등록번호 중복 체크", description = "필수입력: 사업자등록번호")
    @PostMapping("/client/biznumber")
    public ApiResponse<String> checkBizNumber(String bizNumber)
    {
        logger.info("사업자등록번호 중복 체크");
        boolean isExist = clientService.checkBizNumber(bizNumber);
        if (isExist)
            return new ApiResponse<>("사업자등록번호 중복");
        else
            return new ApiResponse<>("사업자등록번호 중복아님");
    }

    @Operation(summary = "거래처 확인 및 수정", description = "필수입력: 거래처명, 구분코드, 대표자명. / 수정시 담당자 추가/삭제도 확인 필요")
    @PutMapping("/client")
    public ApiResponse<String> updateClient(@RequestBody ClientDto clientDto) {
        logger.info("거래처 수정");
        int result = clientService.updateClient(clientDto);
        if(result == 1)
            return new ApiResponse<>("거래처 수정 성공");
        else
            return new ApiResponse<>("거래처 수정 실패");
    }

    @Operation(summary = "거래처 삭제", description = "삭제해도 db에는 남아있고 is_used = 'N'으로 변경됨.")
    @DeleteMapping("/client")
    public ApiResponse<String> deleteClient(@RequestBody String clientUniqId) {
        logger.info("거래처 삭제");
        int result = clientService.deleteClient(clientUniqId);
        if(result == 1)
            return new ApiResponse<>("거래처 삭제 성공");
        else
            return new ApiResponse<>("거래처 삭제 실패");
    }

    //엑셀
    @Operation(summary = "거래처 리스트 엑셀 다운로드")
    public ApiResponse<String> excelDownloadClient() {
        return new ApiResponse<>("엑셀 출력 성공");
    }


}
