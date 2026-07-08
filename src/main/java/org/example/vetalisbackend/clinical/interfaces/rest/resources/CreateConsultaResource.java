package org.example.vetalisbackend.clinical.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateConsultaResource(
        @NotNull
        Long mascotaId,

        @NotNull
        Long veterinarioId,

        @NotNull
        LocalDateTime fecha,

        @NotBlank @Size(max = 50)
        String tipo,

        @Size(max = 1000)
        String subjetivo,

        @Size(max = 1000)
        String objetivo,

        @Size(max = 1000)
        String evaluacion,

        @Size(max = 1000)
        String plan,

        @NotBlank @Size(max = 20)
        String estado,

        @Size(max = 500)
        String diagnostico,

        Double temperatura
) {}
