package org.example.vetalisbackend.clinical.interfaces.rest.resources;

import java.time.LocalDateTime;

public record HospitalizacionResource(
        Long id,
        Long mascotaId,
        LocalDateTime fechaIngreso,
        LocalDateTime fechaSalida,
        String motivo,
        String diagnostico,
        String tratamiento,
        String estado,
        Long veterinarioId
) {}
