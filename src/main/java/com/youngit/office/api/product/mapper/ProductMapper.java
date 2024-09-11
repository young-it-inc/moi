package com.youngit.office.api.product.mapper;

import com.youngit.office.api.product.dto.ProductDto;
import com.youngit.office.api.product.model.ProductModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductDto toDto(ProductModel productModel);
    ProductModel toModel(ProductDto productDto);
    List<ProductDto> toDtoList(List<ProductModel> productModelList);
    List<ProductModel> toModelList(List<ProductDto> productDtoList);


    List<ProductModel> getListProduct();
    int getCountListProduct();

    ProductModel getOneProduct(String productSerialNumber);



    int registerProduct(ProductModel productModel);

    int updateProduct(ProductModel productModel);

    int deleteProduct(String productId);
}
