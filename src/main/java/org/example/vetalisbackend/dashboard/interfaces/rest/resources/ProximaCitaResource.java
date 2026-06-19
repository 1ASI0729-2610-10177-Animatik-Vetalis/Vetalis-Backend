package org.example.vetalisbackend.dashboard.interfaces.rest.resources;

import java.time.LocalDateTime;

public record ProximaCitaResource(
        Long id,
        Long mascotaId,
        Long veterinarioId,
        LocalDateTime fecha,
        String motivo,
        String tipo,
        String estado
) {}
