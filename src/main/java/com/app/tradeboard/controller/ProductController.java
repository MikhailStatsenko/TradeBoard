package com.app.tradeboard.controller;

import com.app.tradeboard.model.Product;
import com.app.tradeboard.service.ProductService;
import com.app.tradeboard.utils.Enums.ProductCategory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/new")
    public String addProductPage(Model model) {
        model.addAttribute("categories", ProductCategory.values());
        return "new-product";
    }

    @PostMapping("/new")
    public String addProduct(@ModelAttribute Product product,
                             @RequestParam long userId,
                             @RequestParam("images_raw") MultipartFile[] images,
                             HttpServletRequest request) throws IOException {
        productService.addProduct(product, userId, request, images);
        return "redirect:/";
    }
}
