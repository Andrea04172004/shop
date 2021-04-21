package org.example.api;

import org.example.dto.CategoryDto;
import org.example.dto.ProductDto;
import org.example.services.CategoryService;
import org.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @PostMapping("/category/create")
    public ModelAndView createNewProduct(@ModelAttribute("category") CategoryDto categoryDto) {
        System.out.println(categoryDto);
        categoryService.createNewCategory(categoryDto);
        ModelAndView modelAndView = new ModelAndView("productDashboard");
        modelAndView.addObject("category", new CategoryDto());
        modelAndView.addObject("product", new ProductDto());
        modelAndView.addObject("products", productService.findAllProducts());
        modelAndView.addObject("categories", categoryService.findAllCategories());
        return modelAndView;
    }
}
