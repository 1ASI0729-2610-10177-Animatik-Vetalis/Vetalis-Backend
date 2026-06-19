package org.example.vetalisbackend.clinical.domain.model.commands;

import java.time.LocalDate;

public record CreateVacunaCommand(
        Long mascotaId,
        Long tipoVacunaId,
        String nombreVacuna,
        String lote,
        LocalDate fechaAplicacion,
        LocalDate proximaDosis,
        String estado,
        Long veterinarioId
) {}
