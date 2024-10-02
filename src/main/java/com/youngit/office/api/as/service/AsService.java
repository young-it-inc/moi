package com.youngit.office.api.as.service;

import com.youngit.office.api.as.dto.AsDto;
import com.youngit.office.api.as.dto.AsSearchDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AsService {

    List<AsDto> getOrSearchListAs(AsSearchDto asSearchDto);
    int countGetOrSearchListAs(AsSearchDto asSearchDto);
    AsDto getOneAs(String asUniqId);
    int registerAs(AsDto asDto);

    int registerBatchAs(String memberId, MultipartFile file) throws Exception;
    int updateAs(AsDto asDto);

    int deleteAs(String asUniqId);
    byte[] generateExcelAsList(List<AsDto> asDtoList) throws IOException;
}
