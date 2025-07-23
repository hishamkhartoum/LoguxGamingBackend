package com.hadef.loguxgaming.repository;

import com.hadef.loguxgaming.domain.entity.User;
import com.hadef.loguxgaming.domain.value.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    List<User> findAllByRole(UserRole role);
}
