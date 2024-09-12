package com.youngit.office.api.bill.service;

import com.youngit.office.api.bill.dto.BillDto;
import com.youngit.office.api.bill.mapper.BillMapper;
import com.youngit.office.api.bill.mapper.BillMapstructMapper;
import com.youngit.office.api.bill.model.BillModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BillService {

    private final BillMapper billMapper;
    private final BillMapstructMapper billMapstructMapper;

    @Autowired
    public BillService(BillMapper billMapper, BillMapstructMapper billMapstructMapper) {
        this.billMapper = billMapper;
        this.billMapstructMapper = billMapstructMapper;
    }

    public List<BillModel> getListBill() {

        return billMapper.getListBill();

    }

    public int registerBill(BillDto billDto) {
        BillModel billModel = billMapstructMapper.toModel(billDto);
        return billMapper.registerBill(billModel);
    }
    public int updateBill(BillDto billDto) {
        BillModel billModel = billMapstructMapper.toModel(billDto);
        return billMapper.updateBill(billModel);
    }

    public int deleteBill(String billId) {
        return  billMapper.deleteBill(billId);
    }


}
