package com.youngit.office.api.contract.service;

import com.youngit.office.api.contract.dto.ContractDto;
import com.youngit.office.api.contract.mapper.ContractMapper;
import com.youngit.office.api.contract.model.ContractModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Service
public class ContractService {
    private final ContractMapper contractMapper;

    @Autowired
    public ContractService(ContractMapper contractMapper) {

        this.contractMapper = contractMapper;
    }

    public ContractDto convertToDto(ContractModel contractModel) {
        return contractMapper.toDto(contractModel);
    }

    public List<ContractDto> convertToDtoList(List<ContractModel> contractModelList) {
        return contractMapper.toDtoList(contractModelList);
    }

    public ContractModel convertToModel(ContractDto contractDto) {
        return contractMapper.toModel(contractDto);
    }
    public List<ContractModel> convertToModelList(List<ContractDto> contractDtoList) {
        return contractMapper.toModelList(contractDtoList);
    }

    public List<ContractDto> getListContract(String contractType) {
        List<ContractModel> contractModelList;
        switch (contractType) {
            case "gov":
                contractModelList = contractMapper.getListGovContract();
                break;
            case "general":
                contractModelList = contractMapper.getListGeneralContract();
                break;
            default:
                contractModelList = Collections.emptyList(); // null 대신 빈 리스트 반환(호출 측에서 null체크 안해도 돼서 안전)
                break;
        }
        return convertToDtoList(contractModelList);
    }

    public int getCountListContract(String contractType) {
        switch (contractType) {
            case "gov":
                return contractMapper.getCountListGovContract();
            case "general":
                return contractMapper.getCountListGeneralContract();
            default:
                return 0;
        }
    }

    public ContractDto getOneContract(String contractUniqNo) {
        ContractModel contractModel = contractMapper.getOneContract(contractUniqNo);
        return convertToDto(contractModel);
    }


    public String getNewContractUniqNo() {
        String todayDate = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
        String lastContractUniqNo = contractMapper.getLastContractUniqNo(todayDate);

        String newContractUniqNo;
        if (lastContractUniqNo == null) {
            newContractUniqNo = todayDate + "0001";
        } else {
            int lastNumber = Integer.parseInt(lastContractUniqNo.substring(8));
            String newNumber = String.format("%04d", lastNumber + 1);
            newContractUniqNo = todayDate + newNumber;
        }
        return newContractUniqNo;
    }

    @Transactional //중간에 에러가 나면 이전 실행된 쿼리문 롤백
    public int registerContract(ContractDto contractDto) {
        int result = 0;
        ContractModel contractModel = convertToModel(contractDto);
        result = contractMapper.registerContract(contractModel);
        result += contractMapper.registerContractDetail(contractModel.getContractDetailList());
        result += contractMapper.registerContractProduct(contractModel.getContractProductList());
        return result;
    }

    @Transactional
    public int updateContract(ContractDto contractDto) {
        int result = 0;
        ContractModel contractModel = convertToModel(contractDto);
        result = contractMapper.updateContract(contractModel);
        result += contractMapper.updateContractDetail(contractModel.getContractDetailList());
        result += contractMapper.updateContractProduct(contractModel.getContractProductList());
        return result;
    }

    public int deleteContract(String contractUniqNo) {
        int result = contractMapper.deleteContract(contractUniqNo);
        return result;
    }


}
