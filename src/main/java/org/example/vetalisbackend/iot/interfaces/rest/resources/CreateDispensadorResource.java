package org.example.vetalisbackend.iot.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateDispensadorResource(
        @NotBlank @Size(max = 50)
        String numeroSerie,

        @NotBlank @Size(max = 50)
        String modelo,

        @Size(max = 20)
        String estado,

        @PositiveOrZero
        Double nivelAlimento,

        LocalDateTime ultimaConexion,

        Long mascotaId
) {}
