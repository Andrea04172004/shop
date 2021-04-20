package org.example.services;

import org.example.dto.CategoryDto;
import org.example.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public CategoryDto createNewCategory (CategoryDto categoryDto);
    public CategoryDto updateCategory (Integer categoryId, CategoryDto productDto);
    public List<CategoryDto> findAllCategories ();
    public CategoryDto findCategoryById (Integer categoryId);
    public CategoryDto findCategoryByTitle (String categoryTitle);
    public void deleteCategory (Integer categoryId);
}
