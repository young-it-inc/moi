package com.youngit.office.api.bill.service;

import com.youngit.office.api.bill.dto.BillDto;
import com.youngit.office.api.bill.mapper.BillMapper;
import com.youngit.office.api.bill.mapstruct.BillMapstruct;
import com.youngit.office.api.bill.model.BillModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BillService {

    private final BillMapper billMapper;
    private final BillMapstruct billMapstruct;

    @Autowired
    public BillService(BillMapper billMapper, BillMapstruct billMapstruct) {
        this.billMapper = billMapper;
        this.billMapstruct = billMapstruct;
    }

    public List<BillModel> getListBill() {

        return billMapper.getListBill();

    }

    public int registerBill(BillDto billDto) {
        BillModel billModel = billMapstruct.toModel(billDto);
        return billMapper.registerBill(billModel);
    }
    public int updateBill(BillDto billDto) {
        BillModel billModel = billMapstruct.toModel(billDto);
        return billMapper.updateBill(billModel);
    }

    public int deleteBill(String billId) {
        return  billMapper.deleteBill(billId);
    }


}
