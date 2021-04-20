package org.example;

import org.example.domain.CategoryEntity;
import org.example.domain.ProductEntity;
import org.example.repositories.CategoryRepository;
import org.example.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
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
                    .image("test.jpg")
                    .description("Best product")
                    .name("Apple")
                    .price(78.56)
                    .quantity(23)
                    .categoryEntity(categoryEntity).build();
            productRepository.save(productEntity);
//            for (int i = 1; i < 11; i++) {
//                ProductEntity productEntity = ProductEntity.builder()
//                        .image("test"+i+".jpg")
//                        .description("Best product")
//                        .name("Apple-"+i)
//                        .price(i+.56)
//                        .quantity(23)
//                        .categoryEntity(new CategoryEntity()).build();
//                productRepository.save(productEntity);
//            }
        };
    }
}
