package com.youngit.office.api.estimate.mapper;

import com.youngit.office.api.estimate.dto.EstimateDto;
import com.youngit.office.api.estimate.model.EstimateModel;
import com.youngit.office.api.estimate.model.EstimateProductModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EstimateMapper {

    EstimateDto toDto(EstimateModel estimateModel);
    EstimateModel toModel(EstimateDto estimateDto);
    List<EstimateDto> toDtoList(List<EstimateModel> estimateModelList);
    List<EstimateModel> toModelList(List<EstimateDto> estimateDtoList);


    List<EstimateModel> getListEstimate();
    int getCountListEstimate();
    EstimateModel getOneEstimate(String estimateUniqNo);


    String getLastEstimateUniqNo(String todayDate);


    int registerEstimate(EstimateModel estimateModel);
    int updateEstimate(EstimateModel estimateModel);
    int deleteEstimate(String estimateUniqNo);


    int registerEstimateProductList(List<EstimateProductModel> estimateProductModelList);
    int updateEstimateProductList(List<EstimateProductModel> estimateProductModelList);
}
