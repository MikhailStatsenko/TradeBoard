package tradeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tradeboard.utils.ProductCategory;

@Controller
public class HomeController {
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("categories", ProductCategory.values());
        return "page elements/header";
    }

}
