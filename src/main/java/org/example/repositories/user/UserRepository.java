package org.example.repositories.user;

import org.example.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {
   UserEntity findByEmail(String email);
}
