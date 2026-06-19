package org.example.vetalisbackend.clinical.interfaces.rest.resources;

import java.time.LocalDate;

public record VacunaResource(
        Long id,
        Long mascotaId,
        Long tipoVacunaId,
        String nombreVacuna,
        String lote,
        LocalDate fechaAplicacion,
        LocalDate proximaDosis,
        String estado,
        Long veterinarioId
) {}
