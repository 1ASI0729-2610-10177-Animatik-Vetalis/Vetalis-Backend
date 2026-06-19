package org.example.vetalisbackend.iam.infrastructure.jpa;

import org.example.vetalisbackend.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryJpa extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
