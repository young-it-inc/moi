package com.youngit.office.api.contract.service;

import com.youngit.office.api.contract.dto.ContractDetailDto;
import com.youngit.office.api.contract.dto.ContractDto;
import com.youngit.office.api.contract.dto.ContractProductDto;
import com.youngit.office.api.contract.dto.ContractSearchDto;

import java.io.IOException;
import java.util.List;

public interface ContractService {
    List<ContractDto> getOrSearchListContract(ContractSearchDto contractSearchDto);
    int countGetOrSearchListContract(ContractSearchDto contractSearchDto);
    ContractDto getOneContract(String contractUniqNo);
    List<ContractDetailDto> getOneContractDetailList(String contractUniqNo);
    List<ContractProductDto> getOneContractProductList(String contractUniqNo);
    String getNewContractUniqNo();
    int registerContract(ContractDto contractDto);
    int updateContract(ContractDto contractDto);
    int deleteContract(String contractUniqNo);
    byte[] generateExcelEstimateList(List<ContractDto> contractDtoList) throws IOException;

}
