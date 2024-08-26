package com.youngit.office.api.bill.mapper;

import com.youngit.office.api.bill.model.BillModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BillMapper {

    int registerBill(BillModel billModel);
    List<BillModel> getListBill();

    int updateBill(BillModel billModel);

    int deleteBill(String billId);



}
