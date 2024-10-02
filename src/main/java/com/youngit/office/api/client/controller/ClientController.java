package com.youngit.office.api.client.controller;

import com.youngit.office.api.client.dto.ClientDto;
import com.youngit.office.api.client.dto.ClientSearchDto;
import com.youngit.office.api.client.service.ClientService;
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

@Tag(name="거래처 관리")
@RestController
@RequestMapping("/api")
public class ClientController {
//거래처 조회, 검색, 등록, 수정, 삭제, 엑셀출력

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @Operation(summary = "거래처 리스트 조회 및 검색")
    @GetMapping("/client")
    public ApiResponse<List<ClientDto>> getOrSearchListClient(ClientSearchDto clientSearchDto) {
        int count = clientService.countGetOrSearchListClient(clientSearchDto);
        List<ClientDto> result = clientService.getOrSearchListClient(clientSearchDto);

        return new ApiResponse<>(result, 0, "거래처 리스트 조회 성공", count);
    }

    @Operation(summary = "거래처 개별 조회")
    @GetMapping("/client/{clientUniqId}")
    public ApiResponse<ClientDto> getOneClient(@PathVariable String clientUniqId) {
        ClientDto result = clientService.getOneClient(clientUniqId);
        return new ApiResponse<>(result, 0, "거래처 개별 조회 성공");
    }


    @Operation(summary = "거래처 등록", description = "필수입력: 거래처명, 구분코드, 대표자명,")
    @PostMapping("/client")
    public ApiResponse<String> registerClient(@RequestBody ClientDto clientDto) {
        int result = clientService.registerClient(clientDto);
        if(result == 1)
            return new ApiResponse<>("거래처 등록 성공");
        else
            return new ApiResponse<>("거래처 등록 실패");
    }

    @Operation(summary = "사업자등록번호 중복 체크", description = "필수입력: 사업자등록번호")
    @GetMapping("/client/biznumber")
    public ApiResponse<String> checkBizNumber(String bizNumber)
    {
        boolean isExist = clientService.checkBizNumber(bizNumber);
        if (isExist)
            return new ApiResponse<>("사업자등록번호 중복");
        else
            return new ApiResponse<>("사업자등록번호 중복아님");
    }

    @Operation(summary = "거래처 확인 및 수정", description = "필수입력: 거래처명, 구분코드, 대표자명. / 수정시 담당자 추가/삭제도 확인 필요")
    @PutMapping("/client")
    public ApiResponse<String> updateClient(@RequestBody ClientDto clientDto) {
        int result = clientService.updateClient(clientDto);
        if(result == 1)
            return new ApiResponse<>("거래처 수정 성공");
        else
            return new ApiResponse<>("거래처 수정 실패");
    }

    @Operation(summary = "거래처 삭제", description = "삭제해도 db에는 남아있고 is_used = 'N'으로 변경됨.")
    @DeleteMapping("/client")
    public ApiResponse<String> deleteClient(@RequestBody String clientUniqId) {
        int result = clientService.deleteClient(clientUniqId);
        if(result == 1)
            return new ApiResponse<>("거래처 삭제 성공");
        else
            return new ApiResponse<>("거래처 삭제 실패");
    }


    @Operation(summary = "거래처 리스트 엑셀 다운로드")
    @GetMapping("/client/excel")
    public ResponseEntity<Object> downloadExcelClientList(@RequestBody(required = false) List<ClientDto> clientDtoList) throws IOException {
        if(clientDtoList == null)
            clientDtoList = clientService.getOrSearchListClient(new ClientSearchDto());

        //엑셀 파일 생성
        byte[] excelFile = clientService.generateExcelClientList(clientDtoList);
        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "client_list.xlsx");

        return new  ResponseEntity<>(excelFile, headers, HttpStatus.OK);
    }


}
