package org.example.vetalisbackend.iam.domain.model.commands;

public record SignUpCommand(
        String firstName,
        String lastName,
        String email,
        String password,
        String dni,
        String telefono,
        String especialidad
) {}
