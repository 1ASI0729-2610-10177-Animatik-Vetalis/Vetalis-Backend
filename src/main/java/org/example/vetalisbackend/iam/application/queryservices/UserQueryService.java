package org.example.vetalisbackend.iam.application.queryservices;

import org.example.vetalisbackend.iam.domain.model.aggregates.User;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
    List<User> findAll();
}
