package org.example.repositories;

import org.example.domain.LineItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemRepository extends JpaRepository <LineItemEntity, Integer> {
}
