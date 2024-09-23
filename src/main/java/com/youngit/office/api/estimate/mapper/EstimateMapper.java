package com.youngit.office.api.estimate.mapper;

import com.youngit.office.api.estimate.dto.EstimateSearchDto;
import com.youngit.office.api.estimate.model.EstimateModel;
import com.youngit.office.api.estimate.model.EstimateProductModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EstimateMapper {

    List<EstimateModel> getOrSearchListEstimate(EstimateSearchDto estimateSearchDto);
    int countGetOrSearchListEstimate(EstimateSearchDto estimateSearchDto);

    EstimateModel getOneEstimate(String estimateUniqNo);
    List<EstimateProductModel> getEstimateProductList(String estimateUniqNo);


    String getLastEstimateUniqNo(String todayDate);


    int registerEstimate(EstimateModel estimateModel);
    int updateEstimate(EstimateModel estimateModel);
    int deleteEstimate(String estimateUniqNo); //사실상 삭제x (isUsed= 'N'으로 변경)




    int registerEstimateProductList(List<EstimateProductModel> estimateProductModelList);
    int updateEstimateProductList(List<EstimateProductModel> estimateProductModelList);
    int deleteEstimateProductList(String estimateUniqNo); //삭제o
}
