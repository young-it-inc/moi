package com.youngit.office.api.bill.mapper;

import com.youngit.office.api.bill.dto.BillDto;
import com.youngit.office.api.bill.model.BillModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BillMapper {

    BillDto toDto(BillModel billModel);
    BillModel toModel(BillDto billDto);
    List<BillDto> toDtoList(List<BillModel> billModelList);
    List<BillModel> toModelList(List<BillDto> billDtoList);


    List<BillModel> getListBill();
    BillModel getOneBill(String billId);

    int registerBill(BillModel billModel);


    int updateBill(BillModel billModel);

    int deleteBill(String billId);



}
