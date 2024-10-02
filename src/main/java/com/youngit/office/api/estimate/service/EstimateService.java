package com.youngit.office.api.estimate.service;

import com.youngit.office.api.estimate.dto.EstimateDto;
import com.youngit.office.api.estimate.dto.EstimateSearchDto;

import java.io.IOException;
import java.util.List;

public interface EstimateService {

    /**
     * 견적서 조회 및 검색
     */
    List<EstimateDto> getOrSearchListEstimate(EstimateSearchDto estimateSearchDto);
    int countGetOrSearchListEstimate(EstimateSearchDto estimateSearchDto);
    EstimateDto getOneEstimate(String estimateUniqNo);

    /**
     * 견적 번호 생성
     */
    String getNewEstimateUniqNo();

    /**
     * 견적서 등록
     */
    int registerEstimate(EstimateDto estimateDto);

    /**
     * 견적서 엑셀 파일 생성
     */
    byte[] generateEstimateExcel(String estimateUniqNo) throws IOException;

    /**
     * 견적서 리스트 엑셀 파일 생성
     */
    byte[] generateExcelEstimateList(List<EstimateDto> estimateDtoList) throws IOException;

    /**
     * 견적서 수정
     */
    int updateEstimate(EstimateDto estimateDto);

    /**
     * 견적서 삭제
     */
    int deleteEstimate(String estimateUniqNo);

    /**
     * 견적서 계약 진행
     */
    int registerEstimateContract(EstimateDto estimateDto);

    /**
     * 견적서 메일 발송(엑셀)
     */
    int sendEmailEstimate(EstimateDto estimateDto);

}
