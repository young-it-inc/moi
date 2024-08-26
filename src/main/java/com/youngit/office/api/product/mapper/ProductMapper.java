package com.youngit.office.api.product.mapper;

import com.youngit.office.api.product.model.ProductModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<ProductModel> getListProduct();

    int registerProduct(ProductModel productModel);



    int updateProduct(ProductModel productModel);

    int deleteProduct(String productId);
}
