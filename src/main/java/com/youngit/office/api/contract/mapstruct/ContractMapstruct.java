package com.youngit.office.api.contract.mapstruct;

import com.youngit.office.api.contract.dto.ContractDetailDto;
import com.youngit.office.api.contract.dto.ContractDto;
import com.youngit.office.api.contract.dto.ContractProductDto;
import com.youngit.office.api.contract.model.ContractDetailModel;
import com.youngit.office.api.contract.model.ContractModel;
import com.youngit.office.api.contract.model.ContractProductModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractMapstruct {
    ContractDto toDto(ContractModel contractModel);
    ContractModel toModel(ContractDto contractDto);

    ContractProductDto toProductDto(ContractProductModel contractProductModel);
    ContractProductModel toProductModel(ContractProductDto contractProductDto);

    ContractDetailDto toDetailDto(ContractDetailModel contractDetailModel);
    ContractDetailModel toDetailModel(ContractDetailDto contractDetailDto);

}
