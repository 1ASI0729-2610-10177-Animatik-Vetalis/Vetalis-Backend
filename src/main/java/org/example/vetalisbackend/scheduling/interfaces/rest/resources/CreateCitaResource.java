package org.example.vetalisbackend.scheduling.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateCitaResource(
        @NotNull
        Long mascotaId,

        @NotNull
        Long veterinarioId,

        @NotNull
        LocalDateTime fecha,

        @NotBlank @Size(max = 500)
        String motivo,

        @NotBlank @Size(max = 50)
        String tipo,

        @Size(max = 20)
        String estado
) {}
