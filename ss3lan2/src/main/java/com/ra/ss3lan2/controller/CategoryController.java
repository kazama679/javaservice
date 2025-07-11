package com.ra.ss3lan2.controller;

import com.ra.ss3lan2.model.entity.Category;
import com.ra.ss3lan2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category/list";
    }

    @GetMapping("/add")
    public String addCategories(Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("category") Category category) {
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            model.addAttribute("category", category);
            return "category/edit";
        }
        return "redirect:/categories";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("category") Category category) {
        if (categoryService.editCategory(category)) {
            return "redirect:/categories";
        }
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/categories";
    }
}
