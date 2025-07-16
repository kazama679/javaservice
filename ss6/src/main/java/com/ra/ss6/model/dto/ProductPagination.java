package com.ra.ss6.model.dto;

import com.ra.ss6.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ProductPagination {
    private List<Product> products;
    private int totalPage;
    private int pageSize;
    private int currentPage;
}
