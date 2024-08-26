package com.youngit.office.api.estimate.mapper;

import com.youngit.office.api.estimate.model.EstimateModel;
import com.youngit.office.api.estimate.model.EstimateProductModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EstimateMapper {

    List<EstimateModel> getListEstimate();

    String getLastEstimateUniqNo(String todayDate);


    int registerEstimate(EstimateModel estimateModel);

    int registerEstimateProducts(List<EstimateProductModel> estimateDetailModels);

    int updateEstimate(EstimateModel estimateModel);
    int updateEstimateProducts(List<EstimateProductModel> estimateDetailModels);


    int deleteEstimate(EstimateModel estimateModel);
}
