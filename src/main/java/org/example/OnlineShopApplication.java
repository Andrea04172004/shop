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
import java.util.List;


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
            CategoryEntity categoryEntity1 = CategoryEntity.builder()
                    .title("Goods1")
                    .productEntities(new ArrayList<>()).build();
            categoryRepository.save(categoryEntity1);
            CategoryEntity categoryEntity2 = CategoryEntity.builder()
                    .title("Goods2")
                    .productEntities(new ArrayList<>()).build();
            categoryRepository.save(categoryEntity2);
            CategoryEntity categoryEntity3 = CategoryEntity.builder()
                    .title("Goods3")
                    .productEntities(new ArrayList<>()).build();
            categoryRepository.save(categoryEntity3);


            ProductEntity productEntity = ProductEntity.builder()
                    .image("https://ilounge.ua/files/products/iphone-13-8.1000x.jpg")
                    .description("Best product")
                    .name("Apple")
                    .price(Double.parseDouble(String.valueOf(Math.round(65.56))))
                    .quantity(23)
                    .categoryEntity(categoryEntity).build();
            productRepository.save(productEntity);

            List<ProductEntity> products = new ArrayList<>();
            for (int i = 1; i < 11; i++) {
                ProductEntity productEntity1 = ProductEntity.builder()
                        .image("https://ilounge.ua/files/products/iphone-13-8.1000x.jpg")
                        .description("Best product")
                        .name("Apple-"+i)
                        .price(Double.parseDouble(String.valueOf(Math.round(i+.56))))
                        .quantity(23)
                        .categoryEntity(categoryEntity).build();
                productRepository.save(productEntity1);
                products.add(productEntity1);
            }

            categoryEntity.setProductEntities(products);
            categoryRepository.save(categoryEntity);
        };
    }
}

