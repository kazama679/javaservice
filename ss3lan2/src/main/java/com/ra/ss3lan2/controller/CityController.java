package com.ra.ss3lan2.controller;

import com.ra.ss3lan2.model.entity.City;
import com.ra.ss3lan2.service.CityService;
import com.ra.ss3lan2.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @GetMapping
    public String list(@RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size,
                       Model model) {

        Pageable pageable = PageRequest.of(page, size);
        List<City> cities = keyword.isEmpty()
                ? cityService.getAll()
                : cityService.searchByName(keyword);

        model.addAttribute("cities", cities);
        model.addAttribute("keyword", keyword);
        return "city/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("city", new City());
        model.addAttribute("countries", countryService.findAll());
        return "city/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute City city) {
        cityService.save(city);
        return "redirect:/cities";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        City city = cityService.findById(id);
        if (city != null) {
            model.addAttribute("city", city);
            model.addAttribute("countries", countryService.findAll());
            return "city/form";
        } else {
            return "redirect:/cities";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        cityService.delete(id);
        return "redirect:/cities";
    }
}