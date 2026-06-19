package org.example.vetalisbackend.clinical.domain.model.commands;

import java.time.LocalDateTime;

public record CreateConsultaCommand(
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
