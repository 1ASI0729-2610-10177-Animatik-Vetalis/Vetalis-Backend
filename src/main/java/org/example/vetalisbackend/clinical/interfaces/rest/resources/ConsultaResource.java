package org.example.vetalisbackend.clinical.interfaces.rest.resources;

import java.time.LocalDateTime;

public record ConsultaResource(
        Long id,
        Long mascotaId,
        Long veterinarioId,
        LocalDateTime fecha,
        String tipo,
        String subjetivo,
        String objetivo,
        String evaluacion,
        String plan,
        String estado,
        String diagnostico
) {}
