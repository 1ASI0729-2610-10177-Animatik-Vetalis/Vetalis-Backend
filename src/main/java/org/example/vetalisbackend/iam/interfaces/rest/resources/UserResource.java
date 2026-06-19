package org.example.vetalisbackend.iam.interfaces.rest.resources;

public record UserResource(
        Long id,
        String username,
        String displayName,
        String dni,
        String telefono,
        String especialidad,
        String role
) {}
