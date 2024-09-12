package com.youngit.office.api.contract.mapper;

import com.youngit.office.api.contract.dto.ContractDto;
import com.youngit.office.api.contract.model.ContractModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContractMapstructMapper {

    ContractDto toDto(ContractModel contractModel);
    ContractModel toModel(ContractDto contractDto);
    List<ContractDto> toDtoList(List<ContractModel> contractModelList);
    List<ContractModel> toModelList(List<ContractDto> contractDtoList);
}
