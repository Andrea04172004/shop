package org.example.services;

import org.example.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
   public ProductDto createNewProduct (ProductDto productDto);
   public ProductDto updateProduct (Integer productId, ProductDto productDto);
   public List<ProductDto> findAllProducts ();
   public ProductDto findProductById (Integer productId);
   public ProductDto findProductByName (String productName);
   public void deleteProduct (Integer productId);
}
