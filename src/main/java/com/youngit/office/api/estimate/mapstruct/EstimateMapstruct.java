package com.youngit.office.api.estimate.mapstruct;

import com.youngit.office.api.estimate.dto.EstimateDto;
import com.youngit.office.api.estimate.dto.EstimateProductDto;
import com.youngit.office.api.estimate.model.EstimateModel;
import com.youngit.office.api.estimate.model.EstimateProductModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstimateMapstruct {
    EstimateDto toDto(EstimateModel estimateModel);
    EstimateProductDto toDto(EstimateProductModel estimateProductModel);
    EstimateModel toModel(EstimateDto estimateDto);
    EstimateProductModel toModel(EstimateProductDto estimateProductDto);
}
