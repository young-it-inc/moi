package com.youngit.office.api.contract.mapstruct;

import com.youngit.office.api.contract.dto.ContractDto;
import com.youngit.office.api.contract.model.ContractModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractMapstruct {
    ContractDto toDto(ContractModel contractModel);
    ContractModel toModel(ContractDto contractDto);

}
