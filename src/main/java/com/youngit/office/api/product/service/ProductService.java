package com.youngit.office.api.product.service;

import com.youngit.office.api.product.dto.ProductDto;
import com.youngit.office.api.product.mapper.ProductMapper;
import com.youngit.office.api.product.mapstruct.ProductMapstruct;
import com.youngit.office.api.product.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductMapstruct productMapstruct;

    @Autowired
    public ProductService(ProductMapper productMapper, ProductMapstruct productMapstruct) {
        this.productMapper = productMapper;
        this.productMapstruct = productMapstruct;
    }


    public List<ProductDto> getListProduct() {
        List<ProductModel> productModelList = productMapper.getListProduct();
        return productModelList.stream().map(productMapstruct::toDto).toList();
    }
    public int getCountListProduct()
    {
        return productMapper.getCountListProduct();
    }

    public ProductDto getOneProduct(String productSerialNumber) {
        ProductModel productModel = productMapper.getOneProduct(productSerialNumber);
        return productMapstruct.toDto(productModel);
    }

    public int registerProduct(ProductDto productDto) {
        ProductModel productModel = productMapstruct.toModel(productDto);
        return productMapper.registerProduct(productModel);
    }

    public int updateProduct(ProductDto productDto) {
        ProductModel productModel = productMapstruct.toModel(productDto);
        return productMapper.updateProduct(productModel);
    }

    public int deleteProduct(String productSerialNumber) {
        return productMapper.deleteProduct(productSerialNumber);
    }

}
