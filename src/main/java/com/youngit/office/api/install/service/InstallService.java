package com.youngit.office.api.install.service;

import com.youngit.office.api.install.dto.InstallDto;
import com.youngit.office.api.install.dto.InstallSearchDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface InstallService {
    List<InstallDto> getOrSearchListInstall(InstallSearchDto installSearchDto);
    int countGetOrSearchListInstall(InstallSearchDto installSearchDto);
    InstallDto getOneInstall(String installUniqId);
    int registerInstall(InstallDto installDto);
    int registerBatchInstall(String memberId, MultipartFile file) throws Exception;
    /**
     * 설치 수정
     */
    int updateInstall(InstallDto installDto);
    /**
     * 설치 삭제
     */
    int deleteInstall(String installUniqId);
    /**
     * 설치 리스트 엑셀 파일 생성
     */
    byte[] generateExcelInstallList(List<InstallDto> installDtoList) throws IOException;
    /**
     * 설치 리스트 PDF 파일 생성
     */
    byte[] generatePdfInstallList(List<InstallDto> installDtoList);
}
