package org.example.vetalisbackend.clients.interfaces.rest.resources;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateClienteResource(
        @NotBlank @Size(max = 100)
        String nombre,

        @NotBlank @Size(min = 8, max = 8)
        String dni,

        @Size(max = 15)
        String telefono,

        @Email @Size(max = 150)
        String email,

        @Size(max = 255)
        String direccion
) {}
