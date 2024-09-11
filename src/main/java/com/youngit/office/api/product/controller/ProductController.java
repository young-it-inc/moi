package com.youngit.office.api.product.controller;

import com.youngit.office.api.http.ApiResponse;
import com.youngit.office.api.product.dto.ProductDto;
import com.youngit.office.api.product.model.ProductModel;
import com.youngit.office.api.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Tag(name="제품 관리")
@RestController
@RequestMapping("/api")
public class ProductController {
    //제품 조회 (생산일/일련번호/설치협력업체/상태/품목분류/자재명/자재규격), 상태변경, 이력, 수정, 삭제, 검색, 제품등록, 선택삭제, 엑셀 다운로드

    private static final Logger logger = Logger.getLogger(ProductController.class.getName());

    @Autowired
    private ProductService productService;

    @Operation(summary = "제품 조회")
    @GetMapping("/product")
    public List<ProductDto> getListProduct() {
        logger.info("제품 조회");
        List<ProductDto> productDto = productService.getListProduct();
        return productDto;
    }

    @Operation(summary = "제품 등록", description = "필수입력: ")
    @PostMapping("/product")
    public ApiResponse<String> registerProduct(@RequestBody ProductDto productDto) {
        logger.info("제품 등록");
        int result = productService.registerProduct(productDto);
        return new ApiResponse<>("제품 등록 성공");
    }

    @Operation(summary = "제품 수정", description = "필수입력: ")
    @PutMapping("/product")
    public ApiResponse<String> updateProduct(@RequestBody ProductDto productDto) {
        logger.info("제품 수정");
        int result = productService.updateProduct(productDto);
        return new ApiResponse<>("제품 수정 성공");
    }

    @Operation(summary = "제품 삭제")
    @DeleteMapping("/product")
    public ApiResponse<String> deleteProduct(@RequestBody String productId) {
        logger.info("제품 삭제");
        int result = productService.deleteProduct(productId);
        if(result == 1) {
            return new ApiResponse<>("제품 삭제 성공");
        } else {
            return new ApiResponse<>("제품 삭제 실패");
        }
    }

}
