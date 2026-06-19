package org.example.vetalisbackend.clients.interfaces.rest.resources;

public record ClienteResource(
        Long id,
        String nombre,
        String dni,
        String telefono,
        String email,
        String direccion
) {}
