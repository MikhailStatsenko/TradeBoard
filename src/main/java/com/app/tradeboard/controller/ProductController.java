package com.app.tradeboard.controller;

import com.app.tradeboard.model.Product;
import com.app.tradeboard.service.ProductService;
import com.app.tradeboard.utils.Enums.ProductCategory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public String fullInfoProductPage(@PathVariable long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product/product-info";
    }

    @GetMapping("/new")
    public String addProductPage(Model model) {
        model.addAttribute("categories", ProductCategory.values());
        return "product/new-product";
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
