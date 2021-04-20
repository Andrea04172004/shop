package org.example;

import org.example.domain.CategoryEntity;
import org.example.domain.ProductEntity;
import org.example.repositories.CategoryRepository;
import org.example.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;


@SpringBootApplication (exclude = {ErrorMvcAutoConfiguration.class})
public class OnlineShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineShopApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ProductRepository productRepository, CategoryRepository categoryRepository) {
        return args -> {
            CategoryEntity categoryEntity = CategoryEntity.builder()
                    .title("Goods")
                    .productEntities(new ArrayList<>()).build();
            categoryRepository.save(categoryEntity);

            ProductEntity productEntity = ProductEntity.builder()
                    .image("https://ilounge.ua/files/products/iphone-13-8.1000x.jpg")
                    .description("Best product")
                    .name("Apple")
                    .price(Double.parseDouble(String.valueOf(Math.round(65.56))))
                    .quantity(23)
                    .categoryEntity(categoryEntity).build();
            productRepository.save(productEntity);
            for (int i = 1; i < 11; i++) {
                ProductEntity productEntity1 = ProductEntity.builder()
                        .image("https://ilounge.ua/files/products/iphone-13-8.1000x.jpg")
                        .description("Best product")
                        .name("Apple-"+i)
                        .price(Double.parseDouble(String.valueOf(Math.round(i+.56))))
                        .quantity(23)
                        .categoryEntity(categoryEntity).build();
                productRepository.save(productEntity1);
            }
        };
    }
}
