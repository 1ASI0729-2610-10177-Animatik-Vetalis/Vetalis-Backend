package org.example.vetalisbackend.iam.application.internal.commandservices;

import org.example.vetalisbackend.iam.application.internal.outboundservices.HashingService;
import org.example.vetalisbackend.iam.domain.model.aggregates.User;
import org.example.vetalisbackend.iam.domain.model.commands.SignInCommand;
import org.example.vetalisbackend.iam.domain.model.commands.SignUpCommand;
import org.example.vetalisbackend.iam.domain.model.valueobjects.Role;
import org.example.vetalisbackend.iam.domain.repositories.UserRepository;
import org.example.vetalisbackend.iam.application.commandservices.UserCommandService;
import org.example.vetalisbackend.iam.infrastructure.tokens.BearerTokenService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final BearerTokenService bearerTokenService;

    public UserCommandServiceImpl(UserRepository userRepository,
                                  HashingService hashingService,
                                  BearerTokenService bearerTokenService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.bearerTokenService = bearerTokenService;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.email())) {
            throw new IllegalArgumentException("User with email " + command.email() + " already exists");
        }
        String displayName = command.firstName() + " " + command.lastName();
        String passwordHash = hashingService.encode(command.password());
        Role role = "ADMIN".equalsIgnoreCase(command.role()) ? Role.ADMIN : Role.VETERINARIAN;
        User user = new User(command.email(), passwordHash, displayName,
                command.dni(), command.telefono(), command.especialidad(), role);
        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<String[]> handle(SignInCommand command) {
        User user = userRepository.findByUsername(command.username())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        if (!hashingService.matches(command.password(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        String token = bearerTokenService.generateToken(user);
        return Optional.of(new String[]{token, user.getRole().name(), user.getId().toString()});
    }
}
