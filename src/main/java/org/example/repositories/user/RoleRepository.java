package org.example.repositories.user;

import org.example.domain.user.RoleEntity;
import org.example.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByRole (String role);
}
