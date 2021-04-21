package org.example.api;

import org.example.domain.ProductEntity;
import org.example.dto.CategoryDto;
import org.example.dto.ProductDto;
import org.example.exeptions.ProductException;
import org.example.services.CategoryService;
import org.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/allProducts")
    public ModelAndView allProductsPage() {
        ModelAndView modelAndView = new ModelAndView("allProducts");
        modelAndView.addObject("products", productService.findAllProducts());
        return modelAndView;
    }

    @GetMapping("/dashboard")
    public ModelAndView getProductDashboard() {
        ModelAndView modelAndView = new ModelAndView("productDashboard");
        modelAndView.addObject("product", new ProductDto());
        modelAndView.addObject("products", productService.findAllProducts());
        modelAndView.addObject("categories", categoryService.findAllCategories());
        modelAndView.addObject("category", new CategoryDto());
        return modelAndView;
    }

    @GetMapping("/deleteProduct/{productId}")
    public ModelAndView deleteProduct(@PathVariable("productId") String productId) {
        productService.deleteProduct(Integer.parseInt(productId));
        ModelAndView modelAndView = new ModelAndView("productDashboard");
        modelAndView.addObject("product", new ProductDto());
        modelAndView.addObject("products", productService.findAllProducts());
        modelAndView.addObject("categories", categoryService.findAllCategories());
        modelAndView.addObject("category", new CategoryDto());
        return modelAndView;
    }


    @PostMapping("/product/create")
    public ModelAndView createNewProduct(@ModelAttribute("product") ProductDto productDto) {
        System.out.println(productDto);
        productService.createNewProduct(productDto);
        ModelAndView modelAndView = new ModelAndView("productDashboard");
        modelAndView.addObject("product", new ProductDto());
        modelAndView.addObject("products", productService.findAllProducts());
        modelAndView.addObject("categories", categoryService.findAllCategories());
        modelAndView.addObject("category", new CategoryDto());
        return modelAndView;
    }

    @ExceptionHandler({ProductException.class})
    public ModelAndView productExceptionsHandler(ProductException ex) {
        ModelAndView modelAndView = new ModelAndView("productDashboard");
        modelAndView.addObject("product", new ProductDto());
        modelAndView.addObject("products", productService.findAllProducts());
        modelAndView.addObject("categories", categoryService.findAllCategories());
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.addObject("status", "alert-danger");
        modelAndView.addObject("category", new CategoryDto());
        return modelAndView;
    }

    @GetMapping("/selectProduct/{productId}")
    public ModelAndView selectProductForUpdate(@PathVariable("productId") String productId) {
        ModelAndView modelAndView = new ModelAndView("productDashboard");
        modelAndView.addObject("product", productService.findProductById(Integer.parseInt(productId)));
        modelAndView.addObject("products", productService.findAllProducts());
        modelAndView.addObject("categories", categoryService.findAllCategories());
        modelAndView.addObject("category", new CategoryDto());
        return modelAndView;
    }

    @PostMapping("/product/updateProduct/{productId}")
    public ModelAndView selectProductForUpdate(@ModelAttribute ("product") ProductDto productDto, @PathVariable("productId") String productId) {
        System.out.println(productDto.toString());
        ProductDto product = productService.updateProduct(Integer.parseInt(productId), productDto);
        ModelAndView modelAndView = new ModelAndView("productDashboard");
        modelAndView.addObject("product", new ProductDto());
        modelAndView.addObject("products", productService.findAllProducts());
        modelAndView.addObject("categories", categoryService.findAllCategories());
        modelAndView.addObject("message", "Product with id "+product.getId() +" successfully updated");
        modelAndView.addObject("status", "alert-success");
        modelAndView.addObject("category", new CategoryDto());
        return modelAndView;
    }
}
