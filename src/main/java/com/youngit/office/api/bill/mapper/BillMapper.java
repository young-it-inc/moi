package com.youngit.office.api.bill.mapper;

import com.youngit.office.api.bill.model.BillModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BillMapper {

    List<BillModel> getListBill();
    int getCountListBill();
    BillModel getOneBill(@Param("billId") String billId);

    int registerBill(BillModel billModel);
    int updateBill(BillModel billModel);

    int deleteBill(@Param("billId") String billId);

}
