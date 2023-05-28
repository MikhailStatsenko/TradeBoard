package com.app.tradeboard.controller;

import com.app.tradeboard.service.ProductService;
import com.app.tradeboard.utils.Enums.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private final ProductService productService;

    @GetMapping("/")
    public String homePage(@RequestParam(required = false) ProductCategory category, Model model) {
        model.addAttribute("products",
                category == null || category.name().isEmpty() ? productService.findAll() :
                productService.findByCategory(category));
        return "index";
    }
}
