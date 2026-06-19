package org.example.vetalisbackend.clinical.domain.model.commands;

import java.time.LocalDateTime;

public record CreateHospitalizacionCommand(
        Long mascotaId,
        LocalDateTime fechaIngreso,
        LocalDateTime fechaSalida,
        String motivo,
        String diagnostico,
        String tratamiento,
        String estado,
        Long veterinarioId
) {}
