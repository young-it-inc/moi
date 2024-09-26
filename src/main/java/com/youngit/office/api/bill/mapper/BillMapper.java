package com.youngit.office.api.bill.mapper;

import com.youngit.office.api.bill.dto.BillSearchDto;
import com.youngit.office.api.bill.model.BillDetailModel;
import com.youngit.office.api.bill.model.BillModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BillMapper {

    List<BillModel> getOrSearchListBill(BillSearchDto billSearchDto);
    int countGetOrSearchListBill(BillSearchDto billSearchDto);
    BillModel getOneBill(@Param("billUniqNo") String billUniqNo);

    List<BillDetailModel> getBillDetailModelList(@Param("billUniqNo") String billUniqNo);

    int registerBill(BillModel billModel);
    int updateBill(BillModel billModel);

    int deleteBill(@Param("billUniqNo") String billUniqNo);


}
