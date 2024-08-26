package com.youngit.office.api.contract.service;

import com.youngit.office.api.contract.mapper.ContractMapper;
import com.youngit.office.api.contract.model.ContractModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ContractService {
    @Autowired
    ContractMapper contractMapper;

    public List<ContractModel> getListGeneralContract()
    {
        return contractMapper.getListContract();
    }

    public String getNewContractUniqNo()
    {
        String todayDate = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
        String lastContractUniqNo = contractMapper.getLastContractUniqNo(todayDate);

        String newContractUniqNo;
        if(lastContractUniqNo == null) {
            newContractUniqNo = todayDate + "0001";
        }
        else {
            int lastNumber = Integer.parseInt(lastContractUniqNo.substring(8));
            String newNumber = String.format("%04d", lastNumber + 1);
            newContractUniqNo = todayDate + newNumber;
        }
        return  newContractUniqNo;
    }

    @Transactional //중간에 에러가 나면 이전 실행된 쿼리문 롤백
    public int registerContract(ContractModel contractModel)
    {
        int result = 0;
        result = contractMapper.registerContract(contractModel);
        result += contractMapper.registerContractDetail(contractModel.getContractDetailList());
        result += contractMapper.registerContractProduct(contractModel.getContractProductList());
        return result;
    }

    @Transactional
    public int updateContract(ContractModel contractModel)
    {
        int result = 0;
        result = contractMapper.updateContract(contractModel);
        result += contractMapper.registerContractDetail(contractModel.getContractDetailList());
        result += contractMapper.registerContractProduct(contractModel.getContractProductList());
        return result;
    }

    public int deleteContract(String contractId)
    {
        return contractMapper.deleteContract(contractId);
    }


    public List<ContractModel> getListGovContract()
    {
        return contractMapper.getListContract();
    }



    public int deleteGovContract(String contractId)
    {
        return contractMapper.deleteContract(contractId);
    }





}
