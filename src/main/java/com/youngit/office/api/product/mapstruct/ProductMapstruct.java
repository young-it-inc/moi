package com.youngit.office.api.product.mapstruct;

import com.youngit.office.api.product.dto.ProductDto;
import com.youngit.office.api.product.model.ProductModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapstruct {
    ProductDto toDto(ProductModel productModel);
    ProductModel toModel(ProductDto productDto);
}
