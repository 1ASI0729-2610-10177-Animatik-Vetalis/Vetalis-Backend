package org.example.vetalisbackend.scheduling.interfaces.rest.resources;

import java.time.LocalDateTime;

public record CitaResource(
        Long id,
        Long mascotaId,
        Long veterinarioId,
        LocalDateTime fecha,
        String motivo,
        String tipo,
        String estado
) {}
