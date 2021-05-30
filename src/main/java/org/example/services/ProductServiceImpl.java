package org.example.services;

import org.example.domain.CategoryEntity;
import org.example.domain.ProductEntity;
import org.example.dto.ProductDto;
import org.example.exeptions.ProductException;
import org.example.exeptions.ResultEnum;
import org.example.exeptions.UserException;
import org.example.repositories.CategoryRepository;
import org.example.repositories.ProductRepository;
import org.example.utils.BusinessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BusinessMapper businessMapper;


    @Override
    public ProductDto createNewProduct(ProductDto productDto) {
        ProductEntity productEntity = productRepository.findByName(productDto.getName());
        if (productEntity != null) {
            throw new ProductException(ResultEnum.PRODUCT_IS_ALREADY_EXIST.name());
        }

        productEntity = businessMapper.convertToProductEntity(productDto);
        productRepository.save(productEntity);

        return businessMapper.convertToProductDto(productEntity);
    }

    @Override
    public ProductDto updateProduct(Integer productId, ProductDto productDto) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ResultEnum.PRODUCT_NOT_FOUND.name()));


        if (productDto.getName() != null) {
            productEntity.setName(productDto.getName());
        }
        if (productDto.getDescription() != null) {
            productEntity.setDescription(productDto.getDescription());
        }
        if (productDto.getImage() != null) {
            productEntity.setImage(productDto.getImage());
        }
        if (productDto.getPrice() != null) {
            productEntity.setPrice(productDto.getPrice());
        }
        if (productDto.getQuantity() != null) {
            productEntity.setQuantity(productDto.getQuantity());
        }
        if(productDto.getCategoryDto()!= null){
            CategoryEntity oldCategory = categoryRepository.findByTitle(productEntity.getCategoryEntity().getTitle());
            CategoryEntity newCategory = categoryRepository.findByTitle(productDto.getCategoryDto().getTitle());
            productEntity.setCategoryEntity(newCategory);

            List<ProductEntity> categoryProducts = oldCategory.getProductEntities();
            categoryProducts.remove(productEntity);
            oldCategory.setProductEntities(categoryProducts);

            categoryProducts = newCategory.getProductEntities();
            categoryProducts.add(productEntity);
            newCategory.setProductEntities(categoryProducts);
            categoryRepository.save(newCategory);
        }
        productRepository.save(productEntity);
        return businessMapper.convertToProductDto(productEntity);
    }

    @Override
    public List<ProductDto> findAllProducts() {
        return businessMapper.convertCollectionToListGen(productRepository.findAll(), businessMapper.toProductDto);
    }

    @Override
    public ProductDto findProductById(Integer productId) {
        return businessMapper.convertToProductDto(productRepository.findById(productId).orElseThrow());
    }

    @Override
    public void deleteProduct(Integer productId) {
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow();
        CategoryEntity categoryEntity = categoryRepository.findByTitle(productEntity.getCategoryEntity().getTitle());
        categoryEntity.getProductEntities().remove(productEntity);
        categoryRepository.save(categoryEntity);
        productRepository.deleteById(productId);
    }

    @Override
    public ProductDto findProductByName(String productName) {
        return businessMapper.convertToProductDto(productRepository.findByName(productName));
    }
}
