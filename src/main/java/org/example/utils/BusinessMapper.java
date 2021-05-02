package org.example.utils;

import org.example.domain.CategoryEntity;
import org.example.domain.LineItemEntity;
import org.example.domain.ProductEntity;
import org.example.domain.ShoppingCartEntity;
import org.example.domain.user.RoleEntity;
import org.example.domain.user.UserEntity;
import org.example.dto.CategoryDto;
import org.example.dto.LineItemDto;
import org.example.dto.ProductDto;
import org.example.dto.ShoppingCartDto;
import org.example.dto.user.RoleDto;
import org.example.dto.user.UserDto;
import org.example.dto.user.UserSignupRequest;
import org.example.repositories.CategoryRepository;
import org.example.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BusinessMapper {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Function<ProductEntity, ProductDto> toProductDto = this::convertToProductDto;
    public Function<ProductDto, ProductEntity> toProductEntity = this::convertToProductEntity;
    public Function<CategoryEntity, CategoryDto> toCategoryDto = this::convertToCategoryDto;
    public Function<CategoryDto, CategoryEntity> toCategoryEntity = this::convertToCategoryEntity;
    public Function<LineItemEntity, LineItemDto> toLineItemDto = this::convertToLineItemDto;
    public Function<LineItemDto, LineItemEntity> toLineItemEntity = this::convertToLineItemEntity;
    public Function<ShoppingCartEntity, ShoppingCartDto> toCartDto = this::convertToCartDto;
    public Function<ShoppingCartDto, ShoppingCartEntity> toCartEntity = this::convertToCartEntity;

    public Function<UserEntity, UserDto> toUserDto = this::convertToUserDto;
    public Function<UserDto, UserEntity> toUserEntity = this::convertToUserEntity;
    public Function<RoleEntity, RoleDto> toRoleDto = this::convertToRoleDto;
    public Function<RoleDto, RoleEntity> toRoleEntity = this::convertToRoleEntity;


    public <T, R> Set<R> convertCollectionToSetGen(Collection<T> collection, Function<T, R> mapper) {
        return collection.stream().map(mapper).collect(Collectors.toSet());
    }

    public <T, R> List<R> convertCollectionToListGen(Collection<T> collection, Function<T, R> mapper) {
        return collection.stream().map(mapper).collect(Collectors.toList());
    }

    public ProductDto convertToProductDto(ProductEntity productEntity) {
        return ProductDto.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .image(productEntity.getImage())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .categoryDto(convertToCategoryDto(productEntity.getCategoryEntity())).build();
    }

    public ProductEntity convertToProductEntity(ProductDto productDto) {
        CategoryEntity categoryEntity = categoryRepository.findByTitle(productDto.getCategoryDto().getTitle());

        return ProductEntity.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .image(productDto.getImage())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .categoryEntity(categoryEntity).build();
    }

    public CategoryEntity convertToCategoryEntity(CategoryDto categoryDto) {
        return CategoryEntity.builder()
                .title(categoryDto.getTitle()).build();
//                .productEntities(convertCollectionToListGen(categoryDto.getProductDto(), toProductEntity))
    }

    public CategoryDto convertToCategoryDto(CategoryEntity categoryEntity) {
        return CategoryDto.builder()
                .title(categoryEntity.getTitle()).build();
//                .productDto(convertCollectionToListGen(categoryEntity.getProductEntities(), toProductDto)).build();
    }

    public LineItemDto convertToLineItemDto(LineItemEntity lineItemEntity) {
        return LineItemDto.builder()
                .id(lineItemEntity.getId())
                .quantity(lineItemEntity.getQuantity())
                .lineSum((double) Math.round(lineItemEntity.getQuantity() * lineItemEntity.getProduct().getPrice()))
                .product(convertToProductDto(lineItemEntity.getProduct())).build();
    }

    public LineItemEntity convertToLineItemEntity(LineItemDto lineItemDto) {
        return LineItemEntity.builder()
                .quantity(lineItemDto.getQuantity())
                .product(convertToProductEntity(lineItemDto.getProduct())).build();
    }

    public ShoppingCartDto convertToCartDto(ShoppingCartEntity shoppingCartEntity) {
        return ShoppingCartDto.builder()
                .id(shoppingCartEntity.getId())
                .lineItemDto(convertCollectionToListGen(shoppingCartEntity.getLineItemEntities(), toLineItemDto)).build();
    }

    public ShoppingCartEntity convertToCartEntity(ShoppingCartDto shoppingCartDto) {
        return ShoppingCartEntity.builder()
                .lineItemEntities(convertCollectionToListGen(shoppingCartDto.getLineItemDto(), toLineItemEntity)).build();
    }

    public RoleEntity convertToRoleEntity(RoleDto roleDto) {
        return RoleEntity.builder()
                .role(roleDto.getRole()).build();
    }

    public RoleDto convertToRoleDto(RoleEntity roleEntity) {
        return RoleDto.builder()
                .role(roleEntity.getRole()).build();
    }

    public UserEntity convertToUserEntity(UserDto userDto) {
        return UserEntity.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .mobileNumber(userDto.getMobileNumber())
                .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .roles(convertCollectionToSetGen(userDto.getRoles(), toRoleEntity))
                .shoppingCartEntity(convertToCartEntity(userDto.getShoppingCartDto())).build();
    }

    public UserDto convertToUserDto(UserEntity userEntity) {
        return UserDto.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .mobileNumber(userEntity.getMobileNumber())
                .password(userEntity.getPassword())
                .roles(convertCollectionToSetGen(userEntity.getRoles(), toRoleDto))
                .shoppingCartDto(convertToCartDto(userEntity.getShoppingCartEntity())).build();
    }

    public UserDto getUserDto(UserSignupRequest userSignupRequest) {
        return UserDto.builder()
                .email(userSignupRequest.getEmail())
                .firstName(userSignupRequest.getFirstName())
                .lastName(userSignupRequest.getLastName())
                .password(userSignupRequest.getPassword()).build();
    }
}
