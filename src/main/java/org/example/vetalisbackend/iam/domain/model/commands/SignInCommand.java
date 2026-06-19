package org.example.vetalisbackend.iam.domain.model.commands;

public record SignInCommand(
        String username,
        String password
) {}
