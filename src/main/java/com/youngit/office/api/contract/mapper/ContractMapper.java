package com.youngit.office.api.contract.mapper;

import com.youngit.office.api.contract.model.ContractDetailModel;
import com.youngit.office.api.contract.model.ContractModel;
import com.youngit.office.api.contract.model.ContractProductListModel;

import java.util.List;

public interface ContractMapper {

    //계약 등록
    int registerContract(ContractModel contractModel);
    int registerContractDetail(List<ContractDetailModel> contractDetailModels);
    int registerContractProduct(List<ContractProductListModel> contractProductListModels);

    //계약 번호 생성
    String getLastContractUniqNo(String todayDate);


    //계약 리스트 조회
    List<ContractModel> getListContract();


    int updateContract(ContractModel contractModel);
    int updateContractDetail(List<ContractDetailModel> contractDetailModels);
    int updateContracProduct(List<ContractProductListModel> contractProductListModels);

    int deleteContract(String contractId);
}
