package com.ra.ss10.service.impl;

import com.ra.ss10.model.entity.Product;
import com.ra.ss10.repo.ProductRepo;
import com.ra.ss10.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepository;

    @Override
    public Page<Product> getProducts(Integer page, Integer itemPage, String sortBy, Boolean orderBy) {
        Pageable  pageable = null;
        Sort sort = null;
        if(sortBy!=null && !sortBy.isEmpty()){
            if(orderBy){
                sort = Sort.by(Sort.Direction.ASC,sortBy);
            }else{
                sort = Sort.by(Sort.Direction.DESC,sortBy);
            }
            pageable = PageRequest.of(page,itemPage,sort);
        }else{
            pageable = PageRequest.of(page,itemPage);
        }
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> getProductsByNameWitchPagingAndSorting(String proName, Integer page, Integer itemPage, String sortBy, Boolean orderBy) {
        Pageable  pageable = null;
        Sort sort = null;
        if(sortBy!=null && !sortBy.isEmpty()){
            if(orderBy){
                sort = Sort.by(Sort.Direction.ASC,sortBy);
            }else{
                sort = Sort.by(Sort.Direction.DESC,sortBy);
            }
            pageable = PageRequest.of(page,itemPage,sort);
        }else{
            pageable = PageRequest.of(page,itemPage);
        }
        return productRepository.findProductsByProNameContaining(proName,pageable);
    }

    @Override
    public List<Product> insertListProducts(List<Product> list) {
        return productRepository.saveAll(list);
    }
}
