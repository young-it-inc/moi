package com.youngit.office.api.estimate.service;

import com.youngit.office.api.estimate.dto.EstimateDto;
import com.youngit.office.api.estimate.mapper.EstimateMapper;
import com.youngit.office.api.estimate.model.EstimateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class EstimateService {

    private final EstimateMapper estimateMapper;
    @Autowired
    public EstimateService(EstimateMapper estimateMapper) {
        this.estimateMapper = estimateMapper;
    }
    public EstimateDto convertToDto(EstimateModel estimateModel) {
        return estimateMapper.toDto(estimateModel);
    }
    public List<EstimateDto> convertToDtoList(List<EstimateModel> estimateModelList) {
        return estimateMapper.toDtoList(estimateModelList);
    }
    public EstimateModel convertToModel(EstimateDto estimateDto) {
        return estimateMapper.toModel(estimateDto);
    }

    public List<EstimateModel> convertToModelList(List<EstimateDto> estimateDtoList) {
        return estimateMapper.toModelList(estimateDtoList);
    }

    public List<EstimateDto> getListEstimate() {
        List<EstimateModel> resultModel= estimateMapper.getListEstimate();
        return convertToDtoList(resultModel);
    }
    public int getCountListEstimate() {
        return estimateMapper.getCountListEstimate();
    }

    public EstimateDto getOneEstimate(String estimateUniqNo) {
        EstimateModel estimateModel = estimateMapper.getOneEstimate(estimateUniqNo);
        return convertToDto(estimateModel);
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

    public int registerEstimate(EstimateDto estimateDto) {
        int result = 0;
        EstimateModel estimateModel = convertToModel(estimateDto);
        result = estimateMapper.registerEstimate(estimateModel);
        result += estimateMapper.registerEstimateProductList(estimateModel.getEstimateProducts());
        return result;
    }


    public int updateEstimate(EstimateDto estimateDto) {
        int result = 0;
        EstimateModel estimateModel = convertToModel(estimateDto);
        result = estimateMapper.updateEstimate(estimateModel);
        result += estimateMapper.updateEstimateProductList(estimateModel.getEstimateProducts());
        return result;
    }

    public int deleteEstimate(String estimateUniqNo) {

        return estimateMapper.deleteEstimate(estimateUniqNo);
    }
}
