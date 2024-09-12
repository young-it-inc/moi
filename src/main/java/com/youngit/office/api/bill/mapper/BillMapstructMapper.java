package com.youngit.office.api.bill.mapper;

import com.youngit.office.api.bill.dto.BillDto;
import com.youngit.office.api.bill.model.BillModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BillMapstructMapper {
    BillDto toDto(BillModel billModel);
    BillModel toModel(BillDto billDto);
    List<BillDto> toDtoList(List<BillModel> billModelList);
    List<BillModel> toModelList(List<BillDto> billDtoList);
}
