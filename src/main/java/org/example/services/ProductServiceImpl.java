package org.example.services;

import org.example.domain.ProductEntity;
import org.example.dto.ProductDto;
import org.example.repositories.CategoryRepository;
import org.example.repositories.ProductRepository;
import org.example.utils.BusinessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BusinessMapper businessMapper;


    @Override
    public ProductDto createNewProduct(ProductDto productDto) {
        ProductEntity productEntity = productRepository.findByName(productDto.getName());
        if(productEntity != null){
            throw new RuntimeException("Product with such name is already exist");
        }
        productEntity = businessMapper.convertToProductEntity(productDto);
        productRepository.save(productEntity);

        return businessMapper.convertToProductDto(productEntity);
    }

    @Override
    public ProductDto updateProduct(Integer productId, ProductDto productDto) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product with such id not found"));

        if(productDto.getName() != null){
            productEntity.setName(productDto.getName());
        }
        if(productDto.getDescription() != null){
            productEntity.setDescription(productDto.getDescription());
        }
        if(productDto.getImage() != null){
            productEntity.setImage(productDto.getImage());
        }
        if(productDto.getPrice() != null){
            productEntity.setPrice(productDto.getPrice());
        }
        if(productDto.getQuantity() != null){
            productEntity.setQuantity(productDto.getQuantity());
        }
        if(productDto.getCategoryDto() != null){
            productEntity.setCategoryEntity(businessMapper.convertToCategoryEntity(productDto.getCategoryDto()));
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
       productRepository.deleteById(productId);
    }

    @Override
    public ProductDto findProductByName(String productName) {
        return businessMapper.convertToProductDto(productRepository.findByName(productName));
    }
}
