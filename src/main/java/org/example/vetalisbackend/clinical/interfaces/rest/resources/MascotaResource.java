package org.example.vetalisbackend.clinical.interfaces.rest.resources;

import java.time.LocalDate;

public record MascotaResource(
        Long id,
        String nombre,
        String sexo,
        LocalDate fechaNacimiento,
        Double peso,
        String estado,
        Long clienteId,
        Long razaId
) {}
