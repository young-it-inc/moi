package com.youngit.office.api.estimate.service;

import com.youngit.office.api.estimate.mapper.EstimateMapper;
import com.youngit.office.api.estimate.model.EstimateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class EstimateService {

    @Autowired
    EstimateMapper estimateMapper;

    public List<EstimateModel> getListEstimate() {

        return estimateMapper.getListEstimate();
    }
    public int getCountListEstimate() {
        return estimateMapper.getCountListEstimate();
    }

    public EstimateModel getOneEstimate(String estimateUniqNo) {
        return estimateMapper.getOneEstimate(estimateUniqNo);
    }

    public String getNewEstimateUniqNo()
    {
        String todayDate = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
        String lastEstimateUniqNo = estimateMapper.getLastEstimateUniqNo(todayDate);

        String newEstimateUniqNo;

        if(lastEstimateUniqNo == null) {
            newEstimateUniqNo = todayDate + "0001";
        }
        else {
            int lastNumber = Integer.parseInt(lastEstimateUniqNo.substring(8));
            String newNumber = String.format("%04d", lastNumber + 1);
            newEstimateUniqNo = todayDate + newNumber;
        }
        return  newEstimateUniqNo;
    }

    public int registerEstimate(EstimateModel estimateModel) {

        int result = 0;
        result = estimateMapper.registerEstimate(estimateModel);
        result += estimateMapper.registerEstimateProducts(estimateModel.getEstimateProducts());
        return result;
    }


    public int updateEstimate(EstimateModel estimateModel) {

        int result = 0;
        result = estimateMapper.updateEstimate(estimateModel);
        result += estimateMapper.updateEstimateProducts(estimateModel.getEstimateProducts());
        return result;
    }

    public int deleteEstimate(EstimateModel estimateModel) {

        return estimateMapper.deleteEstimate(estimateModel);
    }
}
