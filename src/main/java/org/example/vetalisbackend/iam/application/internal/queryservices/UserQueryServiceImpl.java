package org.example.vetalisbackend.iam.application.internal.queryservices;

import org.example.vetalisbackend.iam.domain.model.aggregates.User;
import org.example.vetalisbackend.iam.domain.repositories.UserRepository;
import org.example.vetalisbackend.iam.application.queryservices.UserQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
