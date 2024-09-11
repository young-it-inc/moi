package com.youngit.office.api.contract.mapper;

import com.youngit.office.api.contract.dto.ContractDto;
import com.youngit.office.api.contract.model.ContractDetailModel;
import com.youngit.office.api.contract.model.ContractModel;
import com.youngit.office.api.contract.model.ContractProductModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractMapper {

    ContractDto toDto(ContractModel contractModel);
    ContractModel toModel(ContractDto contractDto);
    List<ContractDto> toDtoList(List<ContractModel> contractModelList);
    List<ContractModel> toModelList(List<ContractDto> contractDtoList);

    //계약 등록
    int registerContract(ContractModel contractModel);
    int registerContractDetail(List<ContractDetailModel> contractDetailModels);
    int registerContractProduct(List<ContractProductModel> contractProductListModels);

    //계약 번호 생성
    String getLastContractUniqNo(String todayDate);


    //계약 리스트 조회
    List<ContractModel> getListGovContract();
    int getCountListGovContract();
    List<ContractModel> getListGeneralContract();
    int getCountListGeneralContract();

    //계약 개별 조회
    ContractModel getOneContract(String contractUniqNo);


    int updateContract(ContractModel contractModel);
    int updateContractDetail(List<ContractDetailModel> contractDetailModels);
    int updateContractProduct(List<ContractProductModel> contractProductListModels);

    int deleteContract(String contractId);
    int deleteContractDetail(String contractId);
    int deleteContractProduct(String contractId);
}
