package com.app.tradeboard.controller;

import com.app.tradeboard.model.Product;
import com.app.tradeboard.model.User;
import com.app.tradeboard.service.ProductService;
import com.app.tradeboard.service.UserService;
import com.app.tradeboard.utils.Enums.ProductCategory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/product")
public class ProductController {
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/{id}")
    public String fullInfoProductPage(@PathVariable long id, Model model) {
        Product product = productService.findById(id);
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
        productService.add(product, userId, request, images);
        return "redirect:/";
    }

    @PostMapping("/add-to-favorites/{productId}")
    public String addToFavorites(@PathVariable long productId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Product product = productService.findById(productId);

        if (product != null) {
            user.addFavorite(product);
            userService.saveUser(user);
        }
        return "redirect:/product/" + productId;
    }

    @PostMapping("/remove-from-favorites")
    @ResponseBody
    public ResponseEntity<String> removeFromFavorites(@RequestParam("productId") long productId , Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Product product = productService.findById(productId);

        if (user != null && product != null) {
            if (user.getFavorites().contains(product)) {
                user.removeFavorite(product);
                userService.saveUser(user);
                return ResponseEntity.ok("Product removed from favorites");
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
