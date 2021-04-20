package org.example.repositories;

import org.example.domain.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository <CategoryEntity, Integer> {
    CategoryEntity findByTitle (String title);
}
