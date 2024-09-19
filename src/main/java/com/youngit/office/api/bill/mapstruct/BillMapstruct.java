package com.youngit.office.api.bill.mapstruct;

import com.youngit.office.api.bill.dto.BillDto;
import com.youngit.office.api.bill.model.BillModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BillMapstruct {
    BillDto toDto(BillModel billModel);
    BillModel toModel(BillDto billDto);

}
