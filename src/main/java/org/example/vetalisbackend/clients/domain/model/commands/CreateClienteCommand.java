package org.example.vetalisbackend.clients.domain.model.commands;

public record CreateClienteCommand(
        String nombre,
        String dni,
        String telefono,
        String email,
        String direccion
) {}
