package com.ra.ss10.service;

import com.ra.ss10.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<Product> getProducts(Integer page, Integer itemPage, String sortBy, Boolean orderBy);
    Page<Product> getProductsByNameWitchPagingAndSorting(String proName, Integer page, Integer itemPage, String sortBy, Boolean orderBy);
    List<Product> insertListProducts(List<Product> list);
}