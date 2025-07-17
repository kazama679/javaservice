package com.ra.ss7.controller;

import com.ra.ss7.model.dto.DataResponse;
import com.ra.ss7.model.entity.Product;
import com.ra.ss7.model.entity.User;
import com.ra.ss7.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Product>>> getAllProducts(){
        return ResponseEntity.ok(new DataResponse<>(productService.getAll(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Product>> addProducts(@RequestBody Product product){
        return ResponseEntity.ok(new DataResponse<>(productService.add(product), HttpStatus.CREATED));
    }

    @PutMapping
    public ResponseEntity<DataResponse<Product>> updateProduct(@RequestBody Product product){
        return ResponseEntity.ok(new DataResponse<>(productService.updateProduct(product.getProduct_id(), product), HttpStatus.OK));
    }

    @GetMapping("{id}")
    public ResponseEntity<DataResponse<Product>> getProductById(Long id){
        return ResponseEntity.ok(new DataResponse<>(productService.findById(id), HttpStatus.OK));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DataResponse<Void>> deleteProductById(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok(new DataResponse<>(null, HttpStatus.NO_CONTENT));
    }
}