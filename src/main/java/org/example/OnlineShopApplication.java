package org.example;

import org.example.domain.CategoryEntity;
import org.example.domain.LineItemEntity;
import org.example.domain.ProductEntity;
import org.example.domain.ShoppingCartEntity;
import org.example.domain.user.RoleEntity;
import org.example.domain.user.UserEntity;
import org.example.repositories.CategoryRepository;
import org.example.repositories.LineItemRepository;
import org.example.repositories.ProductRepository;
import org.example.repositories.ShoppingCartRepository;
import org.example.repositories.user.RoleRepository;
import org.example.repositories.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

import static org.example.security.SecurityConstants.EXPIRATION_TIME;


@SpringBootApplication
public class OnlineShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineShopApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    CommandLineRunner init(ProductRepository productRepository, CategoryRepository categoryRepository,
                           ShoppingCartRepository shoppingCartRepository, LineItemRepository lineItemRepository, RoleRepository roleRepository, UserRepository userRepository) {
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
                        .name("Apple-" + i)
                        .price(Double.parseDouble(String.valueOf(Math.round(i + .56))))
                        .quantity(23)
                        .categoryEntity(categoryEntity).build();
                productRepository.save(productEntity1);
                products.add(productEntity1);
            }

            List<LineItemEntity> lineItemEntities = new LinkedList<>();
//            for (int i = 1; i < 5; i++) {
            LineItemEntity lineItemEntity = LineItemEntity.builder()
                    .product(productEntity)
                    .quantity(2).build();
            lineItemRepository.save(lineItemEntity);
            lineItemEntities.add(lineItemEntity);
//            }
            ShoppingCartEntity shoppingCartEntity = ShoppingCartEntity.builder()
                    .id(1)
                    .lineItemEntities(lineItemEntities).build();
            shoppingCartRepository.save(shoppingCartEntity);
            categoryEntity.setProductEntities(products);
            categoryRepository.save(categoryEntity);



            RoleEntity userRole = roleRepository.findByRole("USER");
            if (userRole == null) {
                userRole = new RoleEntity();
                userRole.setRole("USER");
                roleRepository.save(userRole);
            }


            Set<RoleEntity> roleEntities = new HashSet<>();
            roleEntities.add(userRole);
            UserEntity user = userRepository.findByEmail("admin.agencya@gmail.com");
            if (user == null) {
                user = UserEntity.builder()
                        .email("admin.agencya@gmail.com")
                        .password("$2a$10$7PtcjEnWb/ZkgyXyxY1/Iei2dGgGQUbqIIll/dt.qJ8l8nQBWMbYO") // "123456"
                        .firstName("John")
                        .lastName("Doe")
                        .mobileNumber("9425094250")
                        .shoppingCartEntity(shoppingCartEntity)
                        .roles(roleEntities).build();
                userRepository.save(user);
            }



            RoleEntity adminRole = roleRepository.findByRole("ADMIN");
            if (adminRole == null) {
                adminRole = new RoleEntity();
                adminRole.setRole("ADMIN");
                roleRepository.save(adminRole);
            }

            ShoppingCartEntity shoppingCartEntity1 = ShoppingCartEntity.builder()
                    .id(2)
                    .lineItemEntities(new LinkedList<>()).build();
            shoppingCartRepository.save(shoppingCartEntity1);


            Set<RoleEntity> roleEntities1 = new HashSet<>();
            roleEntities1.add(adminRole);
            UserEntity admin = userRepository.findByEmail("andrew@mail.com");
            if (admin == null) {
                admin = UserEntity.builder()
                        .email("andrew@mail.com")
                        .password("$2a$10$7PtcjEnWb/ZkgyXyxY1/Iei2dGgGQUbqIIll/dt.qJ8l8nQBWMbYO") // "123456"
                        .firstName("Andrew")
                        .lastName("Dev")
                        .mobileNumber("1111111111111")
                        .shoppingCartEntity(shoppingCartEntity1)
                        .roles(roleEntities1).build();
                userRepository.save(admin);
            }
        };
    }
}

