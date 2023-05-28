package com.app.tradeboard.controller;

import com.app.tradeboard.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private final ProductService productService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("products", productService.findAll());
        return "index";
    }
}
