package org.example.api;

import org.example.dto.CategoryDto;
import org.example.dto.ProductDto;
import org.example.services.CategoryService;
import org.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @PostMapping("/category/create")
    public String createNewProduct(@ModelAttribute("category") CategoryDto categoryDto) {
        System.out.println(categoryDto);
        categoryService.createNewCategory(categoryDto);
        return "redirect:/dashboard";
    }

    @GetMapping ("/allCategories")
    public ModelAndView getAllCategories (){
        ModelAndView modelAndView = new ModelAndView("allCategories");
        modelAndView.addObject("categories", categoryService.findAllCategories());
        return modelAndView;
    }
}
