package com.ra.ss7.service;

import com.ra.ss7.model.entity.ProductDetail;
import com.ra.ss7.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;

    public List<ProductDetail> getAllProductDetails() {
        return productDetailRepository.findAll();
    }

    public ProductDetail getProductDetailById(Long id) {
        return productDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiết sản phẩm với ID = " + id));
    }

    public ProductDetail createProductDetail(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    public ProductDetail updateProductDetail(Long id, ProductDetail productDetail) {
        return productDetailRepository.findById(id).map(pd -> {
            pd.setProduct_detail_id(productDetail.getProduct_detail_id());
            pd.setColor(productDetail.getColor());
            pd.setSize(productDetail.getSize());
            pd.setPrice(productDetail.getPrice());
            pd.setYear_making(productDetail.getYear_making());
            pd.setProduct(productDetail.getProduct());
            return productDetailRepository.save(pd);
        }).orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiết sản phẩm với ID = " + id));
    }

    public void deleteProductDetail(Long id) {
        productDetailRepository.deleteById(id);
    }
}
