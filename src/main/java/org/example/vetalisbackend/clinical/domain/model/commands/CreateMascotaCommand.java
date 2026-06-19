package org.example.vetalisbackend.clinical.domain.model.commands;

import java.time.LocalDate;

public record CreateMascotaCommand(
        String nombre,
        String sexo,
        LocalDate fechaNacimiento,
        Double peso,
        String estado,
        Long clienteId,
        Long razaId
) {}
