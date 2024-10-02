package com.youngit.office.api.bill.service;

import com.youngit.office.api.bill.dto.BillDto;
import com.youngit.office.api.bill.dto.BillSearchDto;

import java.io.IOException;
import java.util.List;

public interface BillService {

    List<BillDto> getOrSearchListBill(BillSearchDto billSearchDto);

    int countGetOrSearchListBill(BillSearchDto billSearchDto);

    BillDto getOneBill(String billId);
    int registerBill(BillDto billDto);
    int updateBill(BillDto billDto);
    int deleteBill(String billId);

    byte[] generateBillExcel(String billUniqNo) throws IOException;

    byte[] generateExcelBillList(List<BillDto> billDtoList) throws IOException;

    int sendEmailBill(BillDto billDto);

}
