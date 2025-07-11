package com.ra.ss3lan2.controller;

import com.ra.ss3lan2.model.entity.Category;
import com.ra.ss3lan2.model.entity.Product;
import com.ra.ss3lan2.service.CategoryService;
import com.ra.ss3lan2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product/list";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryService.getAllCategories());
        return "product/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }
}
