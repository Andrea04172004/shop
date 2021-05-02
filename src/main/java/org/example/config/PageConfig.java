package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PageConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/allProducts.html").setViewName("allProducts");
        registry.addViewController("/productDashboard.html").setViewName("productDashboard");
        registry.addViewController("/shoppingCart.html").setViewName("shoppingCart");
        registry.addViewController("/login.html").setViewName("login");
    }
}
