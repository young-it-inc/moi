package com.youngit.office.api.contract.service;

import com.youngit.office.api.contract.dto.ContractDto;
import com.youngit.office.api.contract.mapper.ContractMapper;
import com.youngit.office.api.contract.mapstruct.ContractMapstruct;
import com.youngit.office.api.contract.model.ContractModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ContractService {

    private final ContractMapper contractMapper;
    private final ContractMapstruct contractMapstruct;
    @Autowired
    public ContractService(ContractMapper contractMapper, ContractMapstruct contractMapstruct) {
        this.contractMapper = contractMapper;
        this.contractMapstruct = contractMapstruct;
    }

    /**
     * 계약 조회
     * @param contractType
     * @return
     */
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
        return contractModelList.stream().map(contractMapstruct::toDto).toList();
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
        return contractMapstruct.toDto(contractModel);
    }

    /**
     * 계약번호 생성
     * @return
     */
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

    /**
     * 계약 등록
     * @param contractDto
     * @return
     */
    @Transactional //중간에 에러가 나면 이전 실행된 쿼리문 롤백
    public int registerContract(ContractDto contractDto) {
        int result = 0;
        ContractModel contractModel = contractMapstruct.toModel(contractDto);
        result = contractMapper.registerContract(contractModel);
        result += contractMapper.registerContractDetail(contractModel.getContractDetailList());
        result += contractMapper.registerContractProduct(contractModel.getContractProductList());
        return result;
    }

    /**
     * 계약 수정
     * @param contractDto
     * @return
     */
    @Transactional
    public int updateContract(ContractDto contractDto) {
        int result = 0;
        ContractModel contractModel = contractMapstruct.toModel(contractDto);
        result = contractMapper.updateContract(contractModel);
        result += contractMapper.updateContractDetail(contractModel.getContractDetailList());
        result += contractMapper.updateContractProduct(contractModel.getContractProductList());
        return result;
    }

    /**
     * 계약 삭제
     * @param contractUniqNo
     * @return
     */
    public int deleteContract(String contractUniqNo) {
        int result = contractMapper.deleteContract(contractUniqNo);
        return result;
    }



}
