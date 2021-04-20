package org.example.api;

import org.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/allProducts")
    public ModelAndView allProductsPage (){
       ModelAndView modelAndView = new ModelAndView("allProducts");
       modelAndView.addObject("products", productService.findAllProducts());
       return modelAndView;
    }
}
