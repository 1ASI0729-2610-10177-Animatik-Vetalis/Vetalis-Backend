package org.example.vetalisbackend.clinical.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateHospitalizacionResource(
        @NotNull
        Long mascotaId,

        @NotNull
        LocalDateTime fechaIngreso,

        LocalDateTime fechaSalida,

        @NotBlank @Size(max = 500)
        String motivo,

        @Size(max = 500)
        String diagnostico,

        @Size(max = 500)
        String tratamiento,

        @NotBlank @Size(max = 20)
        String estado,

        @NotNull
        Long veterinarioId
) {}
