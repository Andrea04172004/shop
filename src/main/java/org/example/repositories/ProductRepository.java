package org.example.repositories;

import org.example.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <ProductEntity, Integer> {
    ProductEntity findByName (String name);
}
