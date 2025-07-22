package com.ra.ss10.controller;

import com.ra.ss10.model.dto.request.ProductPagingDTO;
import com.ra.ss10.model.dto.response.APIResponse;
import com.ra.ss10.model.entity.Product;
import com.ra.ss10.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<APIResponse<Page<Product>>> getProducts(@RequestBody ProductPagingDTO productPagingDTO){
        return new ResponseEntity<>(new APIResponse(true,"Get all products successfully!",productService.getProducts(productPagingDTO.getPage(),productPagingDTO.getItemPage(),productPagingDTO.getSortBy(),productPagingDTO.getOrderBy()),HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/products-by-name-and-paging")
    public ResponseEntity<APIResponse<Page<Product>>> getProductsByNameWitchPaging(@RequestBody ProductPagingDTO productPagingDTO){
        return new ResponseEntity<>(new APIResponse<>(true,"Get products by name and paging successfully!",productService.getProductsByNameWitchPagingAndSorting(productPagingDTO.getProName(),productPagingDTO.getPage(),productPagingDTO.getItemPage(),productPagingDTO.getSortBy(),productPagingDTO.getOrderBy()),HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping("/gen-data")
    public ResponseEntity<?> postDataFake(){
        Random r = new Random();
        List<Product> list = new ArrayList<>();
        for(int i=0;i<60;i++){
            Product p = new  Product();
            p.setProName("product - "+i);
            p.setProducer("producer - "+i);
            p.setPrice(r.nextDouble(200000000));
            list.add(p);
        }
        productService.insertListProducts(list);
        return new ResponseEntity<>("Insert List Ok!",HttpStatus.OK);
    }
}
