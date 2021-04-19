package org.example.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShopController {
    @GetMapping
    public ModelAndView mainPage (){
        return new ModelAndView("index");
    }

    @GetMapping ("/allProducts")
    public ModelAndView allProductsPage (){
        return new ModelAndView("allProducts");
    }
}
