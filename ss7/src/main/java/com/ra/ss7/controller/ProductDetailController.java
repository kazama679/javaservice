package com.ra.ss7.controller;

import com.ra.ss7.model.dto.DataResponse;
import com.ra.ss7.model.entity.ProductDetail;
import com.ra.ss7.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product-details")
public class ProductDetailController {
    @Autowired
    private final ProductDetailService productDetailService;

    @GetMapping
    public ResponseEntity<DataResponse<List<ProductDetail>>> getAllProductDetails(){
        return ResponseEntity.ok(new DataResponse<>(productDetailService.getAllProductDetails(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<ProductDetail>> addProductDetails(@RequestBody ProductDetail product){
        return ResponseEntity.ok(new DataResponse<>(productDetailService.createProductDetail(product), HttpStatus.CREATED));
    }

    @PutMapping
    public ResponseEntity<DataResponse<ProductDetail>> updateProductDetail(@RequestBody ProductDetail product){
        return ResponseEntity.ok(new DataResponse<>(productDetailService.updateProductDetail(product.getProduct_detail_id(), product), HttpStatus.OK));
    }

    @GetMapping("{id}")
    public ResponseEntity<DataResponse<ProductDetail>> getProductDetailById(Long id){
        return ResponseEntity.ok(new DataResponse<>(productDetailService.getProductDetailById(id), HttpStatus.OK));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DataResponse<Void>> deleteProductDetailById(@PathVariable Long id){
        productDetailService.deleteProductDetail(id);
        return ResponseEntity.ok(new DataResponse<>(null, HttpStatus.NO_CONTENT));
    }
}
