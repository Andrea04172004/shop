package org.example.services;

import org.example.domain.CategoryEntity;
import org.example.domain.ProductEntity;
import org.example.dto.CategoryDto;
import org.example.repositories.CategoryRepository;
import org.example.repositories.ProductRepository;
import org.example.utils.BusinessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public CategoryDto createNewCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = categoryRepository.findByTitle(categoryDto.getTitle());
        if(categoryEntity != null){
            throw new RuntimeException("Category with such title is already exist");
        }
        categoryEntity = businessMapper.convertToCategoryEntity(categoryDto);
        categoryRepository.save(categoryEntity);

        return businessMapper.convertToCategoryDto(categoryEntity);
    }

    @Override
    public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Product with such id not found"));

        if(categoryEntity.getTitle() != null){
            categoryEntity.setTitle(categoryDto.getTitle());
        }
        if(categoryEntity.getProductEntities() != null){
            categoryEntity.setProductEntities(businessMapper.convertCollectionToListGen(categoryDto.getProductDto(), businessMapper.toProductEntity));
        }
        categoryRepository.save(categoryEntity);
        return businessMapper.convertToCategoryDto(categoryEntity);
    }

    @Override
    public List<CategoryDto> findAllCategories() {
        return businessMapper.convertCollectionToListGen(categoryRepository.findAll(), businessMapper.toCategoryDto);
    }

    @Override
    public CategoryDto findCategoryById(Integer categoryId) {
        return businessMapper.convertToCategoryDto(categoryRepository.findById(categoryId).orElseThrow());
    }

    @Override
    public CategoryDto findCategoryByTitle(String categoryTitle) {
        return  businessMapper.convertToCategoryDto(categoryRepository.findByTitle(categoryTitle));
    }

    @Override
    public void deleteCategory(Integer categoryId) {
       categoryRepository.deleteById(categoryId);
    }
}
