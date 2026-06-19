package org.example.vetalisbackend.scheduling.domain.model.commands;

import org.example.vetalisbackend.scheduling.domain.model.enums.EstadoCita;

import java.time.LocalDateTime;

public record CreateCitaCommand(
        Long mascotaId,
        Long veterinarioId,
        LocalDateTime fecha,
        String motivo,
        String tipo,
        EstadoCita estado
) {}
