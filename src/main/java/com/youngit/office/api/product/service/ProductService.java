package com.youngit.office.api.product.service;

import com.youngit.office.api.product.dto.ProductDto;
import com.youngit.office.api.product.mapper.ProductMapper;
import com.youngit.office.api.product.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public ProductDto convertToDto(ProductModel productModel) {
        return productMapper.toDto(productModel);
    }

    public List<ProductDto> convertoToDtoList(List<ProductModel> productModelList) {
        return productMapper.toDtoList(productModelList);
    }

    public ProductModel convertToModel(ProductDto productDto) {
        return productMapper.toModel(productDto);
    }
    public List<ProductModel> convertToModelList(List<ProductDto> productDtoList) {
        return productMapper.toModelList(productDtoList);
    }

    public List<ProductDto> getListProduct() {
        List<ProductModel> productModelList = productMapper.getListProduct();
        return convertoToDtoList(productModelList);
    }

    public int getCountListProduct() {
        return productMapper.getCountListProduct();
    }

    public ProductDto getOneProduct(String productSerialNumber) {
        ProductModel productModel = productMapper.getOneProduct(productSerialNumber);
        return convertToDto(productModel);
    }

    public int registerProduct(ProductDto productDto) {
        ProductModel productModel = productMapper.toModel(productDto);
        return productMapper.registerProduct(productModel);
    }

    public int updateProduct(ProductDto productDto) {
        ProductModel productModel = productMapper.toModel(productDto);
        return productMapper.updateProduct(productModel);
    }

    public int deleteProduct(String productSerialNumber) {
        return productMapper.deleteProduct(productSerialNumber);
    }


}
