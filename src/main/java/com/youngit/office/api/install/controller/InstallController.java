package com.youngit.office.api.install.controller;

import com.youngit.office.api.http.ApiResponse;
import com.youngit.office.api.install.dto.InstallDto;
import com.youngit.office.api.install.dto.InstallSearchDto;
import com.youngit.office.api.install.service.InstallService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Tag(name = "설치 관리")
@RestController
@RequestMapping("/api")
public class InstallController {

    private static final Logger logger = LoggerFactory.getLogger(InstallController.class);

    private final InstallService installService;
    @Autowired
    public InstallController(InstallService installService) {
        this.installService = installService;
    }

    @Operation(summary = "설치 리스트 + 상태별 조회")
    @GetMapping("/install")
    public ApiResponse<List<InstallDto>> getOrSearchListInstall(InstallSearchDto installsearchDto) {
        int count = installService.countGetOrSearchListInstall(installsearchDto);
        List<InstallDto> result = installService.getOrSearchListInstall(installsearchDto);
        return new ApiResponse<>(result, 0, "설치 리스트 조회 및 검색 완료", count);
    }

    @Operation(summary = "설치 개별 조회")
    @GetMapping("/install/{installUniqId}")
    public ApiResponse<InstallDto> getOneInstall(String installUniqId) {
        InstallDto result = installService.getOneInstall(installUniqId);
        return new ApiResponse<>(result, 0, "설치 개별 조회 완료");
    }

    @Operation(summary = "신규설치 등록")
    @PostMapping("/install")
    public ApiResponse<String> registerInstall(@RequestParam() InstallDto installDto) {
        int result = installService.registerInstall(installDto);
        if (result == 1) {
            return new ApiResponse<>("설치 등록 성공");
        } else {
            return new ApiResponse<>("설치 등록 실패");
        }
    }

    @Operation(summary = "신규설치 일괄 등록 엑셀 양식 다운로드")
    @GetMapping("/install/downloadexcel")
    public ResponseEntity<Object> registerBatchInstall()
    {
        String fileName = "install_upload_form.xlsx";
        Resource resource = new ClassPathResource("form/" + fileName);

        if (!resource.exists()) {
            logger.error("파일 다운로드 실패: 파일이 존재하지 않습니다.");
            return new ResponseEntity<>("파일을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fileName).build());
        headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @Operation(summary = "신규설치 일괄 등록 엑셀 업로드 : 1개라도 조건 불충족 수용가 있을 시 전체 등록 안됨.(예시줄 삭제해야)")
    @PostMapping("/install/uploadexcel")
    public ApiResponse<String> registerBatchInstall(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) throws Exception
    {
        int count = installService.registerBatchInstall(id, file);

        return new ApiResponse<>("설치 일괄 등록 성공", count);
    }

    @Operation(summary = "신규설치 수정")
    @PutMapping("/install")
    public ApiResponse<String> updateInstall(InstallDto installDto) {
        int result = installService.updateInstall(installDto); //설치수량, 잔여수량, 준공률 : 계약 db에도 반영돼야.
        if (result == 1) {
            return new ApiResponse<>("신규설치 수정 성공");
        } else {
            return new ApiResponse<>("신규설치 수정 실패");
        }
    }
    @Operation(summary = "이전설치 등록")
    @PostMapping("/reloactedinstall")
    public ApiResponse<String> registerRelocateInstall(InstallDto installDto) {
        int result = installService.registerInstall(installDto);
        if (result == 1) {
            return new ApiResponse<>("이전설치 등록 성공");
        } else {
            return new ApiResponse<>("이전설치 등록 실패");
        }
    }

    @Operation(summary = "경과재설치 등록")
    @PostMapping("/elapseinstall")
    public ApiResponse<String> registerElapseInstall(InstallDto installDto) {
        int result = installService.registerInstall(installDto);
        if (result == 1) {
            return new ApiResponse<>("경과재설치 등록 성공");
        } else {
            return new ApiResponse<>("경과재설치 등록 실패");
        }
    }

    @Operation(summary = "설치 삭제")
    @DeleteMapping("install")
    public ApiResponse<String> deleteInstall(String installUniqId)
    {
        int result = installService.deleteInstall(installUniqId);
        if (result == 1) {
            return new ApiResponse<>("설치 삭제 성공");
        } else {
            return new ApiResponse<>("설치 삭제 실패");
        }
    }

    @Operation(summary = "설치 리스트 PDF 파일 다운로드")
    @GetMapping("install/pdf")
    public ResponseEntity<byte[]>generatePDF(List<InstallDto> installDtoList) {
        byte[] pdfFile = installService.generatePdfInstallList(installDtoList);
        return null;
    }

    @Operation(summary = "설치 리스트 엑셀 다운로드")
    @GetMapping("/install/listexcel")
    public ResponseEntity<Object> downloadExcelInstallList(@RequestBody(required = false) List<InstallDto> installDtoList) throws IOException {
        //엑셀 파일 생성
        byte[] excelFile = installService.generateExcelInstallList(installDtoList);
        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "install_list.xlsx");

        return new  ResponseEntity<>(excelFile, headers, HttpStatus.OK);
    }

    @GetMapping("install-test")
    public Install test(@RequestParam("name") String name) {
        Install install = new Install();
        install.setName(name);
        return install;
    }

     class Install {
        String name;
    public String getName() { //자바빈규약
                return name;
            }


        public void setName(String name) {
            this.name = name;
        }
    }


}