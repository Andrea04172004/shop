package org.example.utils;

import org.example.domain.CategoryEntity;
import org.example.domain.ProductEntity;
import org.example.dto.CategoryDto;
import org.example.dto.ProductDto;
import org.example.repositories.CategoryRepository;
import org.example.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Function<ProductEntity, ProductDto> toProductDto = this::convertToProductDto;
    public Function<ProductDto, ProductEntity> toProductEntity = this::convertToProductEntity;
    public Function<CategoryEntity, CategoryDto> toCategoryDto = this::convertToCategoryDto;
    public Function<CategoryDto, CategoryEntity> toCategoryEntity = this::convertToCategoryEntity;



    public <T, R> Set<R> convertCollectionToSetGen(Collection<T> collection, Function<T, R> mapper) {
        return collection.stream().map(mapper).collect(Collectors.toSet());
    }

    public <T, R> List<R> convertCollectionToListGen(Collection<T> collection, Function<T, R> mapper) {
        return collection.stream().map(mapper).collect(Collectors.toList());
    }

    public ProductDto convertToProductDto (ProductEntity productEntity){
        return ProductDto.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .image(productEntity.getImage())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .categoryDto(convertToCategoryDto(productEntity.getCategoryEntity())).build();
    }

    public ProductEntity convertToProductEntity (ProductDto productDto){
        CategoryEntity categoryEntity = categoryRepository.findByTitle(productDto.getCategoryDto().getTitle());

        return ProductEntity.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .image(productDto.getImage())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .categoryEntity(categoryEntity).build();
    }

    public CategoryEntity convertToCategoryEntity (CategoryDto categoryDto){
        return CategoryEntity.builder()
                .title(categoryDto.getTitle()).build();
//                .productEntities(convertCollectionToListGen(categoryDto.getProductDto(), toProductEntity))
    }

    public CategoryDto convertToCategoryDto (CategoryEntity categoryEntity){
        return CategoryDto.builder()
                .title(categoryEntity.getTitle()).build();
//                .productDto(convertCollectionToListGen(categoryEntity.getProductEntities(), toProductDto)).build();
    }
}
