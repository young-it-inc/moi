package com.youngit.office.api.bill.service;

import com.youngit.office.api.bill.mapper.BillMapper;
import com.youngit.office.api.bill.model.BillModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BillService {

    @Autowired
    BillMapper billMapper;

    public List<BillModel> getListBill() {
        return billMapper.getListBill();
    }

    public int registerBill(BillModel billModel) {
        return billMapper.registerBill(billModel);
    }
    public int updateBill(BillModel billModel) {
        return billMapper.updateBill(billModel);
    }

    public int deleteBill(String billId) {
        return  billMapper.deleteBill(billId);
    }


}
