package com.youngit.office.api.product.mapper;

import com.youngit.office.api.product.dto.ProductDto;
import com.youngit.office.api.product.model.ProductModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapstructMapper {

    ProductDto toDto(ProductModel productModel);
    ProductModel toModel(ProductDto productDto);
    List<ProductDto> toDtoList(List<ProductModel> productModelList);
    List<ProductModel> toModelList(List<ProductDto> productDtoList);

}
