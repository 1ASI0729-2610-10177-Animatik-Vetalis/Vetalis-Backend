package org.example.vetalisbackend.clinical.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateVacunaResource(
        @NotNull
        Long mascotaId,

        Long tipoVacunaId,

        @NotBlank @Size(max = 100)
        String nombreVacuna,

        @Size(max = 50)
        String lote,

        @NotNull
        LocalDate fechaAplicacion,

        LocalDate proximaDosis,

        @NotBlank @Size(max = 20)
        String estado,

        @NotNull
        Long veterinarioId
) {}
