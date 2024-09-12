package com.youngit.office.api.product.service;

import com.youngit.office.api.product.dto.ProductDto;
import com.youngit.office.api.product.mapper.ProductMapper;
import com.youngit.office.api.product.mapper.ProductMapstructMapper;
import com.youngit.office.api.product.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductMapstructMapper productMapstructMapper;

    @Autowired
    public ProductService(ProductMapper productMapper, ProductMapstructMapper productMapstructMapper) {
        this.productMapper = productMapper;
        this.productMapstructMapper = productMapstructMapper;
    }


    public List<ProductDto> getListProduct() {
        List<ProductModel> productModelList = productMapper.getListProduct();
        return productModelList.stream().map(productMapstructMapper::toDto).toList();
    }

    public int getCountListProduct() {
        return productMapper.getCountListProduct();
    }

    public ProductDto getOneProduct(String productSerialNumber) {
        ProductModel productModel = productMapper.getOneProduct(productSerialNumber);
        return productMapstructMapper.toDto(productModel);
    }

    public int registerProduct(ProductDto productDto) {
        ProductModel productModel = productMapstructMapper.toModel(productDto);
        return productMapper.registerProduct(productModel);
    }

    public int updateProduct(ProductDto productDto) {
        ProductModel productModel = productMapstructMapper.toModel(productDto);
        return productMapper.updateProduct(productModel);
    }

    public int deleteProduct(String productSerialNumber) {
        return productMapper.deleteProduct(productSerialNumber);
    }


}
