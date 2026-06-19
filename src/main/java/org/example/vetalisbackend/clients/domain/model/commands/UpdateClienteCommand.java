package org.example.vetalisbackend.clients.domain.model.commands;

public record UpdateClienteCommand(
        Long id,
        String nombre,
        String dni,
        String telefono,
        String email,
        String direccion
) {}
