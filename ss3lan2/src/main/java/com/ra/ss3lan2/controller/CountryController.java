package com.ra.ss3lan2.controller;

import com.ra.ss3lan2.model.entity.Country;
import com.ra.ss3lan2.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping
    public String listCountries(Model model, @RequestParam(defaultValue = "") String keyword) {
        model.addAttribute("countries", keyword.isEmpty() ? countryService.findAll() : countryService.search(keyword));
        model.addAttribute("keyword", keyword);
        return "country/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("country", new Country());
        return "country/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Country country) {
        countryService.save(country);
        return "redirect:/countries";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("country", countryService.findById(id));
        return "country/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        countryService.delete(id);
        return "redirect:/countries";
    }
}