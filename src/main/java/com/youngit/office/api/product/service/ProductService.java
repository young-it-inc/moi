package com.youngit.office.api.product.service;

import com.youngit.office.api.product.mapper.ProductMapper;
import com.youngit.office.api.product.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper;

    public List<ProductModel> getListProduct() {

        return productMapper.getListProduct();
    }

    public int registerProduct(ProductModel productModel) {
        return productMapper.registerProduct(productModel);

    }

    public int updateProduct(ProductModel productModel) {
        return productMapper.updateProduct(productModel);

    }

    public int deleteProduct(String productId) {
        return productMapper.deleteProduct(productId);
    }
}
