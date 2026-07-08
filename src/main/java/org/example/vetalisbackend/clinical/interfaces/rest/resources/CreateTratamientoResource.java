package org.example.vetalisbackend.clinical.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTratamientoResource(
        @NotNull
        Long consultaId,

        Long medicamentoId,

        @Size(max = 1000)
        String descripcion,

        @Size(max = 100)
        String dosis,

        @Size(max = 100)
        String frecuencia,

        @Size(max = 100)
        String duracion
) {}
