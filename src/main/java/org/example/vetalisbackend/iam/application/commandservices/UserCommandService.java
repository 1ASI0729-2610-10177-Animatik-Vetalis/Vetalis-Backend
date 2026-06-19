package org.example.vetalisbackend.iam.application.commandservices;

import org.example.vetalisbackend.iam.domain.model.aggregates.User;
import org.example.vetalisbackend.iam.domain.model.commands.SignInCommand;
import org.example.vetalisbackend.iam.domain.model.commands.SignUpCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpCommand command);
    Optional<String[]> handle(SignInCommand command);
}
