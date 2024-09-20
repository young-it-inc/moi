package com.youngit.office.api.contract.mapper;

import com.youngit.office.api.contract.dto.ContractSearchDto;
import com.youngit.office.api.contract.model.ContractDetailModel;
import com.youngit.office.api.contract.model.ContractModel;
import com.youngit.office.api.contract.model.ContractProductModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractMapper {

    /**
     * 계약 조회 및 검색
     */
    List<ContractModel> getOrSearchListContract(ContractSearchDto contractSearchDto);
    int getCountListContract(ContractSearchDto contractSearchDto);
    ContractModel getOneContract(String contractUniqNo);


    /**
     * 계약 등록
     */
    int registerContract(ContractModel contractModel);
    int registerContractDetail(List<ContractDetailModel> contractDetailModels);
    int registerContractProduct(List<ContractProductModel> contractProductListModels);
    //계약 번호 생성
    String getLastContractUniqNo(String todayDate);


    /**
     * 계약 수정
     */
    int updateContract(ContractModel contractModel);
    int deleteContractDetail(String contractUniqNo); //사실상 수정(삭제->재등록)
    int deleteContractProduct(String contractId); //사실상 수정 (삭제->재등록)


    /**
     * 계약 삭제
     */
    int deleteContract(String contractId); //사실상 수정(is_used = 'Y'->'N')


}
